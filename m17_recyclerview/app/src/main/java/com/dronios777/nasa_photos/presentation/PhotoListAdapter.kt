package com.dronios777.nasa_photos.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dronios777.nasa_photos.R
import com.dronios777.nasa_photos.data.FotoModel
import com.dronios777.nasa_photos.databinding.PhotoItemBinding

class PhotoListAdapter(
    private val onClick: (FotoModel) -> Unit
) : ListAdapter<FotoModel, PhotoListAdapter.PhotoViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {

        return PhotoViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {

            sol.text = "${date.resources.getString(R.string.sol_text)}  ${item.sol}"
            date.text = "${date.resources.getString(R.string.date_text)}  ${item.earth_date}"

            item?.let {
                Glide
                    .with(photoMars.context)
                    .load(it.img_src)
                    .placeholder(R.drawable.load1)
                    .into(photoMars)
            }
        }
        holder.binding.root.setOnClickListener {
            item?.let {
                onClick(item)
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<FotoModel>() {

        //метод вызывается, чтобы проверить, что два разных объекта описывают один и тот же элемент из набора данных
        override fun areItemsTheSame(oldItem: FotoModel, newItem: FotoModel): Boolean =
            oldItem.img_src == newItem.img_src

        //метод вызывается, чтобы проверить у 2-х разных объектах одни и теже данные
        override fun areContentsTheSame(oldItem: FotoModel, newItem: FotoModel): Boolean =
            oldItem == newItem
    }

    class PhotoViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)
}