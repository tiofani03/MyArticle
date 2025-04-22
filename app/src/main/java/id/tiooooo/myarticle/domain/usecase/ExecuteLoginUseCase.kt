package id.tiooooo.myarticle.domain.usecase

import android.app.Activity
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

class ExecuteLoginUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(activity: Activity): Flow<ResultState<Boolean>> = channelFlow {
        send(ResultState.Loading)

        userRepository.checkIsLoggedIn().collect { isLoggedIn ->
            if (!isLoggedIn) {
                userRepository.executeLogout(activity).collect { logoutState ->
                    if (logoutState is ResultState.Success) {
                        proceedLogin(activity)
                    }
                }
            } else {
                proceedLogin(activity)
            }
        }
    }

    private suspend fun ProducerScope<ResultState<Boolean>>.proceedLogin(activity: Activity) {
        userRepository.executeLogin(activity).collect { loginState ->
            send(loginState)
        }
    }
}
