package com.example.servicestylerx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val rxApiTask by lazy {
        RxApiTask.getApi()
    }

    var disposable : Disposable ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        beginSearch("cat")
    }

    private fun beginSearch(srsearch : String) {
        disposable = rxApiTask.hitCountCheck("query", "json", "search", srsearch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Log.d("Result Hits", result.toString())
                    textView.text = result.query.searchinfo.totalhits.toString()
                    textView2.text = result.query.searchinfo.suggestion
                },
                { error ->
                    Log.d("Result Error", error.message.toString())
                }
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}