package com.example.grocercyservice

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryListAdapter(
    var list: List<GroceryItems>,
    val groceryItemClickListener: GroceryItemClickListener
) : RecyclerView.Adapter<GroceryListAdapter.GroceryViewHolder>(){

    inner class GroceryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.itemName)
        val quantity = itemView.findViewById<TextView>(R.id.itemQuatity)
        val rate = itemView.findViewById<TextView>(R.id.itemRate)
        val amount = itemView.findViewById<TextView>(R.id.itemRate)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.itemDeleteBtn)
    }

    interface GroceryItemClickListener {
        fun onItemClick(groceryItems: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_item_grocery, parent, false)
        return GroceryViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.name.text = list[position].itemName
        holder.quantity.text = list[position].itemQuantity.toString()
        holder.rate.text = "Rs. " + list[position].itemPrice.toString()
        val itemTotal = list[position].itemPrice * list[position].itemQuantity
        holder.amount.text = "Rs. $itemTotal"
        holder.deleteBtn.setOnClickListener {
            groceryItemClickListener.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int = list.size
}