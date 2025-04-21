package id.tiooooo.myarticle.data.implementation.repo

import id.tiooooo.myarticle.data.api.repo.SearchRepository
import id.tiooooo.myarticle.data.implementation.local.dao.SearchDao
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dao: SearchDao
) : SearchRepository {

    override suspend fun insertSearch(keyword: String) {
        val search = SearchEntity(keyword = keyword)
        dao.insertSearch(search)
    }

    override fun getAllSearches(): Flow<List<SearchEntity>> {
        return dao.getAllSearches()
    }

    override suspend fun deleteSearchById(id: Int) {
        dao.deleteSearchById(id)
    }

    override suspend fun clearAllSearches() {
        dao.clearAllSearches()
    }
}