package com.example.mvvminstagramclone.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvvminstagramclone.FindIdActivity
import com.example.mvvminstagramclone.InputNumberActivity
import com.example.mvvminstagramclone.R
import com.example.mvvminstagramclone.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewmodel = loginViewModel
        binding.activity = this
        binding.lifecycleOwner = this //바인딩과 생명주기 동일시
        setObserve()
    }

    private fun setObserve() {
        loginViewModel.showInputNumberActivity.observe(this) {
            if (it) {
                finish()
                startActivity(Intent(this, InputNumberActivity::class.java))
            }
        }
        loginViewModel.showFindIdActivity.observe(this) {
            if (it) {
                finish()
                startActivity(Intent(this, FindIdActivity::class.java))
            }
        }
    }

    fun loginEmail() {
        loginViewModel.showInputNumberActivity.value = true
    }

    fun findId() {
        loginViewModel.showFindIdActivity.value = true
    }
}