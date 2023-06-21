package wat.pl

import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import java.security.Provider


@Database(entities = [Image::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun imageDao(): ArtDao

//    class Callback @Inject constructor(
//        private val database: Provider<ArtDatabase>,
//        @ApplicationScope private val appliactionScope: CoroutineScope
//    ) : RoomDatabase.Callback
}