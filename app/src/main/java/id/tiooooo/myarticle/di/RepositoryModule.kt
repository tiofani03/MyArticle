package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.data.api.repo.SearchRepository
import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.data.api.repo.UserRepository
import id.tiooooo.myarticle.data.implementation.repo.SearchRepositoryImpl
import id.tiooooo.myarticle.data.implementation.repo.SpaceFlightRepositoryImpl
import id.tiooooo.myarticle.data.implementation.repo.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<SpaceFlightRepository> { SpaceFlightRepositoryImpl(get(), get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get(), get()) }
}