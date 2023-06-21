package wat.pl

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
abstract class ArtDao {

    @Insert
    abstract suspend fun insertImage(art: Art)

    @Query("SELECT * FROM image_table")
    abstract fun getAllImages(): LiveData<List<Art>>

}