package wat.pl.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class Image(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    val title: String,
    val author: String,
    val year: String,
    val imageUrl: String
)
