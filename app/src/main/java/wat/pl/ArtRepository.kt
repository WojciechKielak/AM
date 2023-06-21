package wat.pl

import androidx.lifecycle.LiveData

class ArtRepository (private val artDao: ArtDao) {

    val readAllData: LiveData<List<Image>> = artDao.getAllImages()

    suspend fun insertImage(art: Image){
        artDao.insertImage(art)
    }

    suspend fun deleteImage(art: Image){
        artDao.deleteImage(art)
    }

}