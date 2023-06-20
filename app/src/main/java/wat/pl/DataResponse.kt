package wat.pl

import com.google.gson.annotations.SerializedName

data class DataResponse (
    @SerializedName("title")
    val title : String,
    @SerializedName("image_id")
    val image_id : String,
    @SerializedName("date_end")
    val date_end : Int,
    @SerializedName("artist_title")
    val artist_title : String,
        )