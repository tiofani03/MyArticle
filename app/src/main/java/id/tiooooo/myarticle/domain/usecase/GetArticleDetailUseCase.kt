package id.tiooooo.myarticle.domain.usecase

import id.tiooooo.myarticle.data.api.model.ArticleData
import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.utils.DATATYPE
import id.tiooooo.myarticle.utils.wrapper.ResultState
import kotlinx.coroutines.flow.Flow

class GetArticleDetailUseCase(
    private val repository: SpaceFlightRepository,
) {
    operator fun invoke(
        id: Int,
        type: DATATYPE = DATATYPE.ARTICLE,
    ): Flow<ResultState<ArticleData>> {
        return when (type) {
            DATATYPE.ARTICLE -> repository.getArticlesById(id)
            DATATYPE.BLOG -> repository.getBlogsById(id)
            DATATYPE.REPORT -> repository.getReportsById(id)
        }
    }
}