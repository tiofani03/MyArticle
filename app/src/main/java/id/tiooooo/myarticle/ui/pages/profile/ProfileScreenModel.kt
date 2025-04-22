package id.tiooooo.myarticle.ui.pages.profile

import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val userRepository: UserRepository,
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
                userRepository.executeLogout(intent.activity).collect { logoutState ->
                    when (logoutState) {
                        is ResultState.Success -> sendEffect(ProfileEffect.NavigateToLogin)
                        else -> Unit
                    }
                }
            }

            is ProfileIntent.InitProfile -> {
                val currentState = state.value
                screenModelScope.launch {
                    combine(
                        userRepository.getProfileEmail(),
                        userRepository.getProfileNickName()
                    ) { email, name ->
                        email to name
                    }.collect { (email, name) ->
                        setState {
                            currentState.copy(
                                email = email,
                                name = name.ifEmpty {
                                    email.substringBefore("@")
                                },
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