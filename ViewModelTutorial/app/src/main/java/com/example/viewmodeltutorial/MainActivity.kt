package com.example.viewmodeltutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var numberViewModel: NumberViewModel

//    private val numberFactoryViewModel by lazy {
//        ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return NumberViewModel() as T
//            }
//        }).get(NumberViewModel::class.java)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberViewModel = ViewModelProvider(this)[NumberViewModel::class.java]

        numberViewModel.currentValue.observe(this, Observer {
            number_textView.text = it.toString()
        })

        plus.setOnClickListener(this)
        minus.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val userInput = user_input.text.toString().toInt()

        when(view) {
            plus ->
                numberViewModel.updateValue(ActionType.PLUS, userInput)
            minus ->
                numberViewModel.updateValue(ActionType.MINUS, userInput)
        }
    }
}