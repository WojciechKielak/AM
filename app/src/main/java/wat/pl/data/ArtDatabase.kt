package wat.pl.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Image::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun imageDao(): ArtDao

    companion object {
        @Volatile
        private var INSTANCE: ArtDatabase? = null

        fun getDatabase(context: Context): ArtDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArtDatabase::class.java,
                    "image_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}