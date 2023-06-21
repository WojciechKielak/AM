package wat.pl

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Image(
    val id: Int,
    val title: String,
    val author: String,
    val year: String,
    val imageUrl: String
)
