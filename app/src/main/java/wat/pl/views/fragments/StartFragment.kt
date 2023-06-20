package wat.pl.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import wat.pl.ImageViewModel
import wat.pl.R
import wat.pl.databinding.FragmentStartBinding

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
        binding.przegladajButton.setOnClickListener {
            vm.setIsFavorite(false)
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }
        binding.ulubioneButton.setOnClickListener {
            vm.setIsFavorite(true)
            findNavController().navigate(R.id.action_startFragment_to_imageListFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}