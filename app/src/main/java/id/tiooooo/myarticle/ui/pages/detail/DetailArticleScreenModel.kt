package id.tiooooo.myarticle.ui.pages.detail

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.domain.usecase.GetArticleDetailUseCase
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailArticleScreenModel(
    private val getArticleDetailUseCase: GetArticleDetailUseCase,
) : ScreenModel {

    private val _detailState =
        MutableStateFlow<ResultState<ArticleData>>(ResultState.Loading)
    val detailState: StateFlow<ResultState<ArticleData>> = _detailState

    fun fetchDetail(id: Int, type: DATATYPE) {
        screenModelScope.launch {
            getArticleDetailUseCase(id = id, type).collect { result ->
                _detailState.value = result
            }
        }
    }
}