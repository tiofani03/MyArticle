package id.tiooooo.myarticle.di

import id.tiooooo.myarticle.data.api.repo.SpaceFlightRepository
import id.tiooooo.myarticle.data.implementation.repo.SpaceFlightRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<SpaceFlightRepository> { SpaceFlightRepositoryImpl(get()) }
}