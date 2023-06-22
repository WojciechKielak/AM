package wat.pl.remote

import retrofit2.Call
import retrofit2.http.GET
import wat.pl.URL_continue

interface ApiClient {
    @GET(URL_continue)
    fun getArt(): Call<ArtResponse>

}