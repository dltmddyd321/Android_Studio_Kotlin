package com.example.diffutilbasic

import androidx.recyclerview.widget.DiffUtil

class MainDiffCallback: DiffUtil.ItemCallback<Monster>() {
    //두 객체를 비교하여 동일한 객체인지 확인
    override fun areItemsTheSame(oldItem: Monster, newItem: Monster): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Monster, newItem: Monster): Boolean {
        return oldItem == newItem
    }
}