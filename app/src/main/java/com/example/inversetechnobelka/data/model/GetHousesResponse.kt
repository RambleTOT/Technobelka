package com.example.inversetechnobelka.data.model

import FacultyImageEntity
import com.google.gson.annotations.SerializedName

data class GetHousesResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("ourValues")
    val ourValues: String?,
    @SerializedName("usersCount")
    val usersCount: Int?,
    @SerializedName("image")
    val image: FacultyImageEntity?,
)