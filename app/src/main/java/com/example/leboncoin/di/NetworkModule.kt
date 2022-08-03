package com.example.leboncoin.di

import android.app.Application
import com.example.data.api.LeboncoinApi
import com.example.leboncoin.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor( StethoInterceptor())
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): LeboncoinApi {
        return retrofit.create(LeboncoinApi::class.java)
    }

    @Provides
    @Singleton
    fun providePicassoInstance(app: Application): Picasso {

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor( StethoInterceptor())
            .addInterceptor {
                val newRequest: Request = it.request().newBuilder()
                    .addHeader("User-Agent", "leboncoin")
                    .build()
                it.proceed(newRequest)
            }.build()

        return Picasso.Builder(app.applicationContext)
            .downloader(OkHttp3Downloader(client))
            .build()
    }
}
