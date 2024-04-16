import com.google.gson.annotations.SerializedName

data class FacultyImageEntity(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("mimetype")
    val mimetype: String?,
    @SerializedName("url")
    val url: String?,
)
