package com.example.inversetechnobelka.data.model

import com.google.gson.annotations.SerializedName

data class GetMyAccountResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("login")
    val login: String?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("house")
    val house: GetHousesResponse?,

)