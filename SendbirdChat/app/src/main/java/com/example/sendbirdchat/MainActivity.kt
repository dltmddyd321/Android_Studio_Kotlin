package com.example.sendbirdchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.sendbird.android.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var currentUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SendBird.init(getString(R.string.sendbird_key), applicationContext)

        SendBird.connect(getString(R.string.currentUser)) { user, e ->
            if(e == null) {
                GroupChannel.createMyGroupChannelListQuery().next { list, _ ->
                    if(list.isNullOrEmpty()) {
                        Toast.makeText(this, "채팅방 생성!!", Toast.LENGTH_SHORT).show()
                        createChannel()
                    } else {
                        Toast.makeText(this, "이미 채팅방이 존재합니다.", Toast.LENGTH_SHORT).show()
                        currentUrl = list.first().url
                        getGroupUrl(currentUrl) { groupChannel ->
                            prevMsg()
                        }
                    }
                }
            }
        }

        button.setOnClickListener {
            GroupChannel.getChannel(currentUrl) { groupChannel, e ->
                groupChannel.join { }
                groupChannel.sendUserMessage(chatMessage_et.text.toString()) { msg, e ->
                    chat_tv.append("${msg.message}\n")
                }
            }
        }

        btn_invite.setOnClickListener {
            val userIds = listOf<String>(getString(R.string.opponentUser))
            getGroupUrl(currentUrl) { groupChannel ->
                groupChannel.inviteWithUserId(userIds.toString()) {}
            }
        }

        btn_exit.setOnClickListener {
            getGroupUrl(currentUrl) { groupChannel ->
                groupChannel.leave {
                    Toast.makeText(this, "탈퇴", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun createChannel() {
        val userList = listOf<String>(getString(R.string.currentUser))

        val params = GroupChannelParams()
            .setPublic(false)
            .setEphemeral(false)
            .setDistinct(true)
            .setSuper(false)
            .addUserIds(userList)
            .setName(getString(R.string.currentUser))

        GroupChannel.createChannel(params) { groupChannel, _ ->
            currentUrl = groupChannel.url
        }
    }

    private fun prevMsg() {
        GroupChannel.createMyGroupChannelListQuery().next { list, e ->
            list.forEach {
                chat_tv.append("${it.lastMessage.message}\n")
            }
        }
    }

    private fun getGroupUrl(url: String, afterLogin: (GroupChannel) -> Unit) {
        GroupChannel.getChannel(url) { groupChannel, e ->
            afterLogin(groupChannel)
        }
    }

    override fun onResume() {
        super.onResume()
        SendBird.addChannelHandler(GROUP_HANDLER_ID, object : SendBird.ChannelHandler() {
            override fun onMessageReceived(p0: BaseChannel?, message: BaseMessage?) {
                chat_tv.append("${message!!.message}\n")
            }
        })
    }

    companion object {
        const val GROUP_HANDLER_ID = "GROUP_HANDLER_ID"
    }
}