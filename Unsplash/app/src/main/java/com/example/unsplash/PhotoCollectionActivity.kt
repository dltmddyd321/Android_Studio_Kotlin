package com.example.unsplash

import android.app.SearchManager
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.text.InputFilter
import android.util.Log
import android.view.Menu
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.unsplash.model.Photo
import com.example.unsplash.recyclerview.PhotoGridRecyclerviewAdapter
import kotlinx.android.synthetic.main.activity_photo_collection.*

class PhotoCollectionActivity: AppCompatActivity(), SearchView.OnQueryTextListener{

    private var photoList = ArrayList<Photo>()
    private lateinit var photoGridRecyclerviewAdapter: PhotoGridRecyclerviewAdapter
    private lateinit var mySearchView: SearchView
    private lateinit var mySearchViewEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_collection)

        val bundle = intent.getBundleExtra("arrayBundle")
        val searchTerm = intent.getStringExtra("searchTerm")

        photoList = bundle?.getSerializable("photoArrayList") as ArrayList<Photo>

        top_app_bar.title = searchTerm

        //액티비티에서 어떤 액션바를 사용할지 설정
        setSupportActionBar(top_app_bar)

        this.photoGridRecyclerviewAdapter = PhotoGridRecyclerviewAdapter()
        this.photoGridRecyclerviewAdapter.submitList(photoList)
        photoRecyclerview.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        photoRecyclerview.adapter = this.photoGridRecyclerviewAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        this.mySearchView = menu?.findItem(R.id.searchMenuItem)?.actionView as SearchView
        this.mySearchView.apply {
            this.queryHint = "검색어를 입력해주세요."
            this.setOnQueryTextListener(this@PhotoCollectionActivity)
            this.setOnQueryTextFocusChangeListener { _, hasExpanded ->
                when (hasExpanded) {
                    true -> {

                    }
                    false -> {

                    }
                }
            }
            mySearchViewEditText = this.findViewById(androidx.appcompat.R.id.search_src_text)
        }
        this.mySearchViewEditText.apply {
            //12자까지 제한
            this.filters = arrayOf(InputFilter.LengthFilter(12))
            this.setTextColor(Color.WHITE)
            this.setHintTextColor(Color.WHITE)
        }
        return true
    }

    //SearchView 검색어 입력 이벤트
    override fun onQueryTextSubmit(query: String?): Boolean {

        if(!query.isNullOrEmpty()) {
            this.top_app_bar.title = query

            //TODO: API 호출
        }
//        this.mySearchView.setQuery("", false)
//        this.mySearchView.clearFocus()
        this.top_app_bar.collapseActionView()

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        val userInputText = newText ?: ""

        if(userInputText.count() == 12) {
            Toast.makeText(this, "검색어는 12자까지만 입력 가능!", Toast.LENGTH_SHORT).show()
        }

        return true
    }
}