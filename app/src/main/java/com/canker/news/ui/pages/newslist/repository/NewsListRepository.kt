package com.canker.news.ui.pages.newslist.repository

import com.canker.core.extensions.*
import com.canker.core.ioc.scopes.FragmentScope
import com.canker.core.networking.DataFetchResult
import com.canker.core.networking.Scheduler
import com.canker.data.response.search.SearchResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

@FragmentScope
class NewsListRepository(
    private val remote: NewsListContract.Remote,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : NewsListContract.Repository {

    override val searchDataResult: PublishSubject<DataFetchResult<SearchResponse>> =
        PublishSubject.create<DataFetchResult<SearchResponse>>()

    override fun getHomeData(q: String?) {
        searchDataResult.loading(true)
        remote.getSearchData(q)
            .performOnBackOutOnMain(scheduler)
            .subscribe({
                searchDataResult.success(it)
            }, { error -> handleError(searchDataResult, error) })
            .addTo(compositeDisposable)
    }

    override fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        result.failed(error)
        Timber.e(error.localizedMessage)
    }
}
