package com.example.clientvk.adapters

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class slideadapter(private val photos: ArrayList<String>) :
    RecyclerView.Adapter<slideadapter.ViewHolder>() {

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
        if (position+1 < photos.size) Picasso.get().load(photos[position+1]).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return photos.size-1
    }

    inner class ViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}