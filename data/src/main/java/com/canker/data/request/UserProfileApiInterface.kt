package com.canker.data.request

import io.reactivex.Single
import com.canker.data.base.UserProfile
import retrofit2.http.GET

interface UserProfileApiInterface {
    @GET("user_profile.json")
    fun userProfile(): Single<UserProfile>
}