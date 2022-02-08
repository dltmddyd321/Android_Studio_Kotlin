package com.example.retrofitpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder : Retrofit.Builder = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())

        val retrofit : Retrofit = builder.build()

        val client : GitHubClient = retrofit.create(GitHubClient::class.java)

        val call : Call<List<GitHubRepo>> = client.getRepos("dltmddyd321")

        //비동기 처리
        call.enqueue(object : Callback<List<GitHubRepo>> {
            override fun onResponse(
                call: Call<List<GitHubRepo>>,
                response: Response<List<GitHubRepo>>
            ) {
                val repos : List<GitHubRepo>? = response.body()
                var reposStr = ""

                repos?.forEach {
                    reposStr += "$it\n"
                }
                textView.text = reposStr
            }
            override fun onFailure(call: Call<List<GitHubRepo>>, t: Throwable) {}
        })
    }
}