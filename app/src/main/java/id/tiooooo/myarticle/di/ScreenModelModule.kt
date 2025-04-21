package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.ui.pages.detail.DetailArticleScreenModel
import id.tiooooo.myarticle.ui.pages.home.HomeScreenModel
import id.tiooooo.myarticle.ui.pages.list.ArticleListScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory { HomeScreenModel(get()) }
    factory { DetailArticleScreenModel(get()) }
    factory { ArticleListScreenModel(get(), get(), get()) }
}