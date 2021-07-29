package com.example.realtime_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.realtime_kotlin.databinding.ActivityMainBinding
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import org.w3c.dom.Text
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding    // 뷰 바인딩
    val db = FirebaseFirestore.getInstance()    // Firestore 인스턴스 선언
    val itemList = arrayListOf<ListLayout>()    // 리스트 아이템 배열
    val adapter = ListAdapter(itemList)         // 리사이클러 뷰 어댑터

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvList.adapter = adapter

        binding.btnRead.setOnClickListener {
            db.collection("Contacts")   // 작업할 컬렉션
                .get()      // 문서 가져오기
                .addOnSuccessListener { result ->
                    // 성공할 경우
                    itemList.clear()
                    for (document in result) {  // 가져온 문서들은 result에 들어감
                        val item = ListLayout(document["img"] as String,document["name"] as String, document["number"] as String)
                        itemList.add(item)
                    }
                    adapter.notifyDataSetChanged()  // 리사이클러 뷰 갱신
                }
                .addOnFailureListener { exception ->
                    // 실패할 경우
                    Log.w("MainActivity", "Error getting documents: $exception")
                }
        }
    }

}