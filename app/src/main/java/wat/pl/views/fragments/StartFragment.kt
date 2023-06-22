package wat.pl.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wat.pl.ImageViewModel
import wat.pl.R
import wat.pl.URL_obraz_cz1
import wat.pl.URL_obraz_cz2
import wat.pl.data.Image
import wat.pl.databinding.FragmentStartBinding
import wat.pl.remote.ApiClient
import wat.pl.remote.ArtResponse
import wat.pl.remote.RetrofitClient

class StartFragment : Fragment() {

    private val vm by activityViewModels<ImageViewModel>()
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (vm.loadData().isEmpty()) {
            getDataFromApi()
        } else {
            binding.progressBar.visibility = GONE
            binding.przegladajButton.isEnabled = true
            binding.ulubioneButton.isEnabled = true
        }
        binding.przegladajButton.setOnClickListener {
            vm.setIsFavorite(false)
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }
        binding.ulubioneButton.setOnClickListener {
            vm.setIsFavorite(true)
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }
    }

    private fun getDataFromApi() {
        val apiClient = RetrofitClient.client?.create(ApiClient::class.java)
        val call = apiClient?.getArt()
        call?.enqueue(object : Callback<ArtResponse> {
            override fun onResponse(call: Call<ArtResponse>, response: Response<ArtResponse>) {
                val output = response.body()
                if (output != null) {
                    val images = mutableListOf<Image>()
                    for (artResponse in output.data) {
                        val image = Image(
                            id = artResponse.id ?: 0,
                            title = artResponse.title ?: "Nazwa nieznana",
                            author = artResponse.artist_title ?: "Autor nieznany",
                            year = artResponse.date_end.toString() ?: "Rok nieznany",
                            imageUrl = URL_obraz_cz1 + artResponse.image_id + URL_obraz_cz2
                        )
                        images.add(image)
                    }
                    vm.saveDataFromApi(images)
                    binding.przegladajButton.isEnabled = true
                }
                binding.progressBar.visibility = GONE
                binding.ulubioneButton.isEnabled = true
            }

            override fun onFailure(call: Call<ArtResponse>, t: Throwable) {
                Toast.makeText(context, "Nieudane połączenie z api!", Toast.LENGTH_LONG).show()
                binding.progressBar.visibility = GONE
                binding.ulubioneButton.isEnabled = true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
