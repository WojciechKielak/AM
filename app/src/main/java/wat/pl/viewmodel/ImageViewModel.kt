package wat.pl

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wat.pl.data.ArtDatabase
import wat.pl.data.Image
import wat.pl.repository.ArtRepository

private var data: List<Image> = listOf()
private var dataFav: List<Image> = listOf()

interface Server {
    fun loadData(): List<Image>
    fun loadDataFav(): List<Image>
}

class ImageViewModel(application: Application) : AndroidViewModel(application), Server {
    private var image: Image? = null
    private var isFavorite = false

    fun saveDataFromApi(data2: List<Image>) {
        data = data2
    }

    override fun loadData(): List<Image> {
        return data
    }

    override fun loadDataFav(): List<Image> {
        return dataFav
    }

    fun getImage() = this.image
    fun setImage(image: Image) {
        this.image = image
    }

    fun getIsFavorite() = this.isFavorite
    fun setIsFavorite(isFavorite: Boolean) {
        this.isFavorite = isFavorite
    }

    val readAllData: LiveData<List<Image>>
    private val repository: ArtRepository

    init {
        val artDao = ArtDatabase.getDatabase(application).imageDao()
        repository = ArtRepository(artDao)
        readAllData = repository.readAllData

        readAllData.observeForever { images ->
            images?.let {
                dataFav = images.toList()
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            dataFav = repository.readAllData.value?.toList() ?: emptyList()
        }
    }

    fun addImage(art: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertImage(art)
        }
    }

    fun deleteImage(art: Image) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteImage(art)
        }
    }

}