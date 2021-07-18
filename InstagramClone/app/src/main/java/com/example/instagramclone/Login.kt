package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    var auth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        emailLoginBtn.setOnClickListener {
            signinAndSignup()
        }
    }

    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(emailText.text.toString(),pwdText.text.toString())
            ?.addOnCompleteListener {
                task ->
                    if(task.isSuccessful){
                        //아이디 생성 시

                    } else if(task.exception?.message.isNullOrEmpty()) {
                        //로그인 에러 발생 시
                        Toast.makeText(this, task.exception?.message,Toast.LENGTH_SHORT).show()
                    } else {
                        //로그인 성공 시
                        signinEmail()
                    }
            }
    }

    fun signinEmail() {
        auth?.signInWithEmailAndPassword(emailText.text.toString(),pwdText.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if (task.isSuccessful) {
                    //Login
                    moveMainPage(task.result?.user)
                } else {
                    //에러 메시지
                    Toast.makeText(this, task.exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun moveMainPage(user:FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

}