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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.displayhash.DisplayHashManager
import android.widget.Toast
import com.example.unsplash.utils.Constants
import com.example.unsplash.utils.Constants.TAG
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
                //스크롤바 초기 상태 지정
                mainScrollView.scrollTo(0, 200)
            } else {
                frameSearchBtn.visibility = View.INVISIBLE
            }
        }
    }
}