package com.example.diffutilbasic

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilbasic.databinding.LayoutViewholderBinding
import com.google.android.material.snackbar.Snackbar

class MainViewHolder(private val binding: LayoutViewholderBinding): RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(data: Monster) {
        with(binding) {
            tvName.text = "Name: ${data.name}"
            tvRace.text = "Race: ${data.race}"
            tvLevel.text = "Level: ${data.level}"
            tvStats.text = "HP: ${data.stats[0]} / MP: ${data.stats[1]} / EXP: ${data.stats[2]}"
            tvEncount.text = "Encounted: ${data.encount}"

            layoutViewHolder.setOnClickListener {
                Snackbar.make(it, "Item $layoutPosition touched!", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    fun setAlpha(alpha: Float) {
        with(binding) {
            tvName.alpha = alpha
            tvRace.alpha = alpha
            tvLevel.alpha = alpha
            tvStats.alpha = alpha
            tvEncount.alpha = alpha
        }
    }
}