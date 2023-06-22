package wat.pl.repository

import androidx.lifecycle.LiveData
import wat.pl.data.ArtDao
import wat.pl.data.Image

class ArtRepository(private val artDao: ArtDao) {

    val readAllData: LiveData<List<Image>> = artDao.getAllImages()

    suspend fun insertImage(art: Image) {
        artDao.insertImage(art)
    }

    suspend fun deleteImage(art: Image) {
        artDao.deleteImage(art)
    }

}