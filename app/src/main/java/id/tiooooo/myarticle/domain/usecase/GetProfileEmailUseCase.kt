package id.tiooooo.myarticle.domain.usecase

import id.tiooooo.myarticle.data.api.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class GetProfileEmailUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<String> {
        return userRepository.getProfileEmail()
    }
}

class GetProfileNameUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): String {
        return userRepository.getProfileEmail().first().substringBefore("@")
    }
}
