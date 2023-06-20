package wat.pl.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import wat.pl.Image
import wat.pl.databinding.RowBinding

class ImageAdapter(private val images: List<Image>,
                   private val onImageClick: (Image)-> Unit)
    : RecyclerView.Adapter<ImageAdapter.MyViewHolder>() {
    inner class MyViewHolder(binding: RowBinding): ViewHolder(binding.root){
        val image = binding.imageViewRow
        val title = binding.title
        init { binding.root.setOnClickListener{onImageClick(images[adapterPosition]) } }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder( binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(images[position].image)
        holder.title.text = images[position].author
    }
}