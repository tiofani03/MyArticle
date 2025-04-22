package id.tiooooo.myarticle.ui.pages.splash

import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.data.api.repo.UserRepository
import kotlinx.coroutines.launch

class SplashScreenModel(
    private val userRepository: UserRepository,
) : BaseScreenModel<SplashState, SplashIntent, SplashEffect>(
    initialState = SplashState()
) {
    init {
        dispatch(SplashIntent.CheckLogin)
    }

    override fun reducer(state: SplashState, intent: SplashIntent): SplashState {
        return when (intent) {
            is SplashIntent.CheckLogin -> state.copy(isLoading = true)
        }
    }

    override suspend fun handleIntentSideEffect(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.CheckLogin -> {
                screenModelScope.launch {
                    userRepository.checkIsLoggedIn().collect {
                        if (it) {
                            sendEffect(SplashEffect.NavigateToHome)
                        } else {
                            sendEffect(SplashEffect.NavigateToLogin)
                        }
                    }
                }
            }
        }
    }


}