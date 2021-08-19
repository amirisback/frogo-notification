package com.frogobox.notification.simple

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.frogobox.notification.FrogoNotification
import com.frogobox.notification.R
import com.frogobox.notification.core.BaseActivity
import com.frogobox.notification.custom.CustomNotifActivity
import com.frogobox.notification.databinding.ActivityMainBinding
import com.frogobox.notification.stack.StackNotifActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "CHANNEL_$NOTIFICATION_ID"
        private const val CHANNEL_NAME = "CHANNEL_NAME_$CHANNEL_ID"
    }

    override fun setupViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {

            btnSendNotif.setOnClickListener {
                sendNotification()
            }

            btnCustomNotif.setOnClickListener {
                intentToCustom()
            }

            btnStackNotif.setOnClickListener {
                intentToStack()
            }

        }

    }

    private fun sendNotification() {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/amirisback"))
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        FrogoNotification.Inject(this) // Intialize for Context
            .setChannelId(CHANNEL_ID) // Intialize for Channel ID
            .setChannelName(CHANNEL_NAME) // Initialize for Channel Name
            .setContentIntent(pendingIntent) // Initialize for Content Intent
            .setSmallIcon(R.drawable.ic_frogo_notif) // Initialize for Small Icon
            .setLargeIcon(R.drawable.ic_frogo_notif) // Initialize for Large Icon
            .setContentTitle(resources.getString(R.string.content_title)) // Initialize for Content Title
            .setContentText(resources.getString(R.string.content_text)) // Initialize for Content Text
            .setSubText(resources.getString(R.string.subtext)) // Initialize for Sub Text
            .setAutoCancel(true) // Initialize for Auto Cancel
            .build() // Build the Frogo Notification
            .launch(NOTIFICATION_ID) // Notify the Frogo Notification

    }

    private fun intentToCustom() {
        startActivity(Intent(this, CustomNotifActivity::class.java))
    }

    private fun intentToStack() {
        startActivity(Intent(this, StackNotifActivity::class.java))
    }

}