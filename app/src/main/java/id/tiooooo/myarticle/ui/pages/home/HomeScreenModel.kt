package id.tiooooo.myarticle.ui.pages.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.domain.usecase.GetArticleUseCase
import id.tiooooo.myarticle.ui.pages.home.model.HomeItemData
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeScreenModel(
    private val getArticleUseCase: GetArticleUseCase,
) : ScreenModel {

    private val _articlesState =
        MutableStateFlow<ResultState<List<HomeItemData>>>(ResultState.Loading)
    val articlesState: StateFlow<ResultState<List<HomeItemData>>> = _articlesState

    private val _blogsState =
        MutableStateFlow<ResultState<List<HomeItemData>>>(ResultState.Loading)
    val blogsState: StateFlow<ResultState<List<HomeItemData>>> = _blogsState

    private val _reportsState =
        MutableStateFlow<ResultState<List<HomeItemData>>>(ResultState.Loading)
    val reportsState: StateFlow<ResultState<List<HomeItemData>>> = _reportsState

    init {
        fetchArticles()
        fetchBlogs()
        fetchReports()
    }

    private fun fetchArticles() {
        screenModelScope.launch {
            getArticleUseCase().collect { result ->
                _articlesState.value = result
            }
        }
    }

    private fun fetchBlogs() {
        screenModelScope.launch {
            getArticleUseCase(type = DATATYPE.BLOG).collect { result ->
                _blogsState.value = result
            }
        }
    }

    private fun fetchReports() {
        screenModelScope.launch {
            getArticleUseCase(type = DATATYPE.REPORT).collect { result ->
                _reportsState.value = result
            }
        }
    }

    fun getGreetingMessage(): String {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (currentHour) {
            in 5..10 -> "Selamat pagi"
            in 11..14 -> "Selamat siang"
            in 15..17 -> "Selamat sore"
            in 18..23, in 0..4 -> "Selamat malam"
            else -> "Halo"
        }
    }

}
