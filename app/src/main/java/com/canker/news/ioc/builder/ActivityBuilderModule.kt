package com.canker.news.ioc.builder

import com.canker.core.ioc.scopes.ActivityScope
import com.canker.news.ui.pages.home.HomeActivity
import com.canker.news.ui.pages.home.ioc.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeActivityModule::class])
    internal abstract fun bindSplashActivity(): HomeActivity
}