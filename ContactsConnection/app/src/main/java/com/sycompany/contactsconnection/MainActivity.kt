package com.sycompany.contactsconnection

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
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
import java.util.*


class MainActivity : AppCompatActivity() {

    private var mBtnLogin: Button? = null
    private var gBtn: Button? = null

    private var lookupId: String = ""
    private var contactIdKey: Long = 0L

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnLogin = findViewById(R.id.contactBtn)
        gBtn = findViewById(R.id.contactGoBtn)
        mBtnLogin?.setOnClickListener {
            getContacts()
        }



        val uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode("content://com.android.contacts/display_photo/4"))



        val phoneUri = ContactsContract.Contacts.getLookupUri(54600, "0r6666-271C73DB188FB3.847r12337-271C73DB188FB3")

        gBtn?.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.withAppendedPath(
//                ContactsContract.Contacts.CONTENT_URI,
//                java.lang.String.valueOf(contactIdKey)
//            )
//            this.startActivity(intent)

            Log.i("연락처 경로 확인", "$phoneUri")

            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = phoneUri
            this.startActivity(intent)
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
        val contactId = cursor?.getColumnIndex(ContactsContract.PhoneLookup._ID) ?: 0
        val look = cursor?.getColumnIndex(ContactsContract.PhoneLookup.LOOKUP_KEY) ?: 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val bDay = cursor.getString(bDayColumn)
                val pName = cursor.getString(peopleName)
                val contacts = cursor.getLong(contactId)
                val lookup = cursor.getString(look)
                "--05-22".convertToDateArray()
                Log.i("연락처 가져오기 테스트", "Contact Result: Birth $bDay Who $pName contact $contacts lookup $lookup")
                lookupId = lookup
                contactIdKey = contacts
                val birthCal = Calendar.getInstance()
//                val birthMonth = bDay.substring(bDay.length - 4 until bDay.length - 2)
//                val birthDate = bDay.substring(bDay.length - 2 until bDay.length)
//                birthCal.set(Calendar.MONTH, birthMonth.toInt() - 1)
//                birthCal.set(Calendar.DATE, birthDate.toInt())
//                Log.i("연락처 가져오기 테스트", "날짜 확인 : ${birthCal.time}")
            }
        }
    }

    private fun String.checkValidNumbers(): String {
        val regexPickNumber = "^\\d{2,3} - \\d{3,4} - \\d{4}\$".toRegex()
        return this.replace(regexPickNumber, "")
    }

    private fun String.convertToDateArray(): List<String> {
        val dateList = mutableListOf<String>()
        val regexDateConverter: Regex
        val matchResult: MatchResult

        if (this.startsWith("-")) {
            regexDateConverter = "^-[-/]?(\\d{2})[-/]?(\\d{2})$".toRegex()
            matchResult = regexDateConverter.find(this) ?: return emptyList()
            dateList.add("1920")
            dateList.addAll(matchResult.destructured.toList())
        } else {
            regexDateConverter = "^(\\d{4})[-/]?(\\d{2})[-/]?(\\d{2})$".toRegex()
            matchResult = regexDateConverter.find(this) ?: return emptyList()
            dateList.addAll(matchResult.destructured.toList())
        }
        return dateList
    }

    private fun getBirthByContacts(): Cursor? {
        val uri: Uri = ContactsContract.Data.CONTENT_URI

        val projection = arrayOf(
            ContactsContract.PhoneLookup.LOOKUP_KEY,
            ContactsContract.PhoneLookup._ID,
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