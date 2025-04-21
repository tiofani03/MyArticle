package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.domain.usecase.GetArticleDetailUseCase
import id.tiooooo.myarticle.domain.usecase.GetArticleUseCase
import id.tiooooo.myarticle.domain.usecase.GetInfoUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetArticleUseCase(get()) }
    single { GetArticleDetailUseCase(get()) }
    single { GetInfoUseCase(get()) }
}