package com.example.pushevent

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.remoteMessage
import java.nio.channels.Channel

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val type = remoteMessage.data["type"]
            ?.let { NotificationType.valueOf(it) }
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]

        type ?: return
        //타입이 null이면 중지

        NotificationManagerCompat.from(this)
            .notify(type.id, createNotification(type, title, message))
        //메시지 수신 시 설정한 내용에 맞게 알림창이 팝업(상단)
    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_DESCRIPTION
            //채널의 객체를 생성하여 그에 대한 내용을 등록

            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
                //채널 완성
        }
    }

    private fun createNotification(type : NotificationType, title:String?, message:String?): Notification {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notificationType", "${type.title} 타입")
            addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            // 동일한 Activity Stack에 연속적으로 쌓이면 Activity를 재사용하는 Flag
        }

        val pendingIntent = PendingIntent.getActivity(this,type.id,intent,FLAG_UPDATE_CURRENT)
        //자신 제외 다른 누군가에게 Intent를 다룰 수 있는 권한을 부여

        val notificationBuilder =  NotificationCompat.Builder(this, CHANNEL_ID) //알림 컨텐츠 생성
            .setSmallIcon(R.drawable.ic_noti)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        //제목과 Text, Priority 설정

        when(type){
            NotificationType.NORMAL -> Unit
            //기본형 = 특별한 추가 없음

            NotificationType.EXPANDABLE -> { //확장형
                notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(
                            "😀 😃 😄 😁 😆 😅 😂 🤣 🥲 ☺️ 😊 😇 " +
                                    "🙂 🙃 😉 😌 😍 🥰 😘 😗 😙 😚 😋 😛 " +
                                    "😝 😜 🤪 🤨 🧐 🤓 😎 🥸 🤩 🥳 😏 😒 " +
                                    "😞 😔 😟 😕 🙁 ☹️ 😣 😖 😫 😩 🥺 😢 " +
                                    "😭 😤 😠 😡 🤬 🤯 😳 🥵 🥶 😱 😨 😰 " +
                                    "😥 😓 🤗 🤔 🤭 🤫 🤥 😶 😐 😑 😬 🙄 " +
                                    "😯 😦 😧 😮 😲 🥱 😴 🤤 😪 😵 🤐 🥴 " +
                                    "🤢 🤮 🤧 😷 🤒 🤕"
                        ) //확장형에 따른 텍스트 내용과 스타일 지정
                )
            }

            NotificationType.CUSTOM -> { //맞춤형
                notificationBuilder.setStyle(
                    NotificationCompat.DecoratedCustomViewStyle())
                    .setCustomContentView(
                        RemoteViews(
                            packageName,
                            R.layout.view_newcustom_noti)
                            .apply {
                                setTextViewText(R.id.title, title)
                                setTextViewText(R.id.message, message)
                            } //Custom 알림에 대한 제목과 메시지에 대한 내용 반영
                        )
                //개발자의 맞춤 레이아웃을 기준으로 알림창을 설정
            }
        }
        return notificationBuilder.build()
        //최종 빌드로 반환
    }

    companion object {
        private const val CHANNEL_NAME = "Emoji Party"
        private const val CHANNEL_DESCRIPTION = "Emoji Party를 위한 채널"
        private const val CHANNEL_ID = "Channel ID"
    }
}