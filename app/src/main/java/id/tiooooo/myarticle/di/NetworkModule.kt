package id.tiooooo.myarticle.di

import android.content.Context
import com.auth0.android.Auth0
import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.tiooooo.myarticle.R
import id.tiooooo.myarticle.data.implementation.remote.service.SpaceFlightService
import id.tiooooo.myarticle.utils.AppConstants.BASE_URL
import id.tiooooo.myarticle.utils.AppConstants.CERTIFICATE_256
import id.tiooooo.myarticle.utils.AppConstants.HOST_NAME_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { ChuckerInterceptor(context = get()) }
    single {
        Auth0(
            clientId = get<Context>().getString(R.string.com_auth0_client_id),
            domain = get<Context>().getString(R.string.com_auth0_domain)
        )
    }
    single<CoroutineScope> { CoroutineScope(SupervisorJob() + Dispatchers.IO) }

    val certificatePinner = CertificatePinner.Builder()
        .add(HOST_NAME_URL, CERTIFICATE_256)
        .build()

    single {
        OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
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

