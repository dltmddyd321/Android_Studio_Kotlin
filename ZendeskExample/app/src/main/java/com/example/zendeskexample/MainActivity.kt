package com.example.zendeskexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import zendesk.core.AnonymousIdentity
import zendesk.core.Identity
import zendesk.core.Zendesk
import zendesk.support.Support
import zendesk.support.guide.HelpCenterActivity

class MainActivity : AppCompatActivity() {

    private lateinit var helpCenterBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        helpCenterBtn = findViewById(R.id.help_button)

        Zendesk.INSTANCE.init(
            this,"https://omniwear.zendesk.com",
            "23705744c16d8e0698b45920f18aa26e43d7",
            "mobile_sdk_client_b7fd695c0e9a6056"
        )

        val identity : Identity = AnonymousIdentity()
        Zendesk.INSTANCE.setIdentity(identity)

        Support.INSTANCE.init(Zendesk.INSTANCE)

        helpCenterBtn.setOnClickListener {
            HelpCenterActivity.builder()
                .withContactUsButtonVisible(false)
                .withShowConversationsMenuButton(false)
                .withArticlesForCategoryIds(112233L, 223344L)
                .show(this)
        }
    }
}