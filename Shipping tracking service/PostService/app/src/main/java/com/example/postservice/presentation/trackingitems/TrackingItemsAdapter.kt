package com.example.postservice.presentation.trackingitems

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postservice.databinding.ItemTrackingBinding
import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem

class TrackingItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data : List<Pair<TrackingItem, TrackingInformation>> = emptyList()
    var onClickListener : ((TrackingItem, TrackingInformation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val binding : ItemTrackingBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                data.getOrNull(adapterPosition)?.let { (item, information) ->
                    onClickListener?.invoke(item, information)
                }
            }
        }
    }
}