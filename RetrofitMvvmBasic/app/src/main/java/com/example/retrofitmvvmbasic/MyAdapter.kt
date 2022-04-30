package com.example.retrofitmvvmbasic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmvvmbasic.databinding.LayoutItemBinding

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<TestInfo>()
    class MyViewHolder(val binding: LayoutItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.binding.userIdText.text = myList[position].myUserId.toString()
        holder.binding.idText.text = myList[position].id.toString()
        holder.binding.titleText.text = myList[position].title
        holder.binding.bodyText.text = myList[position].body
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    // 데이터 변경시 리스트 다시 할당
    fun setData(newList : List<TestInfo>){
        myList = newList
        // 새로고침
        notifyDataSetChanged()
    }
}