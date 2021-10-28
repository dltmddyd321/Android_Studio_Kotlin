package com.example.multipleview2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multipleview2.MultiMode.Companion.multi_type1
import com.example.multipleview2.MultiMode.Companion.multi_type2
import com.example.multipleview2.MultiMode.Companion.multi_type3
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    lateinit var multiAdapter: MultiviewAdapter
    private val dataList = mutableListOf<MultiData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        multiAdapter = MultiviewAdapter(this)
        rv_profile.adapter = multiAdapter
        rv_profile.addItemDecoration(VerticalItemDecorator(10))
        rv_profile.addItemDecoration(HorizontalItemDecorator(10))

        val superAsset = resources.assets
        val ins = superAsset.open("data.json")
        var str = ins.bufferedReader().use { it.readText() }

        try {
            val pageMap = JSONObject(str).getJSONObject("pageMap")
            val pageNumber = pageMap.getString("pageNumber")

            val expertList = JSONObject(str).getJSONArray("expertList")
            val jsonExpertList = expertList.getJSONObject(0)
            val profileImagePath = jsonExpertList.getString("profileImagePath")

            val jsonExpertList2 = expertList.getJSONObject(2)
            val profileImagePath2 = jsonExpertList2.getString("profileImagePath")

            dataList.apply {
                add(MultiData(image = profileImagePath, name = "json", age = pageNumber, multi_type3))
                add(MultiData(image = profileImagePath2, name = "jenny", age = pageNumber, multi_type2))
                add(MultiData(image = profileImagePath, name = "jhon", age = pageNumber, multi_type1))
                add(MultiData(image = profileImagePath2, name = "ruby", age = pageNumber, multi_type2))
                add(MultiData(image = profileImagePath, name = "yuna", age = pageNumber, multi_type3))

                multiAdapter.dataList = dataList
                multiAdapter.notifyDataSetChanged()
            }

        } catch (e : Exception) {
            e.printStackTrace()
        }
        ins.close()
    }
}