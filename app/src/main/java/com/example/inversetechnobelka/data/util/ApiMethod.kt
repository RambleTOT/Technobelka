package com.example.inversetechnobelka.data.util

import GetTokenResponse
import UserLoginEntity
import UserLoginPatchEntity
import com.example.inversetechnobelka.data.model.GetHousesResponse
import com.example.inversetechnobelka.data.model.GetMyAccountResponse
import com.example.inversetechnobelka.data.model.GetUserPathHouseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface ApiMethod {

    @POST("auth/login")
    fun loginUser(
        @Body body: UserLoginEntity
    ): Call<GetTokenResponse>

    @GET("houses")
    fun getHouses(
    ): Call<List<GetHousesResponse>>

    @PATCH("users")
    fun changeUserHouse(
        @Body body: UserLoginPatchEntity,
        @Header("Authorization") token: String
    ): Call<GetUserPathHouseResponse>

    @GET("users/me/")
    fun getMyAccount(
        @Header("Authorization") token: String
    ): Call<GetMyAccountResponse>

}