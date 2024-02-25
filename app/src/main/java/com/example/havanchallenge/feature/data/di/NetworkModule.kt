package com.example.havanchallenge.feature.data.di

import com.example.havanchallenge.BuildConfig
import com.example.havanchallenge.feature.data.remote.MakeUpApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val TIME_OUT_SECONDS = 15L
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ) : OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun provideGson() : Gson =
        GsonBuilder()
            .setLenient()
            .create()


    @Provides
    fun provideGsonConverterFactory(
        gson: Gson
    ) : GsonConverterFactory =
        GsonConverterFactory.create(gson)


    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory : GsonConverterFactory
    ) : MakeUpApi =
        Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://makeup-api.herokuapp.com/")
            .client(client)
            .build()
            .create(MakeUpApi::class.java)
}