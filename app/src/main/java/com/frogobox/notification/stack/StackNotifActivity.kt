package com.frogobox.notification.stack

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.frogobox.notification.FrogoApp
import com.frogobox.notification.FrogoNotification
import com.frogobox.notification.core.FrogoNotifInboxStyleListener
import com.frogobox.notification.R
import com.frogobox.notification.simple.MainReceiver
import com.frogobox.notification.core.BaseActivity
import com.frogobox.notification.databinding.ActivityStackNotifBinding

class StackNotifActivity : BaseActivity<ActivityStackNotifBinding>() {

    companion object {
        private const val GROUP_KEY_EMAILS = "group_key_emails"
        private const val NOTIFICATION_REQUEST_CODE = 200
        private const val MAX_NOTIFICATION = 2
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "frogobox_channel"
    }

    private var idNotification = 0

    private val stackNotif = ArrayList<NotificationItem>()

    override fun setupViewBinding(): ActivityStackNotifBinding {
        return ActivityStackNotifBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Stack Notif")
        binding.apply {
            btnSend.setOnClickListener {
                val sender = edtSender.text.toString()
                val message = edtMessage.text.toString()
                if (sender.isEmpty() || message.isEmpty()) {
                    Toast.makeText(this@StackNotifActivity, "Data harus diisi", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    stackNotif.add(NotificationItem(idNotification, sender, message))
                    sendNotif()
                    idNotification++
                    edtSender.setText("")
                    edtMessage.setText("")

                    //tutup keyboard ketika tombol diklik
                    val methodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    methodManager.hideSoftInputFromWindow(edtMessage.windowToken, 0)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        stackNotif.clear()
        idNotification = 0
    }

    private fun sendNotif() {
        val intent = Intent(this, StackNotifActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        val pendingIntent = PendingIntent.getActivity(
            this,
            NOTIFICATION_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val frogoNotification = FrogoNotification.Inject(this)
            .setChannelId(CHANNEL_ID)
            .setChannelName(CHANNEL_NAME)
            .setSmallIcon(R.drawable.ic_frogo_email)
            .setGroup(GROUP_KEY_EMAILS)
            .setContentIntent(pendingIntent)
            .setupAutoCancel()

        // Check if NotificationID is smaller than Max Notif
        if (idNotification < MAX_NOTIFICATION) {

            stackNotif[idNotification].message?.let {
                frogoNotification
                    .setContentTitle("New Email from " + stackNotif[idNotification].sender)
                    .setContentText(it)
                    .setLargeIcon(R.drawable.ic_frogo_notif)
            }

        } else {

            frogoNotification
                .setContentTitle("$idNotification new emails")
                .setContentText("mail@frogobox.com")
                .setGroupSummary()
                .setupInboxStyle(object : FrogoNotifInboxStyleListener {
                    override fun addLine1(): String {
                        return "New Email from " + stackNotif[idNotification].sender
                    }

                    override fun addLine2(): String {
                        return "New Email from " + stackNotif[idNotification - 1].sender
                    }

                    override fun setBigContentTitle(): String {
                        return "$idNotification new emails"
                    }

                    override fun setSummaryText(): String {
                        return "mail@frogobox"
                    }
                })

        }

        frogoNotification
            .build()
            .launch(idNotification)

    }

}