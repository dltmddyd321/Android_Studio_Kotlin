package com.example.myapplication

import android.app.Activity
import android.content.Context
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.consumePurchase
import com.android.billingclient.api.queryPurchasesAsync
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object BillingManager {
    private var billingClient: BillingClient? = null

    fun startBillingClient(
        context: Context,
        listener: PurchasesUpdatedListener,
        onStarted: (BillingResult) -> Unit
    ) {
        billingClient?.endConnection()
        billingClient = BillingClient.newBuilder(context)
            .setListener(listener)
            .enablePendingPurchases()
            .build()
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {
                //서비스 연결 종료
            }

            override fun onBillingSetupFinished(p0: BillingResult) {
                //서비스 연결 완료 -> 초기 설정 진행
                CoroutineScope(Dispatchers.Main).launch { onStarted.invoke(p0) }
            }

        })
    }

    fun checkUnConfirmedPurchase(
        products: List<String>,
        productType: String,
        onResult: (List<ProductDetails>) -> Unit
    ) {
        val client = billingClient ?: run {
            onResult.invoke(emptyList())
            return
        }

        if (!client.isReady) {
            onResult.invoke(emptyList())
            return
        }

        val productList = mutableListOf<QueryProductDetailsParams.Product>()
        products.forEach {
            productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(it)
                    .setProductId(productType)
                    .build()
            )
        }

        val params = QueryProductDetailsParams.newBuilder()
        params.setProductList(productList)

        client.queryProductDetailsAsync(params.build()) { result, detailList ->
            if (result.responseCode != BillingClient.BillingResponseCode.OK) {
                onResult.invoke(emptyList())
                return@queryProductDetailsAsync
            }
            CoroutineScope(Dispatchers.Main).launch { onResult.invoke(detailList) }
        }
    }

    fun consumePurchase(purchaseToken: String) {
        val consumeParams = ConsumeParams.newBuilder().setPurchaseToken(purchaseToken).build()
        billingClient?.consumeAsync(consumeParams, object : ConsumeResponseListener {
            override fun onConsumeResponse(p0: BillingResult, p1: String) {
                if (p0.responseCode != BillingClient.BillingResponseCode.OK) return
                CoroutineScope(Dispatchers.Main).launch {
                    billingClient?.consumePurchase(consumeParams)
                }
            }
        })
    }

    fun acknowledgePurchase(purchaseToken: String) {
        val purchaseParams =
            AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchaseToken).build()

        billingClient?.acknowledgePurchase(purchaseParams) {
            if (it.responseCode == BillingClient.BillingResponseCode.OK) {
                CoroutineScope(Dispatchers.Main).launch {
                    billingClient?.acknowledgePurchase(purchaseParams)
                }
            }
        }
    }

    /**
     * 결제 프로세스 진입
     */
    fun startPurchase(product: ProductDetails, activity: Activity) {
        val offerToken = if (product.productType == BillingClient.ProductType.INAPP) "" else
            product.subscriptionOfferDetails?.first()?.offerToken ?: return

        val productDetailParamsList = if (product.productType == BillingClient.ProductType.INAPP)
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(product).build()
            ) else listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(product).setOfferToken(offerToken).build()
        )
        val billingFlowParams = BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailParamsList).build()
        billingClient?.launchBillingFlow(activity, billingFlowParams)
    }

    fun getSubscriptionPurchaseInfo(onChecked: (List<Purchase>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val result = billingClient?.queryPurchasesAsync(BillingClient.ProductType.SUBS)?.purchasesList
            withContext(Dispatchers.Main) { onChecked.invoke(result ?: emptyList()) }
        }
    }
}