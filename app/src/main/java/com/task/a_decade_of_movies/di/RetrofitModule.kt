package com.task.a_decade_of_movies.di

import com.task.a_decade_of_movies.core.Api
import com.task.a_decade_of_movies.core.AppPreferences
import com.task.a_decade_of_movies.core.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {



    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(
//                Interceptor { chain ->
//                    val builder = chain.request().newBuilder()
//                    val language = AppPreferences.getLocale()
//                    builder.header("Accept-Language", language)
//                    return@Interceptor chain.proceed(builder.build())
//                }
//            )
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            //.addConverterFactory(.create(moshi))
            .addConverterFactory(GsonConverterFactory.create())
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun providesIntegrateApi( retrofit: Retrofit): Api = retrofit.create(Api::class.java)

}