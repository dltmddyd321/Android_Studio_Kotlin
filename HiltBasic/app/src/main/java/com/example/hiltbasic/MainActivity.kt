package com.example.hiltbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import com.example.hiltbasic.market.BookMarket
import com.example.hiltbasic.market.ClothingMarket
import com.example.hiltbasic.market.Market
import com.example.hiltbasic.module.BookMarketQualifier
import com.example.hiltbasic.module.ClothingMarketQualifier
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

//    @Inject
//    lateinit var test : String

    @BookMarketQualifier
    @Inject
    lateinit var bookMarket: Market

    @ClothingMarketQualifier
    @Inject
    lateinit var clothingMarket: Market

    private val viewModel: ApiViewModel by viewModels()
    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.resultText)

        viewModel.getData().observe(this) { data ->
            textView.text = data
        }

        bookMarket.open()
        clothingMarket.open()
//
//        Log.d(TAG, "result : $test ")
    }
}