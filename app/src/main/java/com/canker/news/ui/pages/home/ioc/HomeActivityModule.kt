@file:Suppress("unused")

package com.canker.news.ui.pages.home.ioc

import androidx.appcompat.app.AppCompatActivity
import com.canker.core.ioc.scopes.ActivityScope
import com.canker.news.ioc.modules.guardian.GuardianServiceModule
import com.canker.news.ioc.builder.FragmentBuilderModule
import com.canker.news.ioc.keys.ActivityViewModelKey
import com.canker.news.ui.pages.home.HomeActivity
import com.canker.news.ui.pages.home.viewmodel.HomeActivityViewModel
import com.canker.news.ui.base.activity.BaseActivityModule
import com.canker.core.ui.recyclerview.LayoutManager
import com.canker.news.ui.base.viewmodel.BaseActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module(includes = [BaseActivityModule::class, FragmentBuilderModule::class, GuardianServiceModule::class])
abstract class HomeActivityModule {

    @Binds
    @ActivityScope
    abstract fun bindActivity(activity: HomeActivity): AppCompatActivity

    @Binds
    @IntoMap
    @ActivityViewModelKey(HomeActivityViewModel::class)
    @ActivityScope
    abstract fun bindViewModel(activityViewModel: HomeActivityViewModel): BaseActivityViewModel

    @Module
    companion object {

        @Provides
        @ActivityScope
        @JvmStatic
        fun provideLayoutManager(): LayoutManager = LayoutManager()

    }


}
