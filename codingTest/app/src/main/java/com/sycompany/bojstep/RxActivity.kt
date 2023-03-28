package com.sycompany.bojstep

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.util.concurrent.TimeUnit

class RxActivity : AppCompatActivity() {

    private var disposable: Disposable? = null
    private lateinit var editText : EditText
    private lateinit var valueText : TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx)

        editText = findViewById(R.id.editText)
        valueText = findViewById(R.id.valueText)

        val editTextChangeObservable = editText.textChanges()

        disposable = editTextChangeObservable
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    valueText.text = it.toString()
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}