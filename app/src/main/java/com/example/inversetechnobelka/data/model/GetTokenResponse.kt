import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("accessToken")
    val token: String?,
    @SerializedName("phone")
    val phone: String?,
)
