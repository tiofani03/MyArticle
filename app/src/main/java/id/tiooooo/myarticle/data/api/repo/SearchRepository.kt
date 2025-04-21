package id.tiooooo.myarticle.data.api.repo

import id.tiooooo.myarticle.data.implementation.local.dao.SearchDao
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun insertSearch(keyword: String)
    fun getAllSearches(): Flow<List<SearchEntity>>
    suspend fun deleteSearchById(id: Int)
    suspend fun clearAllSearches()
}

