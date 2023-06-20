package wat.pl

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("/api/v1/artworks")
    fun getArt(): Call<List<ArtResponse>>

}