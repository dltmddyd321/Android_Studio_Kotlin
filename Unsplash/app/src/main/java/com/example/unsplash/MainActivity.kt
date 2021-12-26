package com.example.unsplash

import android.annotation.SuppressLint
import android.content.ComponentCallbacks
import android.content.Context
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
import com.example.unsplash.utils.Constants
import com.example.unsplash.utils.Constants.TAG
import com.example.unsplash.utils.RESPONSE_STATE
import com.example.unsplash.utils.SEARCH_TYPE
import com.example.unsplash.utils.onMyTextChanged
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
            if(it.toString().count() > 0) {
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

            RetrofitManager.instance.searchPhotos(searchTerm = searchEditText.toString(), completion = { responseState, responseBody ->
                when(responseState) {
                    RESPONSE_STATE.OKAY -> {

                    }
                    RESPONSE_STATE.FAIL -> {
                        Toast.makeText(this, "API 호출 에러!", Toast.LENGTH_SHORT).show()
                    }
                }
            })
            this.handleSearchBtnUi()
        }
    }

    private fun handleSearchBtnUi() {
        btnProgress.visibility = View.VISIBLE
        btnSearch.text = ""

        Handler().postDelayed({
            btnProgress.visibility = View.INVISIBLE
            btnSearch.text = "검색"
        }, 1500)
    }
}