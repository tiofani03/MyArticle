package id.tiooooo.myarticle.di

import androidx.room.Room
import id.tiooooo.myarticle.data.implementation.local.ArticleDatabase
import id.tiooooo.myarticle.data.implementation.local.datastore.AppDatastore
import id.tiooooo.myarticle.utils.AppConstants.DATABASE_NAME
import org.koin.dsl.module

val localModule = module {
    single { AppDatastore(get()) }
    single {
        Room.databaseBuilder(get(), ArticleDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(false)
            .build()
    }
    single { get<ArticleDatabase>().searchDao() }
}