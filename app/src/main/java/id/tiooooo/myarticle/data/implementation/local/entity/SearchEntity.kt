package id.tiooooo.myarticle.data.implementation.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "searchHistory")
data class SearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val keyword: String,
)