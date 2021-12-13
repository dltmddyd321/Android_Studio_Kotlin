package com.example.carddemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private val titles = arrayOf("렘!","렘!","렘!","렘!","렘!","렘!","렘!","렘!")

    private val details = arrayOf("렘 좋아", "렘 너무 귀엽다", "렘 너무 이쁘다", "렘이 최고", "렘 섹시하다", "리제로 히로인", "렘 사랑해", "렘 팬클럽 가입")

    private val images = arrayOf(R.drawable.das, R.drawable.eee, R.drawable.ffgf, R.drawable.qqq, R.drawable.rem, R.drawable.remy, R.drawable.rmea, R.drawable.rem)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage : ImageView
        var itemTitle: TextView
        var itemDetail : TextView

        init {
            itemImage = itemView.findViewById(R.id.itemImg)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)

            itemView.setOnClickListener { v: View ->
                var position: Int = adapterPosition
                Snackbar.make(v, "Click!", Snackbar.LENGTH_LONG).setAction("Action",null).show()
            }
        }
    }
}