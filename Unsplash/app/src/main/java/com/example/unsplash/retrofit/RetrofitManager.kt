package com.example.unsplash.retrofit

import com.example.unsplash.model.Photo
import com.example.unsplash.utils.API
import com.example.unsplash.utils.RESPONSE_STATUS
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class RetrofitManager {

    companion object {
        val instance = RetrofitManager()
    }

    //Retrofit Interface 가져오기
    private val httpCall : RetrofitInterface ?= RetrofitClient.getClient(API.BASE_URL)?.create(RetrofitInterface::class.java)

    fun searchPhotos(searchTerm : String?, completion: (RESPONSE_STATUS, ArrayList<Photo>?) -> Unit) {

        val term = searchTerm.let {
            it
        }?: ""

        val call = httpCall?.searchPhotos(searchTerm = term) ?: return

        call.enqueue(object : retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {

                when(response.code()) {
                    200 -> {

                        //값이 있다면 JsonObject 형식으로 가져오기
                        response.body()?.let {

                            var parsedPhotoDataArray = ArrayList<Photo>()
                            val body = it.asJsonObject
                            val results = body.getAsJsonArray("results")
                            val total = body.get("total").asInt

                            if(total == 0) {
                                completion(RESPONSE_STATUS.NO_CONTENT, null)
                            } else {
                                //하위의 Json Array Parsing
                                results.forEach { resultItem ->
                                    val resultItemObject = resultItem.asJsonObject
                                    val user = resultItemObject.get("user").asJsonObject
                                    val userName: String = user.get("username").asString
                                    val likesCount = resultItemObject.get("likes").asInt
                                    val thumbnailLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString
                                    val createdAt = resultItemObject.get("created_at").asString

                                    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                    val formatter = SimpleDateFormat("yyyy년\nMM월 dd일")
                                    val outputDateString = formatter.format(parser.parse(createdAt))

                                    val photoItem = Photo(author = userName,
                                        likesCount = likesCount, thumbnail = thumbnailLink, createdAt = createdAt)
                                    parsedPhotoDataArray.add(photoItem)
                                }
                                completion(RESPONSE_STATUS.OKAY, parsedPhotoDataArray)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {

                //에러에 대한 확인 전송
                completion(RESPONSE_STATUS.FAIL, null)
            }
        })
    }
}