package id.tiooooo.myarticle.domain.usecase

import androidx.paging.PagingData
import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.ui.pages.list.ArticleFilterParams
import kotlinx.coroutines.flow.Flow

class GetArticlePagingUseCase(
    private val repository: SpaceFlightRepository
) {
    operator fun invoke(
        articleFilterParams: ArticleFilterParams = ArticleFilterParams()
    ): Flow<PagingData<ArticleData>> {
        return repository.getArticleFlow(articleFilterParams)
    }
}
