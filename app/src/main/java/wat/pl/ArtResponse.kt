package wat.pl
import com.google.gson.annotations.SerializedName

data class ArtResponse (
    @SerializedName("data")
    val data : List<DataResponse>,
)