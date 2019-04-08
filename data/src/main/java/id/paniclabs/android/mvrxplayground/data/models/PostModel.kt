package id.paniclabs.android.mvrxplayground.data.models

import com.google.gson.annotations.SerializedName

data class PostModel(
    @SerializedName("body")
    val body: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("userId")
    val userId: Int = 0
): BaseModel()