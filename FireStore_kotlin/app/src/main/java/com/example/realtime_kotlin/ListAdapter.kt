package com.example.realtime_kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(val itemList: ArrayList<ListLayout>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(itemList[position].img)
            .into(holder.img)

        holder.name.text = itemList[position].name
        holder.number.text = itemList[position].number
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.list_tv_name)
        val number: TextView = itemView.findViewById(R.id.list_tv_number)
        val img: ImageView = itemView.findViewById(R.id.list_tv_img)
    }
}