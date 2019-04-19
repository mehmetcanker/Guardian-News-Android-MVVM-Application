package com.canker.core.ioc.modules

import android.content.Context
import com.canker.core.constants.AppConstants
import com.canker.core.constants.AppConstants.API_KEY_NAME
import com.canker.core.constants.AppConstants.MAX_MEMORY_CACHE
import com.canker.core.constants.AppConstants.NETWORK_TIMEOUT
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.canker.core.networking.AppScheduler
import com.canker.core.networking.Scheduler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun scheduler(): Scheduler {
        return AppScheduler()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient, gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.API_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url().newBuilder()
                .addQueryParameter(API_KEY_NAME, AppConstants.API_KEY)
                .build()
            val request = chain.request()
                .newBuilder().url(url).build()
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        interceptor: Interceptor,
        cache: Cache
    ): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .addInterceptor(logInterceptor)
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        return client.build()
    }

    @Provides
    @Singleton
    fun providesOkhttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, MAX_MEMORY_CACHE)
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

}
