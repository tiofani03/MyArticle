package id.tiooooo.myarticle.data.api.repo

import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.Flow

interface SpaceFlightRepository {
    fun getArticles(): Flow<ResultState<List<ArticleData>>>
    fun getArticlesById(id: Int): Flow<ResultState<ArticleData>>

    fun getBlogs(): Flow<ResultState<List<ArticleData>>>
    fun getBlogsById(id: Int): Flow<ResultState<ArticleData>>

    fun getReports(): Flow<ResultState<List<ArticleData>>>
    fun getReportsById(id: Int): Flow<ResultState<ArticleData>>
}