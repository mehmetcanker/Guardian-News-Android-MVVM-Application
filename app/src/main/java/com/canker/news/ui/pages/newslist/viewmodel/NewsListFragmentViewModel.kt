package com.canker.news.ui.pages.newslist.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.canker.core.extensions.toLiveData
import com.canker.core.ioc.scopes.FragmentScope
import com.canker.core.networking.DataFetchResult
import com.canker.data.response.search.SearchResponse
import com.canker.news.ui.base.viewmodel.BaseFragmentViewModel
import com.canker.news.ui.pages.newslist.repository.NewsListRepository
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Predicate
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@FragmentScope
class NewsListFragmentViewModel @Inject constructor(repository: NewsListRepository) : BaseFragmentViewModel() {


    private val _repository = repository;

    private val autoCompletePublishSubject = PublishRelay.create<String>()

    val newsListResultLiveData: LiveData<DataFetchResult<SearchResponse>> by lazy {
        _repository.searchDataResult.toLiveData(disposables)
    }

    fun getSearchResult(q: String? = null) {
        _repository.getHomeData(q)
    }

    @SuppressLint("CheckResult")
    fun configureSearch() {
        val subscribe = autoCompletePublishSubject
            .debounce(
                800,
                TimeUnit.MILLISECONDS
            )
            .distinctUntilChanged()
            //if string must not empty
            //.filter(Predicate {
            //    return@Predicate it.isNotEmpty()
            //})
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                getSearchResult(result)
            }, { t: Throwable? ->
                Timber.w(t, "Failed to get search results")
            })
    }

    fun onOmnibarInputStateChanged(query: String) {
        autoCompletePublishSubject.accept(query.trim())
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}