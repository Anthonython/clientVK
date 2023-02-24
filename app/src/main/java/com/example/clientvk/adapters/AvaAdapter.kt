package com.example.clientvk.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clientvk.R
import com.example.clientvk.models.AvaModel
import com.example.clientvk.views.AvaView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class AvaAdapter(val listen: AvaView): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mAvaList: ArrayList<AvaModel> = ArrayList()
    var sourseList: ArrayList<AvaModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemview = layoutInflater.inflate(R.layout.activity_ava_recyc, parent, false)

        return AvaViewHolder(itemview)
    }

    override fun getItemCount(): Int {
       return mAvaList.count() }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
          if (holder is AvaViewHolder){
              holder.init(mAvaList[position], listen)
          }
    }

    fun loadUsers(AvaLst: ArrayList<AvaModel>){
        sourseList.clear()
        sourseList.addAll(AvaLst)
        filter("")
    }

    fun filter(query: String) {
        mAvaList.clear()
        sourseList.forEach {
           if (it.name.contains(query, true) || it.surname.contains(query, true)) {
               mAvaList.addAll(listOf(it))
           }
           notifyDataSetChanged()
       }
    }

    class AvaViewHolder(Item_V: View): RecyclerView.ViewHolder(Item_V){
        private var avaCircle: CircleImageView = itemView.findViewById(R.id.avatarCirlc)
        private var nameusertext: TextView = itemView.findViewById(R.id.name)
        private var isOnlineCirc: CircleImageView = itemView.findViewById(R.id.isOnlineCirc)
        private var avaButton: FrameLayout = itemView.findViewById(R.id.buttAva)

        @SuppressLint("SetTextI18n", "ResourceAsColor")
        fun init (AvaModel: AvaModel, listen: AvaView){
            Picasso.get().load(AvaModel.avatar).into(avaCircle)
            nameusertext.text = "${AvaModel.name} ${AvaModel.surname}"

            if (AvaModel.isOnline == 1)  isOnlineCirc.visibility = View.VISIBLE
            else isOnlineCirc.visibility = View.INVISIBLE

            avaButton.setOnClickListener {
             listen.ClckFava(AvaModel)
            }
        }
    }
}