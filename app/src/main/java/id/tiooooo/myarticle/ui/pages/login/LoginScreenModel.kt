package id.tiooooo.myarticle.ui.pages.login

import android.app.Activity
import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenModel(
    private val userRepository: UserRepository,
) : BaseScreenModel<LoginState, LoginIntent, LoginEffect>(
    initialState = LoginState()
) {
    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken

    private fun checkLogin(
        activity: Activity,
    ) {
        screenModelScope.launch {
            userRepository.checkIsLoggedIn().collect {
                if (!it) {
                    userRepository.executeLogout(activity).collect { logoutState ->
                        when (logoutState) {
                            is ResultState.Success -> executeLogin(activity)
                            else -> Unit
                        }
                    }
                } else {
                    executeLogin(activity)
                }
            }
        }
    }

    private fun executeLogin(
        activity: Activity,
    ) {
        screenModelScope.launch {
            userRepository.executeLogin(activity).collect { resultState ->
                when (resultState) {
                    is ResultState.Error -> {
                        sendEffect(LoginEffect.ShowErrorMessage(resultState.throwable.orEmpty()))
                    }

                    is ResultState.Success -> {
                        sendEffect(LoginEffect.NavigateToMain)
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun reducer(state: LoginState, intent: LoginIntent): LoginState {
        return state
    }

    override suspend fun handleIntentSideEffect(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.OnLoginOrRegisterClicked -> {
                checkLogin(activity = intent.activity)
            }
        }
    }
}