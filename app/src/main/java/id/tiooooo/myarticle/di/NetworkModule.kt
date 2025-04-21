package id.tiooooo.myarticle.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.tiooooo.myarticle.data.implementation.remote.service.SpaceFlightService
import id.tiooooo.myarticle.utils.AppConstants.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        ChuckerInterceptor(
            context = get()
        )
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ChuckerInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<SpaceFlightService> {
        get<Retrofit>().create(SpaceFlightService::class.java)
    }
}

