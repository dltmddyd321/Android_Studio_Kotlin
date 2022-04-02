package com.example.servicestylerx

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.servicestylerx.databinding.ActivityMainBinding
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var disposable : Disposable ?= null
    private val rxApiTask by lazy {
        RxApiTask.getApi()
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cntBtn.text = "0"

        cntBtn.setOnClickListener {
            cntBtn.isClickable = false

            Observable
                .interval(0, 1, TimeUnit.SECONDS)
                .subscribe {
                    runOnUiThread {
                        textView.text = it.toString()
                    }
                }
        }

        Timber.plant(Timber.DebugTree())
        beginSearch("cat")
        checkPeriod()
        MaintenanceApiTask.checkMaintenance()
    }

    @SuppressLint("CheckResult")
    private fun checkPeriod() {
        PeriodApiTask.getApi().getPeriod()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    if(result.isSuccessful) {
                        binding.textView3.text = result.body()?.limit_date
                    }
                }, { error ->
                    Timber.d("Error -> $error")
                }
            )
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