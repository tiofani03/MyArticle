package id.tiooooo.myarticle.ui.pages.home

import id.tiooooo.myarticle.ui.pages.home.model.HomeItemData
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.wrapper.ResultState

sealed interface HomeEffect {
    data object NavigateToProfile : HomeEffect
    data class NavigateToDetail(val id: Int) : HomeEffect
    data class NavigateToSeeAll(val type: DATATYPE) : HomeEffect
}

data class HomeState(
    val articles : ResultState<List<HomeItemData>> = ResultState.Loading,
    val blogs : ResultState<List<HomeItemData>> = ResultState.Loading,
    val reports : ResultState<List<HomeItemData>> = ResultState.Loading,
    val userName: String = "",
)

sealed interface HomeIntent {
    data object FetchInitialData : HomeIntent
    data object OnProfileClicked : HomeIntent
    data class OnArticleClicked(val id: Int) : HomeIntent
    data class OnSeeAllClicked(val type: DATATYPE) : HomeIntent
}