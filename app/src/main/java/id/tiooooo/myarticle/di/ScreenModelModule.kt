package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.ui.pages.home.HomeScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory { HomeScreenModel(get()) }
}