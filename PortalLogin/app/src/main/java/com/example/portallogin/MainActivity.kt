package com.example.portallogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.portallogin.databinding.ActivityMainBinding
import com.navercorp.nid.NaverIdLoginSDK
import com.navercorp.nid.oauth.NidOAuthLogin
import com.navercorp.nid.oauth.OAuthLoginCallback
import com.navercorp.nid.profile.NidProfileCallback
import com.navercorp.nid.profile.data.NidProfileResponse

class MainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName

    private lateinit var binding: ActivityMainBinding
    private var email: String = ""
    private var gender: String = ""
    private var name: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.run {
            naverLoginBtn.setOnClickListener {
                val oAuthLoginCallback = object : OAuthLoginCallback {
                    override fun onError(errorCode: Int, message: String) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(httpStatus: Int, message: String) {
                        TODO("Not yet implemented")
                    }

                    override fun onSuccess() {
                        NidOAuthLogin().callProfileApi(object :
                            NidProfileCallback<NidProfileResponse> {
                            override fun onError(errorCode: Int, message: String) {
                                val naverAccessToken = NaverIdLoginSDK.getAccessToken()
                                Log.e(TAG, "naverAccessToken : $naverAccessToken")
                            }

                            override fun onFailure(httpStatus: Int, message: String) {}

                            override fun onSuccess(result: NidProfileResponse) {
                                name = result.profile?.name.toString()
                                email = result.profile?.email.toString()
                                gender = result.profile?.gender.toString()
                                Log.e(TAG, "네이버 로그인한 유저 정보 - 이름 : $name")
                                Log.e(TAG, "네이버 로그인한 유저 정보 - 이메일 : $email")
                                Log.e(TAG, "네이버 로그인한 유저 정보 - 성별 : $gender")
                            }

                        })
                    }
                }

                NaverIdLoginSDK.initialize(
                    this@MainActivity,
                    getString(R.string.naver_client_id),
                    getString(R.string.naver_client_secret),
                    "AuthLoginTest"
                )
                NaverIdLoginSDK.authenticate(this@MainActivity, oAuthLoginCallback)
            }
        }
    }
}