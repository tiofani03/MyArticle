package id.tiooooo.myarticle.data.implementation.repo

import android.app.Activity
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.data.implementation.local.datastore.AppDatastore
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class UserRepositoryImpl(
    private val appDatastore: AppDatastore,
    private val account: Auth0,
    private val externalScope: CoroutineScope,
) : UserRepository {
    override fun executeLogin(activity: Activity): Flow<ResultState<Boolean>> = callbackFlow {
        trySend(ResultState.Loading)
        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            .start(activity, object : Callback<Credentials, AuthenticationException> {
                override fun onFailure(error: AuthenticationException) {
                    trySend(ResultState.Error(error.localizedMessage.orEmpty()))
                    close()
                }

                override fun onSuccess(result: Credentials) {
                    externalScope.launch {
                        appDatastore.setUserEmail(
                            result.user.email.orEmpty(),
                            System.currentTimeMillis().toString()
                        )
                        appDatastore.setUserNickName(
                            result.user.name ?: result.user.email.orEmpty().substringBefore("@"),
                        )
                        appDatastore.setIsLoggedIn(true)
                    }
                    trySend(ResultState.Success(true))
                    close()
                }
            })

        awaitClose { }
    }

    override fun executeLogout(
        activity: Activity
    ): Flow<ResultState<Boolean>> = callbackFlow {
        trySend(ResultState.Loading)

        WebAuthProvider.logout(account)
            .withScheme("demo")
            .start(activity, object : Callback<Void?, AuthenticationException> {
                override fun onSuccess(result: Void?) {
                    externalScope.launch {
                        appDatastore.clearUserEmail()
                        appDatastore.setIsLoggedIn(false)
                        trySend(ResultState.Success(true))
                        close()
                    }
                }

                override fun onFailure(error: AuthenticationException) {
                    trySend(ResultState.Error(error.localizedMessage.orEmpty()))
                    close()
                }
            })

        awaitClose { }
    }

    override suspend fun getProfileEmail(): Flow<String> {
        return appDatastore.userEmail
    }

    override suspend fun getProfileNickName(): Flow<String> {
        return appDatastore.userNickName
    }

    override fun checkIsLoggedIn(): Flow<Boolean> = flow {
        val isLoggedIn = appDatastore.isLoggedIn.first()
        val email = appDatastore.userEmail.first()
        emit(isLoggedIn && email.isNotEmpty())
    }
}