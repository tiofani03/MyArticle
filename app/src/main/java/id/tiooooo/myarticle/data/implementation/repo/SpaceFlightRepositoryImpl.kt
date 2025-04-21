package id.tiooooo.myarticle.data.implementation.repo

import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.data.implementation.remote.response.toDomain
import id.tiooooo.myarticle.data.implementation.remote.service.SpaceFlightService
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SpaceFlightRepositoryImpl(
    private val spaceFlightService: SpaceFlightService,
) : SpaceFlightRepository {

    override fun getArticles() = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getArticles()
            val data = response.results?.map { it.toDomain() }.orEmpty()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getArticlesById(id: Int) = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getArticlesById(id)
            val data = response.toDomain()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBlogs() = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getBlogs()
            val data = response.results?.map { it.toDomain() }.orEmpty()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getBlogsById(id: Int) = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getBlogsById(id)
            val data = response.toDomain()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getReports() = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getReports()
            val data = response.results?.map { it.toDomain() }.orEmpty()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)

    override fun getReportsById(id: Int) = flow {
        try {
            emit(ResultState.Loading)
            val response = spaceFlightService.getReportsById(id)
            val data = response.toDomain()

            emit(ResultState.Success(data))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}