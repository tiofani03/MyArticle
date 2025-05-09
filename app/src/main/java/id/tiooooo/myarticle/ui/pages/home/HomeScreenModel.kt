package id.tiooooo.myarticle.ui.pages.home

import cafe.adriel.voyager.core.model.screenModelScope
import id.tiooooo.myarticle.base.BaseScreenModel
import id.tiooooo.myarticle.domain.usecase.GetArticleUseCase
import id.tiooooo.myarticle.domain.usecase.GetProfileNameUseCase
import id.tiooooo.myarticle.utils.DATATYPE
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeScreenModel(
    private val getArticleUseCase: GetArticleUseCase,
    private val getProfileNameUseCase: GetProfileNameUseCase,
) : BaseScreenModel<HomeState, HomeIntent, HomeEffect>(
    initialState = HomeState()
) {

    init {
        dispatch(HomeIntent.FetchInitialData)
    }

    override fun reducer(state: HomeState, intent: HomeIntent): HomeState {
        return state
    }

    override suspend fun handleIntentSideEffect(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.FetchInitialData -> initialData()
            is HomeIntent.OnArticleClicked -> sendEffect(HomeEffect.NavigateToDetail(intent.id))
            is HomeIntent.OnProfileClicked -> sendEffect(HomeEffect.NavigateToProfile)
            is HomeIntent.OnSeeAllClicked -> sendEffect(HomeEffect.NavigateToSeeAll(intent.type))
        }
    }

    private fun initialData() {
        screenModelScope.launch {
            getArticleUseCase(type = DATATYPE.REPORT).collect { result ->
                setState { it.copy(reports = result) }
            }
        }

        screenModelScope.launch {
            getArticleUseCase(type = DATATYPE.ARTICLE).collect { result ->
                setState { it.copy(articles = result) }
            }
        }

        screenModelScope.launch {
            getArticleUseCase(type = DATATYPE.BLOG).collect { result ->
                setState { it.copy(blogs = result) }
            }
        }

        screenModelScope.launch {
            val name = getProfileNameUseCase.invoke()
            setState { it.copy(userName = name) }
        }
    }


    fun getGreetingMessage(): String {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

        return when (currentHour) {
            in 5..10 -> "Good Morning"
            in 11..14 -> "Good Afternoon"
            in 15..17 -> "Good Evening"
            in 18..23, in 0..4 -> "Good Night"
            else -> "Halo"
        }
    }

}
