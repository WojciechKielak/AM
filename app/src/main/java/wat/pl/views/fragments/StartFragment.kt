package wat.pl.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
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
    private var _binding : FragmentStartBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromApi()
        binding.przegladajButton.setOnClickListener {
            vm.setIsFavorite(false)

//            getDataFromApi()
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }
        binding.ulubioneButton.setOnClickListener {
            vm.setIsFavorite(true)
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }

    }

    private fun getDataFromApi() {
        System.out.println("Pobieramy dane z API.")
        val output = ArrayList<ArtResponse>()
        val apiClient = RetrofitClient.client?.create(ApiClient::class.java)
        val call = apiClient?.getArt()
        call?.enqueue(object : Callback<ArtResponse> {
            override fun onResponse(call: Call<ArtResponse>, response: Response<ArtResponse>) {
//                view?.progressBar1?.visibility = View.GONE
                if (output.isEmpty()) {
//                    output.addAll(response.body())
//                    dbHelper.addCivs(response.body())
                }
//                civList = dbHelper.getCivList()
//                fillDropdown(root)

                Toast.makeText(context, "GGGGGGGGGGGG", Toast.LENGTH_LONG).show()
            }

//            override fun onFailure(call: Call<List<ArtResponse>>, t: Throwable?) {
////                view?.progressBar1?.visibility = View.GONE
//                Toast.makeText(context, "Error occured "+ t?.message, Toast.LENGTH_LONG).show()
//            }

            override fun onFailure(call: Call<ArtResponse>?, t: Throwable?) {
                Toast.makeText(context, "Error occured "+ t?.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

//    private fun getDataFromApi(root: View, dbHelper: DBHelper) {
//        System.out.println("Pobieramy dane z API.")
//        val output = ArrayList<ArtResponse>()
//        val apiClient = RetrofitClient.client?.create(ApiClient::class.java)
//        val call = apiClient?.getArt()
//        call?.enqueue(object : Callback<List<ArtResponse>> {
//            override fun onResponse(call: Call<List<ArtResponse>>, response: Response<List<ArtResponse>>) {
////                view?.progressBar1?.visibility = View.GONE
//                if (output.isEmpty()) {
//                    output.addAll(response.body())
////                    dbHelper.addCivs(response.body())
//                }
////                civList = dbHelper.getCivList()
////                fillDropdown(root)
//            }
//
//            override fun onFailure(call: Call<List<ArtResponse>>, t: Throwable?) {
////                view?.progressBar1?.visibility = View.GONE
//                Toast.makeText(context, "Error occured", Toast.LENGTH_LONG).show()
//            }
//        })
//    }

}