package com.example.postservice.presentation.trackingitems

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postservice.R
import com.example.postservice.databinding.ItemTrackingBinding
import com.example.postservice.entity.Level
import com.example.postservice.entity.ShippingCompany
import com.example.postservice.entity.TrackingInformation
import com.example.postservice.entity.TrackingItem
import java.util.*

class TrackingItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data : List<Pair<TrackingItem, TrackingInformation>> = emptyList()
    var onClickListener : ((TrackingItem, TrackingInformation) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemTrackingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val (item, trackingInformation) = data[position]

        (holder as ViewHolder).bind(item.company, trackingInformation)
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val binding : ItemTrackingBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                data.getOrNull(adapterPosition)?.let { (item, information) ->
                    onClickListener?.invoke(item, information)
                }
            }
        }

        @SuppressLint("ResourceAsColor")
        fun bind(company : ShippingCompany, information: TrackingInformation) {
            binding.updatedAtTextView.text =
                Date(information.lastDetail?.time ?: System.currentTimeMillis()).toString()

            binding.levelLabelTextView.text = information.level?.label

            when(information.level) {
                Level.COMPLETE -> {
                    binding.levelLabelTextView.setTextColor(org.koin.android.R.attr.colorPrimary)
                    binding.root.alpha = 0.5f
                }
                Level.PREPARE -> {
                    binding.levelLabelTextView.setTextColor(R.color.teal_200)
                    binding.root.alpha = 1f
                }
                else -> {
                    binding.levelLabelTextView.setTextColor(R.color.purple_200)
                    binding.root.alpha = 1f
                }
            }

            binding.invoiceTextView.text = information.invoiceNo

            if (information.itemName.isNullOrBlank()) {
                binding.itemNameTextView.text = "이름 없음"
                binding.itemNameTextView.setTextColor(androidx.appcompat.R.color.material_blue_grey_800)
            } else {
                binding.itemNameTextView.text = information.itemName
                binding.itemNameTextView.setTextColor(R.color.black)
            }

            binding.lastStateTextView.text = information.lastDetail?.let { it.kind + " @${it.where}" }
            binding.companyNameTextView.text = company.name
        }
    }
}