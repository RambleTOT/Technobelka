import com.example.inversetechnobelka.data.model.GetHousesResponse
import com.google.gson.annotations.SerializedName

data class GetTokenResponse(
    @SerializedName("accessToken")
    val token: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("house")
    val house: GetHousesResponse?,
)
