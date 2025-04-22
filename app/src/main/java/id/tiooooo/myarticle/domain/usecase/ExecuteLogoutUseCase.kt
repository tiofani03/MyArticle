package id.tiooooo.myarticle.domain.usecase

import android.app.Activity
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.Flow

class ExecuteLogoutUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(activity: Activity): Flow<ResultState<Boolean>> {
        return userRepository.executeLogout(activity)
    }
}
