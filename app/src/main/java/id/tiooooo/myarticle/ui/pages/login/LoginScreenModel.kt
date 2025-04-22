package id.tiooooo.myarticle.ui.pages.login

import android.app.Activity
import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.domain.usecase.ExecuteLoginUseCase
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginScreenModel(
    private val executeLoginUseCase: ExecuteLoginUseCase
) : BaseScreenModel<LoginState, LoginIntent, LoginEffect>(
    initialState = LoginState()
) {
    private val _accessToken = MutableStateFlow<String?>(null)
    val accessToken: StateFlow<String?> = _accessToken

    private fun checkLogin(
        activity: Activity,
    ) {
        screenModelScope.launch {
            executeLoginUseCase.invoke(activity).collect {
                when (it) {
                    is ResultState.Error -> {
                        sendEffect(LoginEffect.ShowErrorMessage(it.throwable.orEmpty()))
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