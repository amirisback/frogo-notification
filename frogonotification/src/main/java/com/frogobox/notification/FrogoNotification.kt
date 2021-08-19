package com.frogobox.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.frogobox.notification.core.Constant
import com.frogobox.notification.core.FrogoNotifActionRemoteInputListener
import com.frogobox.notification.core.FrogoNotifInboxStyleListener

/*
 * Created by Faisal Amir on 26/12/2020
 * Notification Source Code
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2020 FrogoBox Inc.      
 * All rights reserved
 *
 */
class FrogoNotification {

    class Inject(val context: Context) : IFrogoNotification {

        private val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        private lateinit var notification: Notification

        private var smallIcon: Int = R.drawable.ic_frogo_notif

        private var remoteInput: RemoteInput? = null
        private var notificationAction: NotificationCompat.Action? = null
        private var inboxStyle: NotificationCompat.InboxStyle? = null
        private var style: NotificationCompat.Style? = null

        private var contentTitle: CharSequence? = null
        private var contentText: CharSequence? = null
        private var contentSubText: CharSequence? = null
        private var pendingIntent: PendingIntent? = null
        private var largeIcon: Int? = null
        private var autoCancel: Boolean? = false
        private var showWhen: Boolean? = false
        private var vibration: Boolean = false
        private var groupKey: String? = null
        private var isGroupSummary: Boolean = false

        private var notification_id: Int = Constant.FROGO_NOTIFICATION_ID
        private var channel_id: String = Constant.FROGO_CHANNEL_ID
        private var channel_name: String = Constant.FROGO_CHANNEL_NAME

        init {
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Initialize Context and Declare Notification Manager"
            )
        }

        override fun setChannelId(channelId: String): Inject {
            this.channel_id = channelId
            Log.d(FrogoNotification::class.java.simpleName, "Value of Channel_ID : $channelId")
            return this
        }

        override fun setChannelName(channelName: String): Inject {
            this.channel_name = channelName
            Log.d(FrogoNotification::class.java.simpleName, "Value of Channel Name : $channelName")
            return this
        }

        override fun setContentIntent(intent: PendingIntent): Inject {
            this.pendingIntent = intent
            Log.d(FrogoNotification::class.java.simpleName, "Value of Intent : $intent")
            return this
        }

        override fun setSmallIcon(smallIcon: Int): Inject {
            this.smallIcon = smallIcon
            Log.d(FrogoNotification::class.java.simpleName, "Value of Small Icon : $smallIcon")
            return this
        }

        override fun setLargeIcon(largeIcon: Int): Inject {
            this.largeIcon = largeIcon
            Log.d(FrogoNotification::class.java.simpleName, "Value of Large Icon : $largeIcon")
            return this
        }

        override fun setContentTitle(contentTitle: CharSequence): Inject {
            this.contentTitle = contentTitle
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Value of Content Title : $contentTitle"
            )
            return this
        }

        override fun setContentText(contentText: CharSequence): Inject {
            this.contentText = contentText
            Log.d(FrogoNotification::class.java.simpleName, "Value of Content Text : $contentText")
            return this
        }

        override fun setSubText(contentSubText: CharSequence): Inject {
            this.contentSubText = contentSubText
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Value of Content Sub Text : $contentSubText"
            )
            return this
        }

        override fun setAutoCancel(autoCancel: Boolean): Inject {
            this.autoCancel = autoCancel
            Log.d(FrogoNotification::class.java.simpleName, "Value of Auto Cancel : $autoCancel")
            return this
        }

        override fun setStyle(style: NotificationCompat.Style): Inject {
            this.style = style
            Log.d(FrogoNotification::class.java.simpleName, "Value of Style : $style")
            return this
        }

        override fun showWhen(show: Boolean): Inject {
            this.showWhen = show
            Log.d(FrogoNotification::class.java.simpleName, "Value of Show When : $show")
            return this
        }

        override fun setGroup(groupKey: String): Inject {
            this.groupKey = groupKey
            Log.d(FrogoNotification::class.java.simpleName, "Value of Show When : $groupKey")
            return this
        }

        override fun setGroupSummary(): Inject {
            this.isGroupSummary = true
            Log.d(FrogoNotification::class.java.simpleName, "Value of Show When : $isGroupSummary")
            return this
        }

        override fun setupWithVibration(): Inject {
            this.vibration = !vibration
            Log.d(FrogoNotification::class.java.simpleName, "Value of Vibration : $vibration")
            return this
        }

        override fun setupActionRemoteInput(listener: FrogoNotifActionRemoteInputListener): Inject {
            remoteInput = RemoteInput.Builder(listener.setRemoteInputResultKey())
                .setLabel(listener.setRemoteInputLabel())
                .build()

            notificationAction = NotificationCompat.Action.Builder(
                listener.setActionIcon(),
                listener.setActionTitle(),
                listener.setActionIntent()
            )
                .addRemoteInput(remoteInput)
                .setAllowGeneratedReplies(listener.setAllowGeneratedReplies())
                .build()

            Log.d(FrogoNotification::class.java.simpleName, "RemoteInput (Key) : ${listener.setRemoteInputResultKey()}")
            Log.d(FrogoNotification::class.java.simpleName, "RemoteInput (Label) : ${listener.setRemoteInputLabel()}")
            Log.d(FrogoNotification::class.java.simpleName, "Action (Icon) : ${listener.setActionIcon()}")
            Log.d(FrogoNotification::class.java.simpleName, "Action (Title) : ${listener.setActionTitle()}")
            Log.d(FrogoNotification::class.java.simpleName, "Action (Intent) : ${listener.setActionIntent()}")
            Log.d(FrogoNotification::class.java.simpleName, "Action (Generate Replies) : ${listener.setAllowGeneratedReplies()}")

            return this
        }

        override fun setupInboxStyle(listener: FrogoNotifInboxStyleListener): Inject {
            inboxStyle = NotificationCompat.InboxStyle()
                .addLine(listener.addLine1())
                .addLine(listener.addLine2())
                .setBigContentTitle(listener.setBigContentTitle())
                .setSummaryText(listener.setSummaryText())

            Log.d(FrogoNotification::class.java.simpleName, "Inbox Syle (Add Line) : ${listener.addLine1()}")
            Log.d(FrogoNotification::class.java.simpleName, "Inbox Syle (Add Line) : ${listener.addLine2()}")
            Log.d(FrogoNotification::class.java.simpleName, "Inbox Syle (Big Content Title) : ${listener.setBigContentTitle()}")
            Log.d(FrogoNotification::class.java.simpleName, "Inbox Syle (Summary Text) : ${listener.setSummaryText()}")
            return this
        }

        override fun setupWithFrogoStyle(): Inject {
            this.contentTitle = context.resources.getString(R.string.frogo_content_title)
            this.contentText = context.resources.getString(R.string.frogo_content_text)
            this.contentSubText = context.resources.getString(R.string.frogo_subtext)
            this.largeIcon = R.drawable.ic_frogo_notif
            this.autoCancel = false

            Log.d(FrogoNotification::class.java.simpleName, "Using Frogo Notification Template")
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Value of Content Title : $contentTitle"
            )
            Log.d(FrogoNotification::class.java.simpleName, "Value of Content Text : $contentText")
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Value of Sub Context : $contentSubText"
            )
            Log.d(FrogoNotification::class.java.simpleName, "Value of Large Icon : $largeIcon")
            Log.d(FrogoNotification::class.java.simpleName, "Value of Auto Cancel : $autoCancel")

            return this
        }

        override fun build(): Inject {

            val notificationBuilder = NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(smallIcon)

            if (largeIcon != null) {
                notificationBuilder.setLargeIcon(
                    BitmapFactory.decodeResource(
                        context.resources,
                        largeIcon!!
                    )
                )
            }

            if (pendingIntent != null) {
                notificationBuilder.setContentIntent(pendingIntent)
            }

            if (contentTitle != null) {
                notificationBuilder.setContentTitle(contentTitle)
            }

            if (contentText != null) {
                notificationBuilder.setContentText(contentText)
            }

            if (contentSubText != null) {
                notificationBuilder.setSubText(contentSubText)
            }

            if (autoCancel != null) {
                notificationBuilder.setAutoCancel(autoCancel!!)
            }

            if (remoteInput != null && notificationAction != null) {
                notificationBuilder.addAction(notificationAction)
            }

            if (groupKey != null) {
                notificationBuilder.setGroup(groupKey)
            }

            if (isGroupSummary) {
                notificationBuilder.setGroupSummary(true)
            }

            if (inboxStyle != null) {
                notificationBuilder.setStyle(inboxStyle)
            }

            if (style != null) {
                notificationBuilder.setStyle(style)
            }

            /*
                Android oreo version need add notification channel
            */

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                /* Create or update */

                val channel = NotificationChannel(
                    channel_id,
                    channel_name,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = channel_name

                if (vibration) {
                    channel.enableVibration(true)
                    channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                }

                notificationBuilder.setChannelId(channel_id)
                notificationManager.createNotificationChannel(channel)
            }

            notification = notificationBuilder.build()

            return this
        }

        override fun launch(notificationId: Int) {
            this.notification_id = notificationId
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Value of Notification_ID : $notificationId"
            )
            Log.d(
                FrogoNotification::class.java.simpleName,
                "Successfully Notify Frogo Notification"
            )
            notificationManager.notify(notification_id, notification)
        }

    }


}