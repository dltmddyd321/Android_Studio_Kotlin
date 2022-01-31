package com.example.billingprocessormodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anjlab.android.iab.v3.BillingProcessor
import com.anjlab.android.iab.v3.PurchaseInfo

class MainActivity : AppCompatActivity(), BillingProcessor.IBillingHandler {

    lateinit var bp : BillingProcessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //객체 생성 및 초기화
        bp = BillingProcessor(this, "YOUR LICENSE KEY FROM GOOGLE PLAY CONSOLE HERE", this)
        bp.initialize()
    }

    override fun onProductPurchased(productId: String, details: PurchaseInfo?) {
        //요청한 상품 ID가 성공적으로 구매 처리되었을 때 호출
    }

    override fun onPurchaseHistoryRestored() {
        //구매 내역 복원 시 호출
    }

    override fun onBillingError(errorCode: Int, error: Throwable?) {
        //특정 에러가 발생했을 시 호출
    }

    override fun onBillingInitialized() {
        //BillingProcessor 초기화 및 구매 준비가 완료되면 호출
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}