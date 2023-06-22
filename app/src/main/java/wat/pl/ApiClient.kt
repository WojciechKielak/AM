package wat.pl

import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET(URL_continue)
    fun getArt(): Call<ArtResponse>

}