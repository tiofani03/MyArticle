package id.tiooooo.myarticle.domain.usecase

import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository

class GetInfoUseCase(
    private val repository: SpaceFlightRepository,
) {
    operator fun invoke() = repository.getNewsInfo()
}