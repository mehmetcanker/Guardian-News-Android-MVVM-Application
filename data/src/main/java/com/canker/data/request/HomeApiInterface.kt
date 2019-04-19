package com.canker.data.request

import io.reactivex.Single
import com.canker.data.base.Home
import com.canker.data.response.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiInterface {


    @GET("search")
    fun getSearch(@Query("q") q: String?, @Query("show-fields") showFields: String): Single<SearchResponse>

    @GET("search")
    fun getLatestNews(@Query("show-fields") showFields: String): Single<SearchResponse>
}