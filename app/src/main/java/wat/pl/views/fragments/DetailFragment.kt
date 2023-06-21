package wat.pl.views.fragments

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
import java.lang.System.currentTimeMillis

class DetailFragment : Fragment(), SensorEventListener {

    private val vm by activityViewModels<ImageViewModel> ()
    private var _binding : FragmentDetailBinding?= null
    private val binding get() = _binding!!
    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var lastShakeTime: Long = 0
    private val SHAKE_DELAY = 3000 // Opóźnienie między kolejnymi potrząśnięciami (w milisekundach)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        bindImageData(vm.getImage())
        binding.favoriteButton.setOnClickListener {
            if(vm.loadDataFav().contains(vm.getImage())) {
//                vm.removeFromDataFav(vm.getImage()!!)
                vm.deleteImage(vm.getImage()!!)
                Toast.makeText(requireContext(),"Obraz usunięty z ulubionych",Toast.LENGTH_SHORT)
                    .show()
                binding.favoriteButton.text = "Dodaj do Ulubionych"
            } else{
//                vm.addToDataFav(vm.getImage()!!)
                vm.addImage(vm.getImage()!!)
                Toast.makeText(requireContext(),"Obraz dodany do ulubionych",Toast.LENGTH_SHORT)
                    .show()
                binding.favoriteButton.text = "Usuń z Ulubionych"
            }
//            bindImageData(vm.getImage())
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

    // Metoda wywoływana, gdy sensor zostanie zmieniony
    override fun onSensorChanged(event: SensorEvent?) {
        // Sprawdzenie, czy sensor to akcelerometr
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            // Pobranie wartości przyspieszenia w trzech osiach
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            // Obliczenie siły przyspieszenia
            val acceleration = Math.sqrt((x * x + y * y + z * z).toDouble())

            // Określenie progu potrząśnięcia
            val shakeThreshold = 12
//            Log.d("Shake", "MOC: $acceleration")
//            Log.d("Shake", "X: $x    Y: $y    Z: $z")

            // Jeżeli siła przyspieszenia przekroczy próg, potrząśnięcie zostanie wykryte
            if (currentTimeMillis() - lastShakeTime > SHAKE_DELAY && acceleration > shakeThreshold) {
                // Wykonaj dodatkowe operacje po potrząśnięciu telefonem

                if(vm.loadDataFav().contains(vm.getImage())) {
//                vm.removeFromDataFav(vm.getImage()!!)
                    vm.deleteImage(vm.getImage()!!)
                    Toast.makeText(requireContext(),"Obraz usunięty z ulubionych",Toast.LENGTH_SHORT)
                        .show()
                    binding.favoriteButton.text = "Dodaj do Ulubionych"
                } else{
//                vm.addToDataFav(vm.getImage()!!)
                    vm.addImage(vm.getImage()!!)
                    Toast.makeText(requireContext(),"Obraz dodany do ulubionych",Toast.LENGTH_SHORT)
                        .show()
                    binding.favoriteButton.text = "Usuń z Ulubionych"
                }

                // Zaktualizuj czas ostatniego potrząśnięcia
                lastShakeTime = currentTimeMillis()
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

        // Unregister the sensor listener
        sensorManager.unregisterListener(this)
    }

}