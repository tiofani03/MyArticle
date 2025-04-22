package id.tiooooo.myarticle.ui.pages.splash

import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.domain.usecase.GetCheckIsLoggedInUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenModel(
    private val getCheckIsLoggedInUseCase: GetCheckIsLoggedInUseCase
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
                    delay(1500)
                    getCheckIsLoggedInUseCase.invoke().collect {
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