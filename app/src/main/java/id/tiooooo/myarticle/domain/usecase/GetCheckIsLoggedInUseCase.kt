package id.tiooooo.myarticle.domain.usecase

import id.tiooooo.myarticle.data.api.repo.UserRepository
import kotlinx.coroutines.flow.Flow

class GetCheckIsLoggedInUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Boolean> {
        return userRepository.checkIsLoggedIn()
    }
}
