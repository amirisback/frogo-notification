package com.frogobox.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import com.frogobox.frogonotification.FrogoNotification
import com.frogobox.notification.custom.CustomNotifActivity
import com.frogobox.notification.stack.StackNotifActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "dicoding channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //aksi untuk onClick pada button
    fun sendNotification(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://dicoding.com"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val frogoNotification = FrogoNotification.Inject(this)
            .setNotificationId(NOTIFICATION_ID)
            .setChannelId(CHANNEL_ID)
            .setChannelName(CHANNEL_NAME)
            .setResoures(resources)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notifications)
            .setLargeIcon(R.drawable.ic_notifications)
            .setContentTitle(resources.getString(R.string.content_title))
            .setContentText(resources.getString(R.string.content_text))
            .setSubText(resources.getString(R.string.subtext))
            .setAutoCancel(true)
            .build()

        frogoNotification.launch()

    }

    fun intentToCustom(view: View) {
        startActivity(Intent(this, CustomNotifActivity::class.java))
    }

    fun intentToStack(view: View) {
        startActivity(Intent(this, StackNotifActivity::class.java))
    }
}