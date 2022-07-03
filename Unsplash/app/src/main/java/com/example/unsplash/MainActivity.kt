package com.example.unsplash

import android.annotation.SuppressLint
import android.content.ComponentCallbacks
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Point
import android.hardware.display.DisplayManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.displayhash.DisplayHashManager
import android.widget.Toast
import com.example.unsplash.retrofit.RetrofitManager
import com.example.unsplash.utils.*
import com.example.unsplash.utils.Constants.TAG
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_btn_search.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.http.Query

class MainActivity : AppCompatActivity() {

    private val scope = MainScope()
    private var currentSearchType: SEARCH_TYPE = SEARCH_TYPE.PHOTO

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "")

        search_term_radio_group.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId) {
                R.id.photoSearchBtn -> {
                    Log.d(TAG, "사진 검색 버튼 클릭")
                    searchTextLayout.hint = "사진 검색"
                    searchTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_photo_library_24, resources.newTheme())
                }
                R.id.userSearchBtn -> {
                    Log.d(TAG, "사용자 검색 버튼 클릭")
                    searchTextLayout.hint = "사용자 검색"
                    searchTextLayout.startIconDrawable = resources.getDrawable(R.drawable.ic_baseline_person_24, resources.newTheme())
                }
            }
        }
        searchEditText.onMyTextChanged {
            if(it.toString().isNotEmpty()) {
                frameSearchBtn.visibility = View.VISIBLE
                searchTextLayout.helperText = " "
                //스크롤바 초기 상태 지정
                mainScrollView.scrollTo(0, 200)
            } else {
                frameSearchBtn.visibility = View.INVISIBLE
                searchTextLayout.helperText = "검색어를 입력해주세요."
            }
            if(it.toString().count() >= 12) {
                Toast.makeText(this, "검색어는 12자 까지만 입력 가능!", Toast.LENGTH_SHORT).show()
            }
        }

        btnSearch.setOnClickListener {

            this.handleSearchBtnUi()

            val userSearchInput = searchEditText.text.toString()

            RetrofitManager.instance.searchPhotos(searchTerm = searchEditText.text.toString(), completion = { responseState, responseDataArrayList ->
                when(responseState) {
                    RESPONSE_STATUS.OKAY -> {
                        val intent = Intent(this, PhotoCollectionActivity::class.java)

                        //직렬화로 데이터 저장
                        val bundle = Bundle()
                        bundle.putSerializable("photoArrayList", responseDataArrayList)

                        //파싱한 데이터와 입력한 검색어 값 넘기기
                        intent.putExtra("arrayBundle", bundle)
                        intent.putExtra("searchTerm", userSearchInput)

                        startActivity(intent)
                    }
                    RESPONSE_STATUS.FAIL -> {
                        Toast.makeText(this, "API 호출 에러!", Toast.LENGTH_SHORT).show()
                    }

                    RESPONSE_STATUS.NO_CONTENT -> {
                        Toast.makeText(this, "검색 결과 없음", Toast.LENGTH_SHORT).show()
                    }
                }
                btnProgress.visibility = View.INVISIBLE
                btnSearch.text = "검색"
                searchEditText.setText("")
            })
        }
    }

    private fun handleSearchBtnUi() {
        btnProgress.visibility = View.VISIBLE
        btnSearch.text = ""
    }
}