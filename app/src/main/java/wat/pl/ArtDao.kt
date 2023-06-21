package wat.pl

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
abstract class ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertImage(art: Image)

    @Query("SELECT * FROM image_table")
    abstract fun getAllImages(): LiveData<List<Image>>

    @Delete
    abstract suspend fun deleteImage(image: Image)

}