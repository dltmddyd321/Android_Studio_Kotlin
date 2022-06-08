package com.sycompany.roomsave

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity() {

    val TAG = "DB TEST"
    var db : AppDatabase? = null
    var contactsList = mutableListOf<Contract>()
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPlusButton: Button

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.mRecyclerView)
        mPlusButton = findViewById(R.id.mPlusButton)
        db = AppDatabase.getInstance(this)

        //이전에 저장한 내용 모두 불러와서 추가하기
        CoroutineScope(Dispatchers.IO).launch {
            val savedContacts = db?.contractDao()?.getAll()
            if(!savedContacts.isNullOrEmpty()){
                contactsList.addAll(savedContacts)
            }
        }

        val adapter = ContractListAdapter(contactsList)
        adapter.setItemClickListener(object : ContractListAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {

                CoroutineScope(Dispatchers.IO).launch {
                    val contacts = contactsList[position]

                    db?.contractDao()?.delete(contacts = contacts) //DB에서 삭제
                    contactsList.removeAt(position) //리스트에서 삭제
                    adapter.notifyDataSetChanged() //리스트뷰 갱신

                    Log.d(TAG, "remove item($position). name:${contacts.name}")
                }
            }
        })
        mRecyclerView.adapter = adapter

        mPlusButton.setOnClickListener {

            //랜덤 번호 만들기
            val random = Random()
            val numA = random.nextInt(1000)
            val numB = random.nextInt(10000)
            val numC = random.nextInt(10000)
            val rndNumber = String.format("%03d-%04d-%04d",numA,numB,numC)

            val contact = Contract(0, "New $numA", rndNumber) //Contacts 생성
            CoroutineScope(Dispatchers.IO).launch {
                db?.contractDao()?.insertAll(contact) //DB에 추가
                withContext(CoroutineScope(Dispatchers.Main).coroutineContext) {
                    contactsList.add(contact) //리스트 추가
                    adapter.notifyDataSetChanged() //리스트뷰 갱신
                }
            }
        }
    }
}