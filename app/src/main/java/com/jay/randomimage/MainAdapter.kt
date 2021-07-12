package com.jay.randomimage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jay.randomimage.databinding.ItemImageBinding
import com.jay.randomimage.model.ImageResponse

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val imageList: MutableList<ImageResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.parent(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ViewHolder(
        private val binding: ItemImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageResponse) {
            Glide.with(binding.root)
                .load(image.urls?.regular)
                .into(binding.ivImage)

            Glide.with(binding.root)
                .load(image.user?.profileImageUrls?.small)
                .into(binding.ivProfile)

            binding.tvName.text = image.user?.name ?: "이름없음"
        }

        companion object Factory {
            fun parent(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ItemImageBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(view)
            }
        }
    }

    fun addItems(images: List<ImageResponse>) {
        this.imageList.addAll(images)
        notifyDataSetChanged()
    }
}