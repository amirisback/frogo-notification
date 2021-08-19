package com.frogobox.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.frogobox.notification.core.Constant
import com.frogobox.notification.core.FrogoNotifActionRemoteInputListener
import com.frogobox.notification.core.FrogoNotifCustomContentViewListener
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

        private val TAG = FrogoNotification::class.java.simpleName

        private val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        private lateinit var notification: Notification

        private var smallIcon: Int = R.drawable.ic_frogo_notif

        private var remoteInput: RemoteInput? = null
        private var notificationAction: NotificationCompat.Action? = null
        private var inboxStyle: NotificationCompat.InboxStyle? = null
        private var style: NotificationCompat.Style? = null
        private var pendingIntent: PendingIntent? = null

        private var contentTitle: CharSequence? = null
        private var contentText: CharSequence? = null
        private var contentSubText: CharSequence? = null

        private var collapsedView: RemoteViews? = null
        private var expandedView: RemoteViews? = null

        private var largeIcon: Int? = null
        private var groupKey: String? = null

        private var isAutoCancel = false
        private var isShowWhen = false
        private var isVibration = false
        private var isGroupSummary = false
        private var isCustomContentView = false
        private var isBigCustomContentView = false

        private var notificationID: Int = Constant.FROGO_NOTIFICATION_ID
        private var channelID: String = Constant.FROGO_CHANNEL_ID
        private var channelName: String = Constant.FROGO_CHANNEL_NAME

        init {
            Log.d(TAG, "Initialize Context and Declare Notification Manager")
        }

        override fun setChannelId(channelId: String): Inject {
            this.channelID = channelId
            Log.d(TAG, "Value of Channel_ID : $channelId")
            return this
        }

        override fun setChannelName(channelName: String): Inject {
            this.channelName = channelName
            Log.d(TAG, "Value of Channel Name : $channelName")
            return this
        }

        override fun setContentIntent(intent: PendingIntent): Inject {
            this.pendingIntent = intent
            Log.d(TAG, "Value of Intent : $intent")
            return this
        }

        override fun setSmallIcon(smallIcon: Int): Inject {
            this.smallIcon = smallIcon
            Log.d(TAG, "Value of Small Icon : $smallIcon")
            return this
        }

        override fun setLargeIcon(largeIcon: Int): Inject {
            this.largeIcon = largeIcon
            Log.d(TAG, "Value of Large Icon : $largeIcon")
            return this
        }

        override fun setContentTitle(contentTitle: CharSequence): Inject {
            this.contentTitle = contentTitle
            Log.d(TAG, "Value of Content Title : $contentTitle")
            return this
        }

        override fun setContentText(contentText: CharSequence): Inject {
            this.contentText = contentText
            Log.d(TAG, "Value of Content Text : $contentText")
            return this
        }

        override fun setSubText(contentSubText: CharSequence): Inject {
            this.contentSubText = contentSubText
            Log.d(TAG, "Value of Content Sub Text : $contentSubText")
            return this
        }

        override fun setupAutoCancel(): Inject {
            this.isAutoCancel = true
            Log.d(TAG, "Value of Auto Cancel : $isAutoCancel")
            return this
        }

        override fun setStyle(style: NotificationCompat.Style): Inject {
            this.style = style
            Log.d(TAG, "Value of Style : $style")
            return this
        }

        override fun setupShowWhen(): Inject {
            this.isShowWhen = true
            Log.d(TAG, "Value of Show When : $isShowWhen")
            return this
        }

        override fun setGroup(groupKey: String): Inject {
            this.groupKey = groupKey
            Log.d(TAG, "Value of Show When : $groupKey")
            return this
        }

        override fun setGroupSummary(): Inject {
            this.isGroupSummary = true
            Log.d(TAG, "Value of GroupSummary : $isGroupSummary")
            return this
        }

        override fun setupWithVibration(): Inject {
            this.isVibration = true
            Log.d(TAG, "Value of Vibration : $isVibration")
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

            Log.d(TAG, "Setup Action Remote Input")
            Log.d(TAG, "RemoteInput (Key) : ${listener.setRemoteInputResultKey()}")
            Log.d(TAG, "RemoteInput (Label) : ${listener.setRemoteInputLabel()}")
            Log.d(TAG, "Action (Icon) : ${listener.setActionIcon()}")
            Log.d(TAG, "Action (Title) : ${listener.setActionTitle()}")
            Log.d(TAG, "Action (Intent) : ${listener.setActionIntent()}")
            Log.d(TAG, "Action (Generate Replies) : ${listener.setAllowGeneratedReplies()}")
            return this
        }

        override fun setupInboxStyle(listener: FrogoNotifInboxStyleListener): Inject {
            inboxStyle = NotificationCompat.InboxStyle()
                .addLine(listener.addLine1())
                .addLine(listener.addLine2())
                .setBigContentTitle(listener.setBigContentTitle())
                .setSummaryText(listener.setSummaryText())

            Log.d(TAG, "Using Custom Content View")
            Log.d(TAG, "Inbox Syle (Add Line) : ${listener.addLine1()}")
            Log.d(TAG, "Inbox Syle (Add Line) : ${listener.addLine2()}")
            Log.d(TAG, "Inbox Syle (Big Content Title) : ${listener.setBigContentTitle()}")
            Log.d(TAG, "Inbox Syle (Summary Text) : ${listener.setSummaryText()}")
            return this
        }

        override fun setCustomContentView(listener: FrogoNotifCustomContentViewListener): Inject {
            isCustomContentView = true
            collapsedView = RemoteViews(context.packageName, listener.setupCustomView())
            listener.setupComponent(context, collapsedView!!)

            Log.d(TAG, "Using Custom Content View")
            Log.d(TAG, "Custom Content View : $isCustomContentView")
            Log.d(TAG, "Layout Custom Content View : ${listener.setupCustomView()}")
            return this
        }

        override fun setCustomBigContentView(listener: FrogoNotifCustomContentViewListener): Inject {
            isBigCustomContentView = true
            expandedView = RemoteViews(context.packageName, listener.setupCustomView())
            listener.setupComponent(context, expandedView!!)

            Log.d(TAG, "Using Big Custom Content View")
            Log.d(TAG, "Big Custom Content View : $isCustomContentView")
            Log.d(TAG, "Layout Big Custom Content View : ${listener.setupCustomView()}")
            return this
        }

        override fun setupWithFrogoStyle(): Inject {
            this.contentTitle = context.resources.getString(R.string.frogo_content_title)
            this.contentText = context.resources.getString(R.string.frogo_content_text)
            this.contentSubText = context.resources.getString(R.string.frogo_subtext)
            this.largeIcon = R.drawable.ic_frogo_notif
            this.isAutoCancel = false

            Log.d(TAG, "Using Frogo Notification Template")
            Log.d(TAG, "Value of Content Title : $contentTitle")
            Log.d(TAG, "Value of Content Text : $contentText")
            Log.d(TAG, "Value of Sub Context : $contentSubText")
            Log.d(TAG, "Value of Large Icon : $largeIcon")
            Log.d(TAG, "Value of Auto Cancel : $isAutoCancel")
            return this
        }

        override fun build(): Inject {

            val notificationBuilder = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(smallIcon)

            if (largeIcon != null) {
                notificationBuilder.setLargeIcon(
                    BitmapFactory.decodeResource(context.resources, largeIcon!!)
                )
            }

            if (!isCustomContentView && !isBigCustomContentView) {
                if (contentTitle != null) {
                    notificationBuilder.setContentTitle(contentTitle)
                }

                if (contentText != null) {
                    notificationBuilder.setContentText(contentText)
                }

                if (contentSubText != null) {
                    notificationBuilder.setSubText(contentSubText)
                }
            }

            if (isCustomContentView) {
                notificationBuilder.setCustomContentView(collapsedView)
            }

            if (isBigCustomContentView) {
                notificationBuilder.setCustomBigContentView(expandedView)
            }

            if (pendingIntent != null) {
                notificationBuilder.setContentIntent(pendingIntent)
            }

            if (remoteInput != null && notificationAction != null) {
                notificationBuilder.addAction(notificationAction)
            }

            if (groupKey != null) {
                notificationBuilder.setGroup(groupKey)
            }

            if (inboxStyle != null) {
                notificationBuilder.setStyle(inboxStyle)
            }

            if (style != null) {
                notificationBuilder.setStyle(style)
            }

            if (isGroupSummary) {
                notificationBuilder.setGroupSummary(isGroupSummary)
            }

            if (isAutoCancel) {
                notificationBuilder.setAutoCancel(isAutoCancel)
            }

            /*
                Android oreo version need add notification channel
            */

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                /* Create or update */

                val channel = NotificationChannel(
                    channelID,
                    channelName,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = channelName

                if (isVibration) {
                    channel.enableVibration(true)
                    channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                }

                notificationBuilder.setChannelId(channelID)
                notificationManager.createNotificationChannel(channel)
            }

            notification = notificationBuilder.build()

            return this
        }

        override fun launch(notificationId: Int) {
            this.notificationID = notificationId
            Log.d(TAG, "Value of Notification_ID : $notificationId")
            Log.d(TAG, "Successfully Notify Frogo Notification")
            notificationManager.notify(notificationID, notification)
        }

    }


}