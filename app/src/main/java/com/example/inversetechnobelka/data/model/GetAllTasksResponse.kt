package com.example.inversetechnobelka.data.model

import FacultyImageEntity
import TypeTaskEntity
import com.google.gson.annotations.SerializedName

data class GetAllTasksResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expireAt")
    val expireAt: String?,
    @SerializedName("timeUntil")
    val timeUntil: String?,
    @SerializedName("prize")
    val prize: Int?,
    @SerializedName("type")
    val type: TypeTaskEntity?,
)