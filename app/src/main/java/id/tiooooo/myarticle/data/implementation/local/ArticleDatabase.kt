package id.tiooooo.myarticle.data.implementation.local

import androidx.room.Database
import androidx.room.RoomDatabase
import id.tiooooo.myarticle.data.implementation.local.dao.SearchDao
import id.tiooooo.myarticle.data.implementation.local.entity.SearchEntity

@Database(
    entities = [SearchEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}