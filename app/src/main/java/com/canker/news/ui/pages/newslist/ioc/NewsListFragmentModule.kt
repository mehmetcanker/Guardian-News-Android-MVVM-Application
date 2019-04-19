package com.canker.news.ui.pages.newslist.ioc

import android.content.Context
import androidx.fragment.app.Fragment
import com.canker.core.ioc.qualifiers.ApplicationContext
import com.canker.core.ioc.scopes.FragmentScope
import com.canker.core.networking.Scheduler
import com.canker.data.request.SearchApiInterface
import com.canker.news.ioc.keys.FragmentViewModelKey
import com.canker.news.ui.base.fragment.BaseViewModelFragmentModule
import com.canker.news.ui.base.viewmodel.BaseFragmentViewModel
import com.canker.news.ui.pages.newslist.NewsListAdapter
import com.canker.news.ui.pages.newslist.NewsListFragment
import com.canker.news.ui.pages.newslist.repository.NewsListRemoteData
import com.canker.news.ui.pages.newslist.repository.NewsListRepository
import com.canker.news.ui.pages.newslist.viewmodel.NewsListFragmentViewModel
import com.squareup.picasso.Picasso
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [BaseViewModelFragmentModule::class])
abstract class NewsListFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindFragment(fragment: NewsListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentViewModelKey(NewsListFragmentViewModel::class)
    @FragmentScope
    abstract fun bindViewModel(
        viewModel: NewsListFragmentViewModel
    ): BaseFragmentViewModel

    @Module
    companion object {

        @Provides
        @FragmentScope
        @JvmStatic
        fun adapter(picasso: Picasso, @ApplicationContext context: Context): NewsListAdapter =
            NewsListAdapter(context, picasso)

        @Provides
        @FragmentScope
        @JvmStatic
        fun newsListRemote(searchApiInterface: SearchApiInterface): NewsListRemoteData =
            NewsListRemoteData(searchApiInterface)

        @Provides
        @FragmentScope
        @JvmStatic
        fun newsListRepo(
            remote: NewsListRemoteData,
            scheduler: Scheduler,
            compositeDisposable: CompositeDisposable
        ): NewsListRepository =
            NewsListRepository(remote, scheduler, compositeDisposable)
    }
}
