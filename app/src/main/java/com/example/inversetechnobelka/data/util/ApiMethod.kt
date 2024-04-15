package com.example.inversetechnobelka.data.util

import GetTokenResponse
import UserLoginEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiMethod {

    @POST("auth/login")
    fun loginUser(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

}