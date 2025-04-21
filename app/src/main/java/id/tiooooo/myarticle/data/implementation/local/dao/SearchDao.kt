package id.tiooooo.myarticle.data.implementation.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearch(search: SearchEntity)

    @Query("SELECT * FROM searchHistory ORDER BY id DESC")
    fun getAllSearches(): Flow<List<SearchEntity>>

    @Query("DELETE FROM searchHistory WHERE id = :id")
    suspend fun deleteSearchById(id: Int)

    @Query("DELETE FROM searchHistory")
    suspend fun clearAllSearches()
}
