package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.ui.pages.detail.DetailArticleScreenModel
import id.tiooooo.myarticle.ui.pages.home.HomeScreenModel
import id.tiooooo.myarticle.ui.pages.list.ArticleListScreenModel
import id.tiooooo.myarticle.ui.pages.login.LoginScreenModel
import id.tiooooo.myarticle.ui.pages.profile.ProfileScreenModel
import id.tiooooo.myarticle.ui.pages.splash.SplashScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory { HomeScreenModel(get(), get()) }
    factory { DetailArticleScreenModel(get()) }
    factory { ArticleListScreenModel(get(), get(), get()) }
    factory { LoginScreenModel(get()) }
    factory { ProfileScreenModel(get()) }
    factory { SplashScreenModel(get()) }
}