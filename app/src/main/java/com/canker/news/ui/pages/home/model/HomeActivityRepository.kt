package com.canker.news.ui.pages.home.model

import com.canker.core.extensions.failed
import com.canker.core.networking.DataFetchResult
import com.canker.core.networking.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

class HomeActivityRepository(
    private val local: HomeActivityContract.Local,
    private val scheduler: Scheduler,
    private val compositeDisposable: CompositeDisposable
) : HomeActivityContract.Repository {

    override fun <T> handleError(dataFetchResult: PublishSubject<DataFetchResult<T>>, error: Throwable) {
        dataFetchResult.failed(error)
        Timber.e(error.localizedMessage)
    }
}
