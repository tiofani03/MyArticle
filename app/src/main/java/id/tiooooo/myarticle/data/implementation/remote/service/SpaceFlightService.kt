package id.tiooooo.myarticle.data.implementation.remote.service

import id.tiooooo.myarticle.data.implementation.remote.response.ArticleResponse
import id.tiooooo.myarticle.data.implementation.remote.response.InfoResponse
import id.tiooooo.myarticle.utils.wrapper.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpaceFlightService {
    @GET("v4/articles")
    suspend fun getArticles(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("ordering") sortBy: String? = "-published_at",
        @Query("news_site") newsSite: String? = null,
        @Query("search") search: String? = null,
    ): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/articles/{id}")
    suspend fun getArticlesById(@Path("id") id: Int): ArticleResponse

    @GET("v4/blogs")
    suspend fun getBlogs(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("ordering") sortBy: String? = "-published_at",
        @Query("news_site") newsSite: String? = null,
        @Query("search") search: String? = null,
    ): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/blogs/{id}")
    suspend fun getBlogsById(@Path("id") id: Int): ArticleResponse

    @GET("v4/reports")
    suspend fun getReports(
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("ordering") sortBy: String?= "-published_at",
        @Query("news_site") newsSite: String? = null,
        @Query("search") search: String? = null,
    ): ResponseWrapper<List<ArticleResponse>>

    @GET("v4/reports/{id}")
    suspend fun getReportsById(@Path("id") id: Int): ArticleResponse

    @GET("v4/info")
    suspend fun getInfo(): InfoResponse

}