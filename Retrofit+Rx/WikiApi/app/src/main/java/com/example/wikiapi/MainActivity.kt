package com.example.wikiapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.wikiapi.gitrepoapi.GithubApi
import com.example.wikiapi.gitrepoapi.GithubResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.internal.notifyAll

class MainActivity : AppCompatActivity() {

    private val wikiApiService by lazy {
        WikiApiService.create()
    }
    var disposable : Disposable? = null
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        compositeDisposable.add(GithubApi.getRepoList("test")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .subscribe({ response : GithubResponseModel ->
                for(item in response.items) {
                    Log.d("MainActivity", item.name)
                }
            }, { error : Throwable ->
                Log.d("MainActivity", error.localizedMessage)
            }))

//        beginSearch2("cat")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    private fun beginSearch2(srsearch: String) {
        disposable =
            wikiApiService.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(result.query.searchInfo.totalHits) },
                    { error -> showError(error.message!!) }
                )
    }

    private fun beginSearch(srsearch : String) {
            disposable = wikiApiService.hitCountWithResponseCode("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result ->
                        if(result.isSuccessful) {
                            val res : WikiModels.Result = result.body()?:WikiModels.Result(WikiModels.Query(WikiModels.SearchInfo(-1)))
                            result.body()?.let {
                                showResult(res.query.searchInfo.totalHits)
                            }
                            Log.i("Test : ", "success" + result.code())
                        } else {
                            Log.i("TEST : ", "failed" + result.code())
                        }
                    },
                    { error ->
                        showError(error.message!!)
                    }
                )
    }

    private  fun showResult(totalHits: Int){
        Log.d("Result hits: ", totalHits.toString())
    }

    private fun showError(message: String){
        Log.d("Error Msg:", message)
    }

    override fun onPause() {
        super.onPause()
//        disposable?.dispose()
    }
}