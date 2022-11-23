package com.sycompany.contactsconnection

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var mBtnLogin: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnLogin = findViewById(R.id.contactBtn)
        mBtnLogin?.setOnClickListener {
            getContacts()
        }
    }

    @SuppressLint("Range")
    private fun getContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)
        }
        val cursor = getBirthByContacts()
        val bDayColumn = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE) ?: 0
        val peopleName = cursor?.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME) ?: 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val bDay = cursor.getString(bDayColumn)
                val pName = cursor.getString(peopleName)
                Log.i("연락처 가져오기 테스트", "Contact Result: Birth $bDay Who $pName")
            }
        }
    }

    private fun getBirthByContacts(): Cursor? {
        val uri: Uri = ContactsContract.Data.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Event.START_DATE
        )

        val where = ContactsContract.Data.MIMETYPE + "= ? AND " +
                ContactsContract.CommonDataKinds.Event.TYPE + "=" +
                ContactsContract.CommonDataKinds.Event.TYPE_BIRTHDAY
        val selectionArgs = arrayOf(
            ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE
        )
        return contentResolver.query(uri, projection, where, selectionArgs, null)
    }
}