package com.example.stickyheader

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stickyheader.databinding.ViewItemOneBinding
import com.example.stickyheader.databinding.ViewItemTwoBinding

class StickyHeaderAdapter(private val items : List<Event>) : RecyclerView.Adapter<StickyHeaderAdapter.StickyHeaderViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = StickyHeaderViewHolder(
        ViewItemOneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(
        holder: StickyHeaderAdapter.StickyHeaderViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class StickyHeaderViewHolder(
        private val binding : ViewItemOneBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.title.text = event.value.toString()
            binding.date.text = "${event.date}"

            binding.date.visibility = if(event.isHeader) View.VISIBLE else View.GONE
        }
    }

    fun isHeader(position : Int) = items[position].isHeader

    fun getHeaderView(list : RecyclerView, position: Int) : View {
        val item = items[position]

        val binding = ViewItemTwoBinding.inflate(LayoutInflater.from(list.context), list, false)
        binding.date.text = "${item.date}"
        return binding.root
    }
}