package wat.pl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabelka")
data class Art(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String,
    val author: String,
    val year: String,
    val imageUrl: String
)