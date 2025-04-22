package id.tiooooo.myarticle.ui.pages.login

import android.app.Activity

data class LoginState(
    val isLoginSuccess: Boolean = false,
)

sealed interface LoginIntent {
    data class OnLoginOrRegisterClicked(val activity: Activity) : LoginIntent
}

sealed interface LoginEffect {
    data object NavigateToMain : LoginEffect
    data class ShowErrorMessage(val errorMessage: String) : LoginEffect
}