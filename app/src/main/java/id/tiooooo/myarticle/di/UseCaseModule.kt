package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.domain.usecase.ExecuteLoginUseCase
import id.tiooooo.myarticle.domain.usecase.ExecuteLogoutUseCase
import id.tiooooo.myarticle.domain.usecase.GetArticleDetailUseCase
import id.tiooooo.myarticle.domain.usecase.GetArticlePagingUseCase
import id.tiooooo.myarticle.domain.usecase.GetArticleUseCase
import id.tiooooo.myarticle.domain.usecase.GetCheckIsLoggedInUseCase
import id.tiooooo.myarticle.domain.usecase.GetInfoUseCase
import id.tiooooo.myarticle.domain.usecase.GetProfileEmailUseCase
import id.tiooooo.myarticle.domain.usecase.GetProfileNameUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetArticleUseCase(get()) }
    single { GetArticleDetailUseCase(get()) }
    single { GetArticlePagingUseCase(get()) }
    single { GetInfoUseCase(get()) }
    single { ExecuteLoginUseCase(get()) }
    single { ExecuteLogoutUseCase(get()) }
    single { GetProfileNameUseCase(get()) }
    single { GetProfileEmailUseCase(get()) }
    single { GetCheckIsLoggedInUseCase(get()) }
}