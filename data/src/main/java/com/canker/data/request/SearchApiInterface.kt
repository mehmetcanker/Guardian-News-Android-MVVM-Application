package com.canker.data.request

import com.canker.data.response.search.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiInterface {

    @GET("search")
    fun getLatestNews(@Query("q") q: String?, @Query("show-fields") showFields: String): Single<SearchResponse>

}