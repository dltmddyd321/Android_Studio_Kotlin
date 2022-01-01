package com.example.rxtest

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import io.reactivex.MaybeObserver
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    private val TAG = "RxAndroid AsyncTask"
//    val mMyAsyncTask = MyAsyncTask()
    var mTextView : TextView ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextView = findViewById(R.id.textView)
        initRxAsync()
//        initAndroidAsync()
    }

    @SuppressLint("CheckResult")
    fun initRxAsync() {
        Observable.just("Hello", "Rx", "World")
            .reduce{ x, y ->
                "$x $y"
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ s ->
                mTextView!!.text = s
            }, { e ->
                e.message?.let { Log.e(TAG, it) }
            }, {
                Log.i(TAG, "done")
            })
    }

//    fun initAndroidAsync() {
//        mMyAsyncTask.execute("Hello","Async","World")
//    }

    fun getObserver() : MaybeObserver<String> {
        return object : MaybeObserver<String> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onSuccess(t: String) {
                mTextView!!.text = t
            }

            override fun onError(e: Throwable) {
                e.message?.let { Log.e(TAG, it) }
            }

            @SuppressLint("LongLogTag")
            override fun onComplete() {
                Log.i(TAG, "done")
            }

        }
    }

//    inner class MyAsyncTask : AsyncTask<String, Void, String> () {
//        override fun doInBackground(vararg p0: String?): String {
//            val word = StringBuilder()
//            for(s in p0) {
//                word.append(s).append("")
//            }
//            return word.toString()
//        }
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            mTextView!!.text = result
//        }
//    }
}