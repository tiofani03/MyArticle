package id.tiooooo.myarticle.data.implementation.pagingsource

import androidx.core.net.toUri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.data.implementation.remote.response.toDomain
import id.tiooooo.myarticle.data.implementation.remote.service.SpaceFlightService
import id.tiooooo.myarticle.ui.pages.list.ArticleFilterParams
import id.tiooooo.myarticle.utils.DATATYPE

class ArticlePagingSource(
    private val service: SpaceFlightService,
    private val articleFilterParams: ArticleFilterParams,
) : PagingSource<Int, ArticleData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleData> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize

            val response = when (articleFilterParams.types) {
                DATATYPE.ARTICLE -> service.getArticles(
                    limit = limit,
                    offset = offset,
                    sortBy = articleFilterParams.sortBy,
                    newsSite = articleFilterParams.newsSite,
                    search = articleFilterParams.query
                )

                DATATYPE.BLOG -> service.getBlogs(
                    limit = limit,
                    offset = offset,
                    sortBy = articleFilterParams.sortBy,
                    newsSite = articleFilterParams.newsSite,
                    search = articleFilterParams.query
                )

                DATATYPE.REPORT -> service.getReports(
                    limit = limit,
                    offset = offset,
                    sortBy = articleFilterParams.sortBy,
                    newsSite = articleFilterParams.newsSite,
                    search = articleFilterParams.query
                )

                else -> service.getArticles(
                    limit = limit,
                    offset = offset,
                    sortBy = articleFilterParams.sortBy,
                    newsSite = articleFilterParams.newsSite,
                    search = articleFilterParams.query
                )
            }

            val data = response.results?.map { it.toDomain() } ?: emptyList()

            val nextOffset = getOffsetFromUrl(response.next)
            val prevOffset = if (offset == 0) null else maxOf(0, offset - limit)

            LoadResult.Page(
                data = data,
                prevKey = prevOffset,
                nextKey = nextOffset
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleData>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    private fun getOffsetFromUrl(url: String?): Int? {
        return url?.let { it.toUri().getQueryParameter("offset")?.toIntOrNull() }
    }
}