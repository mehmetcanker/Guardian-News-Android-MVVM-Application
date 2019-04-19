package com.canker.news.ioc.modules.guardian

import com.canker.core.ioc.scopes.ActivityScope
import com.canker.data.request.HomeApiInterface
import com.canker.data.request.SearchApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class GuardianServiceModule {

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideGuardianSearchInterface(retrofit: Retrofit): SearchApiInterface =
            retrofit.create(SearchApiInterface::class.java)
    }
}
