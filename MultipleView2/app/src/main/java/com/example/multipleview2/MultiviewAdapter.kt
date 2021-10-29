package com.example.multipleview2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.multipleview2.MultiMode.Companion.multi_type1
import com.example.multipleview2.MultiMode.Companion.multi_type2
import com.example.multipleview2.MultiMode.Companion.multi_type3

class MultiviewAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var dataList = mutableListOf<MultiData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View?
        return when(viewType) {
            multi_type1 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.firstlayout,parent,false
                )
                MultiViewHolder1(view)
            }

            multi_type2 -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.secondlayout,parent,false
                )
                MultiViewHolder2(view)
            }

            else -> {
                view = LayoutInflater.from(parent.context).inflate(
                    R.layout.thirdlayout,parent,false
                )
                MultiViewHolder3(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type) {
            multi_type1 -> {
                (holder as MultiViewHolder1).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_type2 -> {
                (holder as MultiViewHolder2).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            else -> {
                (holder as MultiViewHolder3).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }

    inner class MultiViewHolder1(view: View) : RecyclerView.ViewHolder(view) {
        private val textName: TextView = view.findViewById(R.id.nameText)
        private val textTag: TextView = view.findViewById(R.id.tagText)
        private val textInfo: TextView = view.findViewById(R.id.infoText)
        private val textRipple: TextView = view.findViewById(R.id.rippleText)

        fun bind(item: MultiData) {
            textName.text = item.firstName
            textTag.text = item.firstTag
            textInfo.text = item.firstInfo
            textRipple.text = item.ripple.toString()
        }
    }

    inner class MultiViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        private val firstImgView: ImageView = view.findViewById(R.id.firstDoctor)
        private val firstName: TextView = view.findViewById(R.id.firstDoctorName)
        private val firstJob: TextView = view.findViewById(R.id.firstDoctorJob)
        private val firstTag: TextView = view.findViewById(R.id.firstDoctorTag)
        private val firstHospital: TextView = view.findViewById(R.id.firstHospitalName)

        private val secondImgView: ImageView = view.findViewById(R.id.secondDoctor)
        private val secondName: TextView = view.findViewById(R.id.firstDoctorName)
        private val secondJob: TextView = view.findViewById(R.id.firstDoctorJob)
        private val secondTag: TextView = view.findViewById(R.id.firstDoctorTag)
        private val secondHospital: TextView = view.findViewById(R.id.firstHospitalName)

        fun bind(item: MultiData) {
            firstHospital.text = item.firstInfo
            secondHospital.text = item.secondInfo
            firstTag.text = item.firstTag
            secondTag.text = item.secondTag
            firstJob.text = item.firstJob
            secondJob.text = item.secondJob
            firstName.text = item.firstName
            secondName.text = item.secondName

            Glide.with(itemView)
                .load(item.firstImage)
                .into(firstImgView)

            Glide.with(itemView)
                .load(item.secondImage)
                .into(secondImgView)
        }
    }

    inner class MultiViewHolder3(view: View) : RecyclerView.ViewHolder(view) {
        private val firstHospitalName: TextView = view.findViewById(R.id.firstName)
        private val firstHospitalLocal: TextView = view.findViewById(R.id.firstHospitalLocal)
        private val firstHospitalImage: ImageView = view.findViewById(R.id.hospital_photo)

        private val secondHospitalName: TextView = view.findViewById(R.id.secondName)
        private val secondHospitalLocal: TextView = view.findViewById(R.id.secondHospitalLocal)
        private val secondHospitalImage: ImageView = view.findViewById(R.id.hospital_photo2)

        fun bind(item: MultiData) {
            firstHospitalName.text = item.firstName
            firstHospitalLocal.text = item.firstTag

            secondHospitalName.text = item.secondName
            secondHospitalLocal.text = item.secondTag

            Glide.with(itemView)
                .load(item.firstImage)
                .into(firstHospitalImage)

            Glide.with(itemView)
                .load(item.secondImage)
                .into(secondHospitalImage)
        }
    }
}