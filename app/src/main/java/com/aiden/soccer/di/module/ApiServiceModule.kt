package com.aiden.soccer.di.module

import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Aiden ( hai Le Thanh )
 * aiden9xx@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient().newBuilder()
        client.addInterceptor(interceptor)

        return Retrofit.Builder()
            .baseUrl(ApiService.API_URL)
            .client(client.build())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(ApiService::class.java)
    }
}
