package com.example.multipleview2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multipleview2.MultiMode.Companion.multi_type1
import com.example.multipleview2.MultiMode.Companion.multi_type2
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
        //Adapter 연결 및 View 간격 지정
        multiAdapter = MultiviewAdapter(this)
        rv_profile.adapter = multiAdapter
        rv_profile.addItemDecoration(VerticalItemDecorator(10))
        rv_profile.addItemDecoration(HorizontalItemDecorator(10))

        //Asset 폴더에 담긴 JSON 데이터를 호출하여 읽기
        val superAsset = resources.assets
        val ins = superAsset.open("data.json")
        var str = ins.bufferedReader().use { it.readText() }

        try {
            //최상위 JSON Object 선언
            val consultList = JSONObject(str).getJSONArray("consultList")

            //JSON의 전체 데이터를 순회하여 값 가져오기
            for(i in 0..consultList.length()) {
                val jsonConsultList = consultList.getJSONObject(i)
                val title = jsonConsultList.getString("title")
                val context = jsonConsultList.getString("context")
                val answerCnt = jsonConsultList.getInt("answerCnt")
                val tagList = jsonConsultList.getJSONArray("tagList")
                val tagText1 = tagList.getJSONObject(0)
                val tag1 = tagText1.getString("name")

                dataList.apply {
                    //MultiData에 담긴 형식대로 JSON 데이터를 등록하고 View에 반영
                    add(MultiData(
                        firstImage = "",
                        firstName = title, firstJob ="", firstTag = "#$tag1", firstInfo = context
                        , secondImage = "",
                        secondName = "", secondJob ="", secondTag = "", secondInfo = "", ripple = "답변 $answerCnt", type = multi_type1))
                }

                val expertList = JSONObject(str).getJSONArray("expertList")

                for(j in 0..expertList.length()) {
                    val jsonExpertList = expertList.getJSONObject(j)
                    val profileImagePath = jsonExpertList.getString("profileImagePath")
                    val name = jsonExpertList.getString("name")
                    val typeName = jsonExpertList.getString("typeName")
                    val companyName = jsonExpertList.getString("companyName")
                    val manList = jsonExpertList.getJSONArray("tagList")
                    val manIndex = manList.getJSONObject(0)
                    val manTag = manIndex.getString("name")

                    dataList.apply {
                        add(MultiData(
                            firstImage = profileImagePath,
                            firstName = name, firstJob =typeName, firstTag = "#$manTag", firstInfo = companyName
                            , secondImage = profileImagePath,
                            secondName = name, secondJob =typeName, secondTag = "#$manTag", secondInfo = companyName, ripple = "", type = multi_type2))

                        multiAdapter.dataList = dataList
                        multiAdapter.notifyDataSetChanged()
                    }
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
        ins.close()
    }
}