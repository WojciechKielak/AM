package wat.pl.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import wat.pl.ImageViewModel
import wat.pl.R
import wat.pl.adapters.ImageAdapter
import wat.pl.data.Image
import wat.pl.databinding.FragmentImageListBinding

class ImageListFragment : Fragment() {

    private val vm by activityViewModels<ImageViewModel>()
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!
    private var img: List<Image>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (vm.getIsFavorite()) {
            img = vm.loadDataFav()
            binding.listaPodpis.text = "Wybierz dzieło sztuki z ulubionych"
        } else {
            img = vm.loadData()
            binding.listaPodpis.text = "Wybierz dzieło sztuki"
        }
        val adapter = ImageAdapter(
            images = img!!,
            onImageClick = { image ->
                vm.setImage(image)
                findNavController().navigate(R.id.action_imageListFragment_to_detailFragment)
            }
        )
        binding.recycleView.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}