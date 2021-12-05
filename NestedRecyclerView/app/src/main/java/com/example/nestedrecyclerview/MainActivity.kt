package com.example.nestedrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.adapter.MainRecyclerAdapter
import com.example.nestedrecyclerview.model.AllCategory
import com.example.nestedrecyclerview.model.CategoryItem

class MainActivity : AppCompatActivity() {

    private var mainCategoryRecycler : RecyclerView? = null
    private var mainRecyclerAdapter : MainRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val categoryItemList : MutableList<CategoryItem> = ArrayList()
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))
        categoryItemList.add(CategoryItem(1, R.drawable.ic_launcher_foreground))

        val categoryItemList2 : MutableList<CategoryItem> = ArrayList()
        categoryItemList2.add(CategoryItem(1, R.drawable.random))
        categoryItemList2.add(CategoryItem(1, R.drawable.random))
        categoryItemList2.add(CategoryItem(1, R.drawable.random))
        categoryItemList2.add(CategoryItem(1, R.drawable.random))
        categoryItemList2.add(CategoryItem(1, R.drawable.random))
        categoryItemList2.add(CategoryItem(1, R.drawable.random))

        val categoryItemList3 : MutableList<CategoryItem> = ArrayList()
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))
        categoryItemList3.add(CategoryItem(1, R.drawable.girl))

        val allCategory : MutableList<AllCategory> = ArrayList()
        allCategory.add(AllCategory("HollyWood",categoryItemList))
        allCategory.add(AllCategory("Best of Oscars",categoryItemList2))
        allCategory.add(AllCategory("Movies Blue Dragon",categoryItemList3))

        setMainCategoryRecycler(allCategory)
    }

    private fun setMainCategoryRecycler(allCategory: List<AllCategory>) {
        mainCategoryRecycler = findViewById(R.id.rootRecyclerView)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        
        mainCategoryRecycler!!.layoutManager = layoutManager
        mainRecyclerAdapter = MainRecyclerAdapter(this, allCategory)
        mainCategoryRecycler!!.adapter = mainRecyclerAdapter
    }
}