package com.movingroot.flowassignment.presentation.di.core

import com.movingroot.flowassignment.BuildConfig
import com.movingroot.flowassignment.data.api.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService = getRetrofit().create(ApiService::class.java)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(provideOkHttpClient(HeaderInterceptor()))
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    private fun provideOkHttpClient(interceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .run {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
                    )
                }
                addInterceptor(interceptor)
                build()
            }
    }

    private inner class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = with (chain) {
            proceed(
                request().newBuilder()
                    .header("Content-Type", "application/json; charset=utf-8")
                    .addHeader("X-Naver-Client-Id", BuildConfig.CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", BuildConfig.CLIENT_SECRET)
                    .build()
            )
        }
    }
}
