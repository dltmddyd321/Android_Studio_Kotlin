package com.sycompany.roomsave

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContractListAdapter(private val itemList : List<Contract>) : RecyclerView.Adapter<ContractListAdapter.ContractViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contract, parent, false)
        return ContractViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContractViewHolder, position: Int) {
        val item = itemList[position]

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
        holder.apply {
            bind(item)
        }
    }

    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }

    private lateinit var itemClickListener : OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun getItemCount(): Int = itemList.size

    inner class ContractViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val mName: TextView = itemView.findViewById(R.id.mName)
        private val mTel: TextView = itemView.findViewById(R.id.mTel)

        fun bind(item: Contract) {
            mName.text = item.name
            mTel.text = item.tel
        }
    }
}