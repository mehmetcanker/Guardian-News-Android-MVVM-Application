package com.canker.news.ui.pages.newsdetail.ioc

import androidx.fragment.app.Fragment
import com.canker.core.ioc.scopes.FragmentScope
import com.canker.news.ioc.keys.FragmentViewModelKey
import com.canker.news.ui.base.fragment.BaseViewModelFragmentModule
import com.canker.news.ui.base.viewmodel.BaseFragmentViewModel
import com.canker.news.ui.pages.newsdetail.NewsDetailFragment
import com.canker.news.ui.pages.newsdetail.viewmodel.NewsDetailFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class NewsDetailFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: NewsDetailFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(NewsDetailFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(viewModel: NewsDetailFragmentViewModel): BaseFragmentViewModel

}
