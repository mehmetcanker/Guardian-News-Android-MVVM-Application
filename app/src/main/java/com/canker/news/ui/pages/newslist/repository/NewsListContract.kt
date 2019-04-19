package com.canker.news.ui.pages.newslist.repository

import com.canker.core.networking.DataFetchResult
import com.canker.data.response.search.SearchResponse
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

interface NewsListContract {

    interface Repository {
        val searchDataResult: PublishSubject<DataFetchResult<SearchResponse>>
        fun getHomeData(q: String?)
        fun <T> handleError(result: PublishSubject<DataFetchResult<T>>, error: Throwable)
    }

    interface Remote {
        fun getSearchData(q: String?): Single<SearchResponse>

    }
}
