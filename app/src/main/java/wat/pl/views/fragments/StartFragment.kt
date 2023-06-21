package wat.pl.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import wat.pl.*
import wat.pl.databinding.FragmentStartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartFragment : Fragment() {

    private val vm by activityViewModels<ImageViewModel> ()
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
        }else {
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
        System.out.println("Pobieramy dane z API.")
        val apiClient = RetrofitClient.client?.create(ApiClient::class.java)
        val call = apiClient?.getArt()
        call?.enqueue(object : Callback<ArtResponse> {
            override fun onResponse(call: Call<ArtResponse>, response: Response<ArtResponse>) {
                val output = response.body()
                if (output != null) {
                    val images = mutableListOf<Image>()
                    for (artResponse in output.data) {
                        val image = Image(
                            id =artResponse.id ?: 0 ,
                            title = artResponse.title ?: "Nazwa nieznana",
                            author = artResponse.artist_title ?: "Autor nieznany",
                            year = artResponse.date_end.toString() ?: "Rok nieznany",
//                            image = artResponse.image_id
//                            image = R.drawable.obraz1
                            imageUrl = "https://www.artic.edu/iiif/2/"+artResponse.image_id+"/full/843,/0/default.jpg"
                        )
                        images.add(image)
                    }
                    vm.saveDataFromApi(images)
//                    Toast.makeText(context, "GGGGGGGGGGGG $output", Toast.LENGTH_LONG).show()
                    binding.przegladajButton.isEnabled = true
                }
                binding.progressBar.visibility = GONE
                binding.ulubioneButton.isEnabled = true
            }

            override fun onFailure(call: Call<ArtResponse>, t: Throwable) {
                Toast.makeText(context, "Error occurred: ${t.message}", Toast.LENGTH_LONG).show()
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
