package com.frogobox.notification.custom

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.frogobox.notification.R
import com.frogobox.notification.custom.NotificationService.Companion.CHANNEL_ID
import com.frogobox.notification.custom.NotificationService.Companion.CHANNEL_NAME
import com.frogobox.notification.custom.NotificationService.Companion.REPLY_ACTION

class ReplyActivity : AppCompatActivity() {

    companion object {
        private const val KEY_MESSAGE_ID = "key_message_id"
        private const val KEY_NOTIFY_ID = "key_notify_id"
        fun getReplyMessageIntent(context: Context, notifyId: Int, messageId: Int): Intent {
            val intent = Intent(context, ReplyActivity::class.java)
            intent.action = REPLY_ACTION
            intent.putExtra(KEY_MESSAGE_ID, messageId)
            intent.putExtra(KEY_NOTIFY_ID, notifyId)
            return intent
        }
    }
    private var mMessageId: Int = 0
    private var mNotifyId: Int = 0

    private lateinit var mEditReply: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reply)

        val intent = intent
        if (REPLY_ACTION == intent.action) {
            mMessageId = intent.getIntExtra(KEY_MESSAGE_ID, 0)
            mNotifyId = intent.getIntExtra(KEY_NOTIFY_ID, 0)
        }
        mEditReply = findViewById<EditText>(R.id.edit_reply)
        val sendButton = findViewById<ImageButton>(R.id.button_send)
        sendButton.setOnClickListener { sendMessage(mNotifyId, mMessageId) }
    }

    private fun sendMessage(notifyId: Int, messageId: Int) {
        updateNotification(notifyId)
        val message = mEditReply.text.toString().trim { it <= ' ' }
        Toast.makeText(this, "Message ID: $messageId\nMessage: $message",
            Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun updateNotification(notifyId: Int) {
        val notificationManagerCompat = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications_48px)
            .setContentTitle(getString(R.string.notif_title_sent))
            .setContentText(getString(R.string.notif_content_sent))
        /*
        Untuk android Oreo ke atas perlu menambahkan notification channel
        Materi ini akan dibahas lebih lanjut di modul extended
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /* Create or update. */
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifyId, notification)
    }
}