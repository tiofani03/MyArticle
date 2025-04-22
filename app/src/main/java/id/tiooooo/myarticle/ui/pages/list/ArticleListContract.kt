package id.tiooooo.myarticle.ui.pages.list

import androidx.compose.material3.rememberModalBottomSheetState
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.SortValue

data class ArticleListState(
    val articleFilterParams: ArticleFilterParams = ArticleFilterParams(),
    val searchHistory: List<SearchEntity> = listOf(),
    val filterNewsSite: List<String> = listOf(),
    val filterSortBy: List<SortValue> = listOf(),
    val isSearchExpand: Boolean = false,
    val isBottomSheetFilterOpen: Boolean = false,
    val isBottomSheetNewsSite: Boolean = false,
)

sealed interface ArticleListIntent {
    data class UpdateArticleFilterParams(val params: ArticleFilterParams) : ArticleListIntent
    data class UpdateBottomSheetFilter(val isOpen: Boolean) : ArticleListIntent
    data class UpdateBottomSheetNewsSite(val isOpen: Boolean) : ArticleListIntent
    data class UpdateSearchExpand(val isExpand: Boolean) : ArticleListIntent
    data class SaveSearchQuery(val query: String) : ArticleListIntent
    data class RemoveSearchQuery(val searchEntity: SearchEntity) : ArticleListIntent
}

data class ArticleFilterParams(
    val types: DATATYPE = DATATYPE.ARTICLE,
    val sortBy: String = "-published_at",
    val query: String = "",
    val newsSite: String = "",
)
