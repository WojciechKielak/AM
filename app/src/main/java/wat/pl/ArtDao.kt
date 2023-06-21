package wat.pl

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
abstract class ArtDao {

    @Insert
    abstract suspend fun insert(image: Image)

    @Query("SELECT * FROM tabelka")
    abstract suspend fun getAllImages(): List<Image>

}