package com.example.inversetechnobelka.data.model

import LevelEntity
import com.google.gson.annotations.SerializedName

data class GetCurrentEventsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expireAt")
    val expireAt: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("level")
    val level: LevelEntity?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("prize")
    val prize: Int?,
    @SerializedName("link")
    val link: String?,
)