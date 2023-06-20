package wat.pl

import androidx.annotation.DrawableRes

data class Image(
    val title: String,
    val author: String,
    val year: String,
    @DrawableRes val image: Int
)
