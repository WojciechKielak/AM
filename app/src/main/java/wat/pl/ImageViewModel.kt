package wat.pl

import androidx.lifecycle.ViewModel

private val image1 =
    Image(
        title = "q",
        author = "w",
        year = "1",
        imageUrl = "https://www.artic.edu/iiif/2/cf95a821-e48a-1d64-0375-47e7597fb197/full/843,/0/default.jpg"
//        image = R.drawable.obraz1
    )
private val image2 =
    Image(
        title = "a",
        author = "s",
        year = "2",
//        image = R.drawable.obraz2
        imageUrl = "https://www.artic.edu/iiif/2/cf95a821-e48a-1d64-0375-47e7597fb197/full/843,/0/default.jpg"
    )
private val image3 =
    Image(
        title = "z",
        author = "x",
        year = "3",
//        image = R.drawable.obraz3
        imageUrl = "https://www.artic.edu/iiif/2/cf95a821-e48a-1d64-0375-47e7597fb197/full/843,/0/default.jpg"
    )
//private var data = listOf(image1, image2, image3,image1, image2, image3)
private var data: List<Image> = listOf()
private var dataFav = listOf(image1, image2)

interface Server{
    fun loadData(): List<Image>
    fun loadDataFav(): List<Image>
    //fun addToDataFav()
    //fun removeFromDataFav()
}
class ImageViewModel : ViewModel(), Server{
    private var image : Image ?= null
    private var isFavorite = false

    fun saveDataFromApi(data2: List<Image>) {
        data = data2
    }
    override fun loadData(): List<Image> {
        return data
    }
    override fun loadDataFav(): List<Image> {
        return dataFav
    }
    fun addToDataFav(image: Image)  {
        if(!dataFav.contains(image)) dataFav += image

    }
    fun removeFromDataFav(image: Image) {
        if(dataFav.contains(image)) dataFav -= image

    }
    fun getImage() = this.image
    fun setImage(image: Image){
        this.image = image
    }

    fun getIsFavorite() = this.isFavorite
    fun setIsFavorite(isFavorite: Boolean){
        this.isFavorite = isFavorite
    }


}