package com.sycompany.moshibasic

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.sycompany.moshibasic.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(AdviceService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val adviceService = retrofit.create(AdviceService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val advice = adviceService.getAdviceSuspend()
            withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
                binding?.textView?.text = advice.slip.advice
            }
        }

//        adviceService.getAdvice().enqueue(object : Callback<Advice> {
//            override fun onResponse(call: Call<Advice>, response: Response<Advice>) {
//                val advice = response.body()?.slip?.advice
//                binding?.textView?.text = advice
//            }
//
//            override fun onFailure(call: Call<Advice>, t: Throwable) {
//                Log.v("retrofit", "call failed")
//            }
//        })
    }
}