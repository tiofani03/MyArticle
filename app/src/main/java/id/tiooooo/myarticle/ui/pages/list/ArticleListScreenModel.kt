package id.tiooooo.myarticle.ui.pages.list

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.data.api.repo.SearchRepository
import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.domain.usecase.GetInfoUseCase
import id.tiooooo.myarticle.utils.createSortValueListSlug
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ArticleListScreenModel(
    private val articleRepository: SpaceFlightRepository,
    private val getInfoUseCase: GetInfoUseCase,
    private val searchRepository: SearchRepository,
) : BaseScreenModel<ArticleListState, ArticleListIntent, ArticleListEffect>(
    initialState = ArticleListState()
) {
    init {
        getFilterData()
        fetchSearchHistory()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val articlesFlow = state
        .map { it.articleFilterParams }
        .distinctUntilChanged()
        .flatMapLatest { params ->
            articleRepository.getArticleFlow(params)
        }
        .cachedIn(screenModelScope)


    override fun reducer(state: ArticleListState, intent: ArticleListIntent): ArticleListState {
        return when (intent) {
            is ArticleListIntent.UpdateArticleFilterParams -> {
                state.copy(
                    articleFilterParams = intent.params
                )
            }

            is ArticleListIntent.UpdateBottomSheetFilter -> {
                state.copy(
                    isBottomSheetFilterOpen = intent.isOpen
                )
            }

            is ArticleListIntent.UpdateBottomSheetNewsSite -> {
                state.copy(
                    isBottomSheetNewsSite = intent.isOpen
                )
            }

            is ArticleListIntent.UpdateSearchExpand -> {
                state.copy(
                    isSearchExpand = intent.isExpand
                )
            }

            else -> state
        }
    }

    override suspend fun handleIntentSideEffect(intent: ArticleListIntent) {
        when (intent) {
            is ArticleListIntent.SaveSearchQuery -> saveSearchKeyword(intent.query)
            is ArticleListIntent.RemoveSearchQuery -> {
                screenModelScope.launch {
                    searchRepository.deleteSearchById(intent.searchEntity.id)
                }
            }

            else -> {}
        }
    }

    private fun fetchSearchHistory() {
        screenModelScope.launch {
            searchRepository.getAllSearches().collect { data ->
                setState {
                    state.value.copy(
                        searchHistory = data
                    )
                }
            }
        }
    }

    private fun saveSearchKeyword(keyword: String) {
        screenModelScope.launch {
            searchRepository.insertSearch(keyword)
        }
    }

    private fun getFilterData() {
        screenModelScope.launch {
            getInfoUseCase().collect { result ->
                when (result) {
                    is ResultState.Success -> {
                        setState {
                            state.value.copy(
                                filterNewsSite = result.data,
                                filterSortBy = createSortValueListSlug()
                            )
                        }
                    }

                    else -> {
                        setState {
                            state.value.copy(
                                filterNewsSite = emptyList(),
                                filterSortBy = createSortValueListSlug()
                            )
                        }
                    }


                }
            }
        }
    }
}
