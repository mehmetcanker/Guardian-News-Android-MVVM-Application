package com.canker.core.testing

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okio.Okio
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object DependencyProvider {

    fun getRetrofit(baseUrl: HttpUrl): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClient.Builder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS).build())
                .build()
    }



    @SuppressLint("NewApi")
    fun getResponseFromJson(fileName: String): String {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName.json")
        val source = Okio.buffer(Okio.source(inputStream))
        return source.readString(StandardCharsets.UTF_8)
    }
}
