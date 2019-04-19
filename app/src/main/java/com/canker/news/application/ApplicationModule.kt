@file:Suppress("unused")

package com.canker.news.application

import android.app.Application
import android.content.Context
import com.canker.core.ioc.modules.*
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.canker.news.ioc.builder.ActivityBuilderModule
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class, ImageModule::class, SystemServiceModule::class,
        TimberModule::class, UtilsModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModule::class]
)
abstract class ApplicationModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: com.canker.news.application.Application): Application

    @Binds
    @Singleton
    @ApplicationContext
    abstract fun bindApplicationContext(application: Application): Context

}