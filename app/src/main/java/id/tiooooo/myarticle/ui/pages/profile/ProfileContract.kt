package id.tiooooo.myarticle.ui.pages.profile

import android.app.Activity

sealed interface ProfileEffect {
    data object NavigateToLogin : ProfileEffect
}

data class ProfileState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val name: String = "",
    val email: String = "",
    val activeTheme: String = "",
    val selectedLanguage: String = "",
    val isShowDialogTheme: Boolean = false,
    val isShowDialogLanguage: Boolean = false,
)

sealed interface ProfileIntent {
    data class ExecuteLogout(val activity: Activity) : ProfileIntent
    data object InitProfile : ProfileIntent
    data class UpdateTheme(val value: String) : ProfileIntent
    data class UpdateLanguage(val value: String) : ProfileIntent
    data class ShowDialogTheme(val value: Boolean) : ProfileIntent
    data class ShowDialogLanguage(val value: Boolean) : ProfileIntent
}