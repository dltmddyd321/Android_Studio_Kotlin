package com.example.jsonparsingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val serverSyncJsonString = """
        {
        	"err": 0,
        	"msg": "",
        	"connections": [
        		{
        			"id": 1,
        			"userId": 12459051,
        			"type": "GOOGLE_CALENDAR",
        			"email": "psycho1q2w@gmail.com",
        			"status": "Y",
        			"updatedAt": "2022-02-15T15:02:11",
        			"createdAt": "2022-02-15T15:02:11"
        		},
                {
        			"id": 2,
        			"userId": 12459051,
        			"type": "NAVER_CALENDAR",
        			"email": "dltmddyd321@naver.com",
        			"status": "Y",
        			"updatedAt": "2022-02-15T15:02:11",
        			"createdAt": "2022-02-15T15:02:11"
        		}
        	]
        } 
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val localJsonObject = JSONObject(serverSyncJsonString)
        val localJsonArray = localJsonObject.getJSONArray("connections")

        for(i in 0 until localJsonArray.length()) {
            val localObject = localJsonArray.getJSONObject(i)
            val id = localObject.getInt("id")
            val name = localObject.getString("type")
            val email = localObject.getString("email")

            Log.d("결과 : ", "$id $name $email")
        }

        val jsonString = assets.open("test.json").reader().readText()
        Log.d("jsonString", jsonString)

        val jsonObject = JSONObject(jsonString)
        Log.d("jsonObject", jsonObject.toString())

        val viewObject = jsonObject.getJSONObject("view")
        Log.d("viewObject", viewObject.toString())

        val mapObject = jsonObject.getJSONObject("modelMap")
        Log.d("mapObject", mapObject.toString())

        val array = mapObject.get("additionalProp1")
        Log.d("array", array.toString())

//        for(index in 0 until jsonArray.length()) {
//            val jsonObject = jsonArray.getJSONObject(index)
//            val id = jsonObject.getString("id")
//            val language = jsonObject.getString("language")
//
//            Log.d("jsonObject", jsonObject.toString())
//            Log.d("json_id_language", "$id $language")
//        }
    }
}