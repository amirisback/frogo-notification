package com.frogobox.notification.custom

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.frogobox.frogonotification.FrogoNotification
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
        mEditReply = findViewById(R.id.edit_reply)
        val sendButton = findViewById<ImageButton>(R.id.button_send)
        sendButton.setOnClickListener { sendMessage(mNotifyId, mMessageId) }
    }

    private fun sendMessage(notifyId: Int, messageId: Int) {
        updateNotification(notifyId)
        val message = mEditReply.text.toString().trim { it <= ' ' }
        Toast.makeText(this, "Message ID: $messageId\nMessage: $message", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun updateNotification(notifyId: Int) {

        FrogoNotification.Inject(this)
            .setNotificationId(notifyId)
            .setChannelId(CHANNEL_ID)
            .setChannelName(CHANNEL_NAME as String)
            .setSmallIcon(R.drawable.ic_frogo_notif)
            .setContentTitle(getString(R.string.notif_title_sent))
            .setContentText(getString(R.string.notif_content_sent))
            .setupWithVibration()
            .build()
            .launch()

    }
}