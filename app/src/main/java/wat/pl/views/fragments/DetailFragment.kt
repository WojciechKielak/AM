package wat.pl.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.squareup.picasso.Picasso
import wat.pl.Image
import wat.pl.ImageViewModel
import wat.pl.databinding.FragmentDetailBinding
import android.util.Log

class DetailFragment : Fragment() {

    private val vm by activityViewModels<ImageViewModel> ()
    private var _binding : FragmentDetailBinding?= null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindImageData(vm.getImage())
        binding.favoriteButton.setOnClickListener {
            if(vm.loadDataFav().contains(vm.getImage())) {
                vm.removeFromDataFav(vm.getImage()!!)
                Toast.makeText(requireContext(),"Obraz usunięty z ulubionych",Toast.LENGTH_SHORT)
                    .show()
            } else{
                vm.addToDataFav(vm.getImage()!!)
                Toast.makeText(requireContext(),"Obraz dodany do ulubionych",Toast.LENGTH_SHORT)
                    .show()
            }
            bindImageData(vm.getImage())
        }

    }

    private fun bindImageData(image: Image?) {
        image ?: return
//        binding.imageView.setImageResource(image.image)
        Picasso.get()
            .load(image.imageUrl)
            .fit()
            .into(binding.imageView)
        Log.d("DetailFragment", "Bind image data: $image")
        binding.title.text = image.title
        binding.author.text = image.author
        binding.year.text = image.year
        if(vm.loadDataFav().contains(image))binding.favoriteButton.text = "Usuń z Ulubionych"
        else binding.favoriteButton.text = "Dodaj do Ulubionych"
        //if(vm.getIsFavorite()) binding.favoriteButton.text = "Usuń z Ulubionych"
        //else binding.favoriteButton.text = "Dodaj do Ulubionych"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}