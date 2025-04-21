package id.tiooooo.myarticle

import android.app.Application
import id.tiooooo.myarticle.di.networkModule
import id.tiooooo.myarticle.di.repositoryModule
import id.tiooooo.myarticle.di.screenModelModule
import id.tiooooo.myarticle.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyArticleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyArticleApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    screenModelModule,
                )
            )
        }
    }
}