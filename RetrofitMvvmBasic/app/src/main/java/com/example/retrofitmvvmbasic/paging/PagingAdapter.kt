package com.example.retrofitmvvmbasic.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmvvmbasic.TestInfo
import com.example.retrofitmvvmbasic.databinding.LayoutItemBinding

class PagingAdapter : PagingDataAdapter<TestInfo, PagingAdapter.MyViewHolder>(IMAGE_COMPARATOR) {

    class MyViewHolder(private val binding : LayoutItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post : TestInfo) {
            binding.userIdText.text = post.myUserId.toString()
            binding.idText.text = post.id.toString()
            binding.titleText.text = post.title
            binding.bodyText.text = post.body
        }
    }

    override fun onBindViewHolder(holder: PagingAdapter.MyViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingAdapter.MyViewHolder {
        val binding = LayoutItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    /*
    areItemsTheSame -> 두 개체가 동일한 항목(ID)을 나타내는 지 확인하기 위해 호출 되고,
    areContentsTheSame -> 두 항목에 동일한 데이터가 있는지 확인하기 위해 호출 된다.
     */
    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<TestInfo>() {
            override fun areItemsTheSame(oldItem: TestInfo, newItem: TestInfo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TestInfo, newItem: TestInfo) =
                oldItem == newItem
        }
    }

}