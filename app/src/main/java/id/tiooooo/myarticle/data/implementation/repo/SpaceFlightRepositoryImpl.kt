package id.tiooooo.myarticle.data.implementation.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.data.implementation.local.datastore.AppDatastore
import id.tiooooo.myarticle.data.implementation.pagingsource.ArticlePagingSource
import id.tiooooo.myarticle.data.implementation.remote.response.toDomain
import id.tiooooo.myarticle.data.implementation.remote.service.SpaceFlightService
import id.tiooooo.myarticle.ui.pages.list.ArticleFilterParams
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SpaceFlightRepositoryImpl(
    private val spaceFlightService: SpaceFlightService,
    private val appDatastore: AppDatastore,
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

    override fun getArticleFlow(
         articleFilterParams: ArticleFilterParams,
    ): Flow<PagingData<ArticleData>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ArticlePagingSource(spaceFlightService, articleFilterParams) }
        ).flow
    }

    override fun getNewsInfo() = flow {
        try {
            emit(ResultState.Loading)
            val localData = appDatastore.newsSite.first()
            val list = localData
                .removePrefix("[")
                .removeSuffix("]")
                .split(", ")
            if (list.isEmpty() || list.size == 1) {
                val response = spaceFlightService.getInfo()
                val data = response.newsSite.orEmpty()
                if (data.isNotEmpty()){
                    appDatastore.setNewsSite(data.toString())
                }

                emit(ResultState.Success(data))
            } else {
                emit(ResultState.Success(list))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(ResultState.Error(e.message.orEmpty()))
        }
    }.flowOn(Dispatchers.IO)
}