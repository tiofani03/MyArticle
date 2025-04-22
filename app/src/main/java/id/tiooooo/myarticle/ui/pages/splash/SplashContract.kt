package id.tiooooo.myarticle.ui.pages.splash

sealed interface SplashEffect {
    data object NavigateToLogin : SplashEffect
    data object NavigateToHome : SplashEffect
}

data class SplashState(
    val isLoading: Boolean = true,
)

sealed interface SplashIntent {
    data object CheckLogin : SplashIntent
}