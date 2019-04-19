package com.canker.core.ioc.modules

import android.content.Context
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun providesOkhttp3Downloader(okHttpClient: OkHttpClient): OkHttp3Downloader {
        return OkHttp3Downloader(okHttpClient)
    }

    @Provides
    @Singleton
    fun providesPicasso(@ApplicationContext context: Context, okHttp3Downloader: OkHttp3Downloader): Picasso {
        return Picasso.Builder(context)
            .downloader(okHttp3Downloader)
            .listener { _, _, exception ->
                Timber.e("Picasso Error: ")
                exception?.let {
                    Timber.e(exception.localizedMessage)
                }
            }
            .build()
    }
}
