package com.dronios777.nasa_photos.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dronios777.nasa_photos.R.*
import com.dronios777.nasa_photos.R.string.*
import com.dronios777.nasa_photos.data.FotoModel
import com.dronios777.nasa_photos.databinding.PhotoItemBinding


class PhotoAdapter:RecyclerView.Adapter<PhotoViewHolder1>() {
    private var data: List<FotoModel> = emptyList()

    fun setData(data1: List<FotoModel>) {
        data = data1
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder1 {


        return PhotoViewHolder1(
            com.dronios777.nasa_photos.databinding.PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhotoViewHolder1, position: Int) {


        val item = data.getOrNull(position)

        with(holder.binding) {

            if (item != null) {
                sol.text = "${date.resources.getString(sol_text)}  ${item.sol}"
                date.text = "${date.resources.getString(date_text)}  ${item.earth_date}"

                item.let {
                    Glide
                        .with(photoMars.context)
                        .load(it.img_src)
                        .placeholder(drawable.load1)
                        .into(photoMars)
                }
            } else {
                ""
            }
        }

    }
}

class PhotoViewHolder1(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)