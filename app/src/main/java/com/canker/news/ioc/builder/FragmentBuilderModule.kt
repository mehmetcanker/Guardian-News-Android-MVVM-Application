package com.canker.news.ioc.builder

import com.canker.core.ioc.scopes.FragmentScope
import com.canker.news.ui.pages.newsdetail.NewsDetailFragment
import com.canker.news.ui.pages.newsdetail.ioc.NewsDetailFragmentModule
import com.canker.news.ui.pages.newslist.NewsListFragment
import com.canker.news.ui.pages.newslist.ioc.NewsListFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [NewsListFragmentModule::class])
    @FragmentScope
    abstract fun contributeNewsListFragment(): NewsListFragment

    @ContributesAndroidInjector(modules = [NewsDetailFragmentModule::class])
    @FragmentScope
    abstract fun contributeNewsDetailFragment(): NewsDetailFragment

}