import com.google.gson.annotations.SerializedName

data class UserLoginPatchEntity(
    @SerializedName("houseId")
    val houseId: Int?,
)
