package id.tiooooo.myarticle.domain.usecase

import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.ui.pages.home.model.HomeItemData
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetArticleUseCase(
    private val repository: SpaceFlightRepository,
) {
    operator fun invoke(type: DATATYPE = DATATYPE.ARTICLE): Flow<ResultState<List<HomeItemData>>> {
        val repositoryFlow = when (type) {
            DATATYPE.ARTICLE -> repository.getArticles()
            DATATYPE.BLOG -> repository.getBlogs()
            DATATYPE.REPORT -> repository.getReports()
        }

        return repositoryFlow.map { result ->
            when (result) {
                is ResultState.Success -> {
                    val mapped = result.data.map { item ->
                        HomeItemData(
                            imageUrl = item.imageUrl,
                            title = item.title
                        )
                    }
                    ResultState.Success(mapped)
                }

                is ResultState.Error -> ResultState.Error(result.throwable)
                ResultState.Loading -> ResultState.Loading
            }
        }
    }
}

enum class DATATYPE {
    ARTICLE,
    BLOG,
    REPORT
}
