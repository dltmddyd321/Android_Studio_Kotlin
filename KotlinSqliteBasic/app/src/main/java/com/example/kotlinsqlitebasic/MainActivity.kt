package com.example.kotlinsqlitebasic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinsqlitebasic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var usersDbHelper: UsersDbHelper
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        usersDbHelper = UsersDbHelper(this)
    }

    @SuppressLint("SetTextI18n")
    fun addUser(v: View) {
        val userid = this.binding.edittextUserid.text.toString()
        val name = this.binding.edittextName.text.toString()
        val age = this.binding.edittextAge.text.toString()
        val result = usersDbHelper.insertUser(UserModel(userid, name, age))

        this.binding.edittextAge.setText("")
        this.binding.edittextName.setText("")
        this.binding.edittextUserid.setText("")
        this.binding.textviewResult.text = "Added User : $result"
        this.binding.llEntries.removeAllViews()
    }

    @SuppressLint("SetTextI18n")
    fun deleteUser(v: View) {
        val userId = this.binding.edittextUserid.text.toString()
        val result = usersDbHelper.deleteUser(userId)
        this.binding.textviewResult.text = "Deleted User $result"
        this.binding.llEntries.removeAllViews()
    }

    @SuppressLint("SetTextI18n")
    fun showAllUsers(v : View) {
        val users = usersDbHelper.readAllUsers()
        this.binding.llEntries.removeAllViews()
        users.forEach {
            val userItem = TextView(this)
            userItem.textSize = 30F
            userItem.text = it.name + " - "
            this.binding.llEntries.addView(userItem)
        }
        this.binding.textviewResult.text = "Fetched " + users.size + " users"
    }
}