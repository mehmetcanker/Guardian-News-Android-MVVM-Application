package com.canker.news.ui.pages.newslist.repository

import com.canker.core.ioc.scopes.FragmentScope
import com.canker.data.request.HomeApiInterface
import com.canker.data.request.SearchApiInterface
import com.canker.data.response.search.SearchResponse
import com.canker.news.ui.pages.newslist.repository.NewsListRemoteData.APIConstants.DEFAULT_SEARCH_PARAMETER
import io.reactivex.Single

@FragmentScope
class NewsListRemoteData(
    private val searchService: SearchApiInterface
) : NewsListContract.Remote {

    object APIConstants {
        const val DEFAULT_SEARCH_PARAMETER = "thumbnail,body"
    }

    override fun getSearchData(q: String?): Single<SearchResponse> =
        searchService.getLatestNews(q, DEFAULT_SEARCH_PARAMETER)
}
