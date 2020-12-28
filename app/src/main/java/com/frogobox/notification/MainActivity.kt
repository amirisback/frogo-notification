package com.frogobox.notification

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.frogobox.frogonotification.FrogoNotification
import com.frogobox.notification.custom.CustomNotifActivity
import com.frogobox.notification.stack.StackNotifActivity

class MainActivity : AppCompatActivity() {

    companion object {
        private const val NOTIFICATION_ID = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //aksi untuk onClick pada button
    fun sendNotification(view: View) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amirisback"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val frogoNotification = FrogoNotification.Inject(this)
            .setResoures(resources)
            .setNotificationId(NOTIFICATION_ID)
//            .setChannelId(CHANNEL_ID)
//            .setChannelName(CHANNEL_NAME)
//            .setContentIntent(pendingIntent)
//            .setSmallIcon(R.drawable.ic_frogo_notif)
//            .setLargeIcon(R.drawable.ic_frogo_notif)
//            .setContentTitle(resources.getString(R.string.content_title))
//            .setContentText(resources.getString(R.string.content_text))
//            .setSubText(resources.getString(R.string.subtext))
//            .setAutoCancel(true)
            .setupWithFrogoStyle()
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