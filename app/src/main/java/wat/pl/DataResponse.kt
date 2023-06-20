package wat.pl

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("id")
    val id : Int,
    @SerializedName("title")
    val title : String,
    @SerializedName("image_id")
    val image_id : String,
        )