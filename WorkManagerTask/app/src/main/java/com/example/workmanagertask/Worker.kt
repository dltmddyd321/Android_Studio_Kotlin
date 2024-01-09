package com.example.workmanagertask

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

/*
처리해야 하는 작업을 자신의 큐에 넣고 관리한다
싱글톤으로 구현이 되어있기 때문에 getInstance()로 WorkManager의 인스턴스를 받아 사용한다.
 */
class Worker(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    /*
    작업을 완료하고 결과에 따라 Worker클래스 내에 정의된 enum인 Result의 값중 하나를 리턴해야 한다
    SUCCESS, FAILURE, RETRY의 3개 값이 있으며 리턴되는 이 값에 따라 WorkManager는 해당 작업을
    마무리 할것인지, 재시도 할것인지, 실패로 정의하고 중단할 것인지를 결정하게 된다.
     */
    override fun doWork(): Result {

        showNotification()

        Log.d("Worker", "doWork : Success!!")
        return Result.success()
    }

    private fun showNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, 0
        )

        val builder = NotificationCompat.Builder(applicationContext, "Test_Notification_Id")
            .setContentTitle("Life is good!")
            .setContentText("I'm handsome")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(1, builder.build())
        }

    }
}

object SyncListManger {

    var onEndLastTask: (() -> Unit?)? = null
    private var syncTasks = Collections.synchronizedList<SyncObject>(mutableListOf())

    fun addTask(key: String, syncTask: PagingSyncTask) {
        synchronized(syncTasks) {
            if (syncTasks.size >= 3) {
                syncTasks.last().task.cancel()
                syncTasks.removeLast()
            }
            syncTasks.add(SyncObject(key, syncTask))
        }
    }

    fun cancelAllTasks() {
        synchronized(syncTasks) {
            syncTasks.forEach { it.task.cancel() }
            syncTasks.clear()
        }
    }

    fun removeTask(key: String) {
        synchronized(syncTasks) {
            syncTasks.removeIf { it.key == key }
            if (syncTasks.isNotEmpty()) return
            mainAsync {
                onEndLastTask?.invoke()
                onEndLastTask = null
            }
        }
    }

    fun isSyncing() = syncTasks.isNotEmpty()

    data class SyncObject(
        val key: String,
        val task: PagingSyncTask
    )
}
