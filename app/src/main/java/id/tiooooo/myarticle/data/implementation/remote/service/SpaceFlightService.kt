package id.tiooooo.myarticle.data.implementation.remote.service

import id.tiooooo.myarticle.data.implementation.remote.response.ArticleResponse
import id.tiooooo.myarticle.utils.wrapper.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceFlightService {
    @GET("v4/articles")
    suspend fun getArticles(): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/articles/{id}")
    suspend fun getArticlesById(@Path("id") id: Int): ArticleResponse

    @GET("v4/blogs")
    suspend fun getBlogs(): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/blogs/{id}")
    suspend fun getBlogsById(@Path("id") id: Int): ArticleResponse

    @GET("v4/reports")
    suspend fun getReports(): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/reports/{id}")
    suspend fun getReportsById(@Path("id") id: Int): ArticleResponse
}