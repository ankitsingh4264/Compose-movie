package com.example.featuresapp.network.di

import com.example.featuresapp.network.API_KEY
import com.example.featuresapp.network.ApiService
import com.example.featuresapp.network.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url

            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor,interceptorHttp: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(interceptorHttp)
            .build()
    }

    @Provides
    fun provideGsonConvert(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    fun provideAppService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }



}