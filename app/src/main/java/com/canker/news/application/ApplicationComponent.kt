package com.canker.news.application

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<Application> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}