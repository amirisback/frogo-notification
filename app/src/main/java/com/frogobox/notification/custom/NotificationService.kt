package com.frogobox.notification.custom

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.core.app.RemoteInput
import com.frogobox.frogonotification.FrogoNotification
import com.frogobox.frogonotification.attr.IFNActionRemoteInput
import com.frogobox.notification.R


class NotificationService : IntentService("NotificationService") {

    companion object {
        private const val KEY_REPLY = "key_reply_message"
        const val REPLY_ACTION = "com.frogobox.notification.directreply.REPLY_ACTION"
        const val CHANNEL_ID = "channel_01"
        val CHANNEL_NAME: CharSequence = "frogobox channel"

        fun getReplyMessage(intent: Intent): CharSequence? {
            val remoteInput = RemoteInput.getResultsFromIntent(intent)
            return remoteInput?.getCharSequence(KEY_REPLY)
        }
    }

    private var mNotificationId: Int = 0
    private var mMessageId: Int = 0

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            showNotification()
        }
    }

    private fun showNotification() {
        mNotificationId = 1
        mMessageId = 123

        FrogoNotification.Inject(this)
            .setChannelId(CHANNEL_ID)
            .setChannelName(CHANNEL_NAME as String)
            .setSmallIcon(R.drawable.ic_frogo_notif)
            .setContentTitle(getString(R.string.notif_title))
            .setContentText(getString(R.string.notif_content))
            .showWhen(true)
            .setupActionRemoteInput(object : IFNActionRemoteInput{
                override fun setRemoteInputResultKey(): String { return KEY_REPLY }
                override fun setRemoteInputLabel(): String { return getString(R.string.notif_action_reply) }
                override fun setActionIcon(): Int { return R.drawable.ic_frogo_send }
                override fun setActionTitle(): String { return getString(R.string.notif_action_reply) }
                override fun setActionIntent(): PendingIntent? { return getReplyPendingIntent() }
                override fun setAllowGeneratedReplies(): Boolean { return true }
            })
            .build()
            .launch(mNotificationId)

    }

    private fun getReplyPendingIntent(): PendingIntent {
        val intent: Intent
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = NotificationBroadcastReceiver.getReplyMessageIntent(
                this,
                mNotificationId,
                mMessageId
            )
            PendingIntent.getBroadcast(
                applicationContext,
                100,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            intent = ReplyActivity.getReplyMessageIntent(this, mNotificationId, mMessageId)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }
}