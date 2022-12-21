package com.sycompany.contactsconnection

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.database.Cursor
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private var imgView: ImageView? = null
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
        imgView = findViewById(R.id.imgView)

        checkRegDate()

        mBtnLogin?.setOnClickListener {
            getContacts()
        }

        Glide.with(this)
            .load("content://com.android.contacts/display_photo/4")
            .into(imgView!!)

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

    private fun getContacts() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)
        }
        val cursor = getBirthByContacts()
        val bDayColumn = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE) ?: 0
        val peopleName = cursor?.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME) ?: 0
        val contactId = cursor?.getColumnIndex(ContactsContract.PhoneLookup._ID) ?: 0
        val look = cursor?.getColumnIndex(ContactsContract.PhoneLookup.LOOKUP_KEY) ?: 0
        val photo = cursor?.getColumnIndex(ContactsContract.CommonDataKinds.Photo.PHOTO_URI) ?: 0
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val bDay = cursor.getString(bDayColumn)
                val pName = cursor.getString(peopleName)
                val contacts = cursor.getLong(contactId)
                val lookup = cursor.getString(look)
                val photoUri = cursor.getString(photo)
                Log.i("연락처 가져오기 테스트", "Contact Result: birth $bDay name $pName contactId $contacts lookupKey $lookup \n photo: $photoUri")
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
            ContactsContract.CommonDataKinds.Photo.PHOTO_URI,
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

    fun View.isVisible(): Boolean {
        if (!isShown) {
            return false
        }
        val actualPosition = Rect()
        val isGlobalVisible = getGlobalVisibleRect(actualPosition)
        val screenWidth = Resources.getSystem().displayMetrics.widthPixels
        val screenHeight = Resources.getSystem().displayMetrics.heightPixels
        val screen = Rect(0, 0, screenWidth, screenHeight)
        return isGlobalVisible && Rect.intersects(actualPosition, screen)
    }

    fun ScrollView.isShowInScrollArea(childView: View): Boolean {
        var topValue = 0f
        var child = childView
        val showingScrollArea = Rect()
        this.getDrawingRect(showingScrollArea)
        while (child !is ScrollView){
            topValue += (child).y
            child = child.parent as View
        }
        val bottomValue = topValue + childView.height
        return showingScrollArea.top < topValue && showingScrollArea.bottom > bottomValue
    }

    private fun checkWeekDays() {
        val calOne = Calendar.getInstance()
        calOne.timeInMillis = System.currentTimeMillis()
        calOne.set(Calendar.HOUR_OF_DAY, 0)
        calOne.set(Calendar.MINUTE, 0)
        calOne.set(Calendar.SECOND, 0)
        calOne.set(Calendar.MILLISECOND, 0)
        calOne.add(Calendar.DATE, 7 - calOne.get(Calendar.DAY_OF_WEEK))

        Log.d("Calendar Test!", "이번주 종료 시간 : ${calOne.timeInMillis}")
    }

    @SuppressLint("SimpleDateFormat")
    private fun checkRegDate() {
        val now = System.currentTimeMillis()
        val date = Date(now)
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val strDate = sdf.format(date)
        Log.d("날짜 문자열 확인", "result : $strDate")
    }
}