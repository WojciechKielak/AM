package wat.pl

import androidx.lifecycle.LiveData

class ArtRepository (private val artDao: ArtDao) {

    val readAllData: LiveData<List<Art>> = artDao.getAllImages()

    suspend fun insertImage(art: Art){
        artDao.insertImage(art)
    }

}