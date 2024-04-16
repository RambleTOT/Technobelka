import com.google.gson.annotations.SerializedName

data class LevelEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
)
