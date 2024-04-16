package com.example.inversetechnobelka.data.model

import LevelEntity
import com.google.gson.annotations.SerializedName
import java.util.logging.Level

data class GetEventsResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("expireAt")
    val expireAt: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("prize")
    val prize: Int?,
    @SerializedName("level")
    val level: LevelEntity,
    @SerializedName("image")
    val link: String?,
)