package com.example.servicestylerx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicestylerx.databinding.ActivityMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var disposable : Disposable ?= null
    private val rxApiTask by lazy {
        RxApiTask.getApi()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.plant(Timber.DebugTree())
        beginSearch("cat")
    }

    private fun beginSearch(srsearch : String) {
        val options : HashMap<String, String> = HashMap()
        options["list"] = "search"
        options["srsearch"] = srsearch

        disposable = rxApiTask.hitCountCheck("query", "json", options)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    Timber.d("Result -> $result")
                    binding.textView.text = result.query.searchinfo.totalhits.toString()
                    binding.textView2.text = result.query.searchinfo.suggestion
                }, { error ->
                    Timber.d("Error -> $error")
                }
            )
//        disposable = rxApiTask.hitCountCheck("query", "json", "search", srsearch)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { result ->
//                    Timber.d("Result -> $result")
//                    binding.textView.text = result.query.searchinfo.totalhits.toString()
//                    binding.textView2.text = result.query.searchinfo.suggestion
//                },
//                { error ->
//                    Timber.d("Error -> $error")
//                }
//            )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}