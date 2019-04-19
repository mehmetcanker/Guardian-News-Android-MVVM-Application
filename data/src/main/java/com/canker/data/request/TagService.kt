package com.canker.data.request

import com.canker.data.response.tag.TagResponse
import io.reactivex.Single
import retrofit2.http.GET

interface TagService {

  @GET("tags")
  fun getAllTags(): Single<TagResponse>
}