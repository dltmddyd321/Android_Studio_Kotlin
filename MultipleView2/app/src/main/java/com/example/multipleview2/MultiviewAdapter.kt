package com.example.multipleview2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.multipleview2.MultiMode.Companion.multi_type1
import com.example.multipleview2.MultiMode.Companion.multi_type2
import com.example.multipleview2.MultiMode.Companion.multi_type3

class MultiviewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = mutableListOf<MultiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.firstlayout,parent,false
                )
                MultiViewHolder1(view)
            }

            multi_type2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.secondlayout,parent,false
                )
                MultiViewHolder2(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.thirdlayout,parent,false
                )
                MultiViewHolder3(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type) {
            multi_type1 -> {
                (holder as MultiViewHolder1).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_type2 -> {
                (holder as MultiViewHolder2).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder3).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    inner class MultiViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.tv_rv_name)
        private val textAge: TextView = view.findViewById(R.id.tv_rv_age)
        private val imgView: ImageView = view.findViewById(R.id.img_rv_photo)

        fun bind(item: MultiData) {
            textName.text = item.name
            textAge.text = item.age
            Glide.with(itemView)
                .load(item.image)
                .into(imgView)
        }
    }

    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.tv_rv_name)
        private val textAge: TextView = view.findViewById(R.id.tv_rv_age)
        private val imgView: ImageView = view.findViewById(R.id.img_rv_photo)

        fun bind(item: MultiData) {
            textName.text = item.name
            textAge.text = item.age
            Glide.with(itemView)
                .load(item.image)
                .into(imgView)
        }
    }

    inner class MultiViewHolder3(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.tv_rv_name)
        private val imgView: ImageView = view.findViewById(R.id.img_rv_photo)

        fun bind(item: MultiData) {
            textName.text = item.name
            Glide.with(itemView)
                .load(item.image)
                .into(imgView)
        }
    }
}