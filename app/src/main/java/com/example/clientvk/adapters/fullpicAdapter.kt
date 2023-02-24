package com.example.clientvk.adapters

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class fullpicAdapter(private val photos: ArrayList<String>) :
    RecyclerView.Adapter<fullpicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        val layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.layoutParams = layoutParams
        return ViewHolder(imageView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(photos[position]).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}
