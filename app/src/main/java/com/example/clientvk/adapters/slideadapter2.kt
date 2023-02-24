package com.example.clientvk.adapters

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class slideadapter2(private val photos: ArrayList<String>) :
    RecyclerView.Adapter<slideadapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = CircleImageView(parent.context)
        // imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = layoutParams
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position+2 < photos.size) Picasso.get().load(photos[position+2]).into(holder.imageView)
        else Picasso.get().load("https://cojo.ru/wp-content/uploads/2022/12/belyi-priamougolnik-2.webp").into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}