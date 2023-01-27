package com.example.biometricseries

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import com.example.biometricseries.databinding.ActivityMainBinding
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "BiometricTest"
    }

    private val pattern = longArrayOf(0, 50, 0)
    private lateinit var vibrator: Vibrator
    private lateinit var binding: ActivityMainBinding
    private var biometricPrompt: BiometricPrompt? = null
    private var promptInfo: BiometricPrompt.PromptInfo? = null

    private val loginLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            Log.d(TAG, "registerForActivityResult - result : $result")

            if (result.resultCode == Activity.RESULT_OK) {
                Log.d(TAG, "registerForActivityResult - RESULT_OK")
                authenticateToEncrypt()  //생체 인증 가능 여부확인 다시 호출
            } else {
                Log.d(TAG, "registerForActivityResult - NOT RESULT_OK")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vibrator =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) (getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager).defaultVibrator
            else @Suppress("DEPRECATION") getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    private fun setPromptInfo(): BiometricPrompt.PromptInfo {
        val promptBuilder = BiometricPrompt.PromptInfo.Builder()

        promptBuilder.setTitle("Biometric login for my app")
        promptBuilder.setSubtitle("Log in using your biometric credential")
        promptBuilder.setNegativeButtonText("Use account password")
        promptBuilder.setConfirmationRequired(false)

        promptInfo = promptBuilder.build()
        return promptInfo as BiometricPrompt.PromptInfo
    }

    private fun setBiometricPrompt(): BiometricPrompt {
        val executor = Executors.newSingleThreadExecutor()
        biometricPrompt =
            BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(this@MainActivity, "지문 인식 성공", Toast.LENGTH_SHORT).show()
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        this@MainActivity,
                        """"지문 인식 ERROR [ errorCode: $errorCode, errString: $errString ]""".trimIndent(),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(this@MainActivity, "지문 인식 실패", Toast.LENGTH_SHORT).show()
                }
            })
        return biometricPrompt as BiometricPrompt
    }

    private fun authenticateToEncrypt() = with(binding) {
        val textStatus: String
        val biometricManager = BiometricManager.from(this@MainActivity)

        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)) {

            //생체 인증 가능
            BiometricManager.BIOMETRIC_SUCCESS -> textStatus =
                "App can authenticate using biometrics."

            //기기에서 생체 인증을 지원하지 않는 경우
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> textStatus =
                "No biometric features available on this device."

            //현재 생체 인증을 사용할 수 없는 경우
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> textStatus =
                "Biometric features are currently unavailable."

            //생체 인식 정보가 등록되어 있지 않은 경우
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                textStatus = "Prompts the user to create credentials that your app accepts."

                val dialogBuilder = AlertDialog.Builder(this@MainActivity)
                dialogBuilder
                    .setTitle("나의앱")
                    .setMessage("지문 등록이 필요합니다. 지문등록 설정화면으로 이동하시겠습니까?")
                    .setPositiveButton("확인") { dialog, which -> goBiometricSettings() }
                    .setNegativeButton("취소") { dialog, which -> dialog.cancel() }
                dialogBuilder.show()
            }

            //기타 실패
            else -> textStatus = "Fail Biometric facility"

        }
        binding.tvStatus.text = textStatus

        goAuthenticate()
    }

    /*
    * 생체 인식 인증 실행
    */
    private fun goAuthenticate() {
        Log.d(TAG, "goAuthenticate - promptInfo : $promptInfo")
        promptInfo?.let {
            biometricPrompt?.authenticate(it) //인증 실행
        }
    }

    /*
    * 지문 등록 화면으로 이동
    */
    private fun goBiometricSettings() {
        val enrollIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
            }
        } else {
            Intent(Settings.ACTION_SECURITY_SETTINGS)
        }
        loginLauncher.launch(enrollIntent)
    }

    private fun startVibrate(pattern: LongArray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(pattern, VibrationEffect.DEFAULT_AMPLITUDE))
        }
    }
}