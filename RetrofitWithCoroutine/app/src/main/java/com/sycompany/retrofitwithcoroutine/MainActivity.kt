package com.sycompany.retrofitwithcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.sycompany.retrofitwithcoroutine.databinding.ActivityMainBinding
import retrofit2.Response
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofitInstance: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        retrofitInstance = RetrofitInstance.getRetrofitInstance().create(AlbumService::class.java)

        binding.apply {
            btnGetAll.setOnClickListener {
                getAll()
            }
            btnGetPath.setOnClickListener {
                getResponsePathParameter()
            }
            btnGetSorted.setOnClickListener {
                getResponseQuery()
            }
            btnPost.setOnClickListener {
                uploadAlbum()
            }
        }
    }

    private fun getResponsePathParameter() {
        binding.textView.text = ""
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retrofitInstance.getPathedAlbum(5)
            emit(response)
        }

        pathResponse.observe(this) {
            val title = it.body()?.title
            binding.textView.text = title
        }
    }

    private fun getAll() {
        binding.textView.text = ""
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retrofitInstance.getAlbums()
            emit(response)


        }

        responseLiveData.observe(this) {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    val result = " "+"Album title : ${albumItem.title}"+"\n"+
                            " "+"Album id : ${albumItem.id}"+"\n"+
                            " "+"User id : ${albumItem.userId}"+"\n\n\n"
                    binding.textView.append(result)
                }
            }
        }
    }

    private fun getResponseQuery() {
        binding.textView.text = ""
        val responseLiveData: LiveData<Response<Album>> = liveData {
            val response = retrofitInstance.getSortedAlbums(1)
            emit(response)
        }

        responseLiveData.observe(this) {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumItem = albumList.next()
                    val result = " "+"Album title : ${albumItem.title}"+"\n"+
                            " "+"Album id : ${albumItem.id}"+"\n"+
                            " "+"User id : ${albumItem.userId}"+"\n\n\n"
                    binding.textView.append(result)
                }
            }
        }
    }

    private fun uploadAlbum() {
        binding.textView.text = ""
        val album = AlbumItem(7, "NEW TITLE", 7)
        val poseResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retrofitInstance.uploadAlbum(album)
            emit(response)
        }

        poseResponse.observe(this) {
            val receivedAlbumItem = it.body()
            val result = " "+"Album title : ${receivedAlbumItem?.title}"+"\n"+
                    " "+"Album id : ${receivedAlbumItem?.id}"+"\n"+
                    " "+"User id : ${receivedAlbumItem?.userId}"+"\n\n\n"
            binding.textView.append(result)
        }
    }
}