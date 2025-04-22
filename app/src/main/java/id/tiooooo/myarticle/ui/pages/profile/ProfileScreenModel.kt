package id.tiooooo.myarticle.ui.pages.profile

import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.domain.usecase.ExecuteLogoutUseCase
import id.tiooooo.myarticle.domain.usecase.GetCheckIsLoggedInUseCase
import id.tiooooo.myarticle.domain.usecase.GetProfileEmailUseCase
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val getProfileEmailUseCase: GetProfileEmailUseCase,
    private val executeLogoutUseCase: ExecuteLogoutUseCase,
) : BaseScreenModel<ProfileState, ProfileIntent, ProfileEffect>(
    initialState = ProfileState()
) {
    init {
        dispatch(ProfileIntent.InitProfile)
    }

    override fun reducer(state: ProfileState, intent: ProfileIntent): ProfileState {
        return when (intent) {
            is ProfileIntent.ExecuteLogout -> state
            is ProfileIntent.InitProfile -> state
            is ProfileIntent.UpdateTheme -> state
            is ProfileIntent.UpdateLanguage -> state
            is ProfileIntent.ShowDialogTheme -> state.copy(isShowDialogTheme = intent.value)
            is ProfileIntent.ShowDialogLanguage -> state.copy(isShowDialogLanguage = intent.value)
        }
    }

    override suspend fun handleIntentSideEffect(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.ExecuteLogout -> {
                executeLogoutUseCase.invoke(intent.activity).collect { logoutState ->
                    when (logoutState) {
                        is ResultState.Success -> sendEffect(ProfileEffect.NavigateToLogin)
                        else -> Unit
                    }
                }
            }

            is ProfileIntent.InitProfile -> {
                screenModelScope.launch {
                    getProfileEmailUseCase.invoke().collect { email ->
                        setState {
                            it.copy(
                                email = email,
                                name = email.substringBefore("@"),
                            )
                        }
                    }
                }
            }

            is ProfileIntent.UpdateTheme -> {}
            is ProfileIntent.UpdateLanguage -> {}

            else -> Unit
        }
    }
}