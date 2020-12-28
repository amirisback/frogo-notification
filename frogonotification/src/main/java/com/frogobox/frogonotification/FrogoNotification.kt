package com.frogobox.frogonotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat

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

    class Inject {

        private lateinit var context: Context
        private lateinit var contentTitle: CharSequence
        private lateinit var contentText: CharSequence
        private lateinit var contentSubText: CharSequence
        private lateinit var resources: Resources
        private lateinit var pendingIntent: PendingIntent
        private lateinit var notificationManager: NotificationManager
        private lateinit var notification: Notification

        private var smallIcon: Int = 0
        private var largeIcon: Int = 0
        private var autoCancel: Boolean = false

        private var notification_id: Int = 0
        private var channel_id: String = ""
        private var channel_name: String = ""

        constructor(){
        }

        constructor(context: Context) {
            this.context = context
            this.notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        fun setResoures(resources: Resources): Inject {
            this.resources = resources
            return this
        }

        fun setNotificationId(notificationId: Int) : Inject {
            this.notification_id = notification_id
            return this
        }

        fun setChannelId(channelId: String): Inject {
            this.channel_id = channelId
            return this
        }

        fun setChannelName(channelName: String): Inject {
            this.channel_name = channelName
            return this
        }

        fun setContentIntent(intent: PendingIntent): Inject {
            this.pendingIntent = intent
            return this
        }

        fun setSmallIcon(smallIcon: Int): Inject {
            this.smallIcon = smallIcon
            return this
        }

        fun setLargeIcon(largeIcon: Int): Inject {
            this.largeIcon = largeIcon
            return this
        }

        fun setContentTitle(contentTitle: CharSequence): Inject {
            this.contentTitle = contentTitle
            return this
        }

        fun setContentText(contentText: CharSequence): Inject {
            this.contentText = contentText
            return this
        }

        fun setSubText(contentSubText: CharSequence): Inject {
            this.contentSubText = contentSubText
            return this
        }

        fun setAutoCancel(autoCancel: Boolean): Inject {
            this.autoCancel = autoCancel
            return this
        }

        fun getNotificationManager() : NotificationManager {
            return notificationManager
        }

        fun getNotificationId() : Int {
            return notification_id
        }

        fun getNotification() : Notification {
            return notification
        }

        fun build(): Inject {

            val mBuilder = NotificationCompat.Builder(context, channel_id)
                .setContentIntent(pendingIntent)
                .setSmallIcon(smallIcon)
                .setLargeIcon(BitmapFactory.decodeResource(resources, largeIcon))
                .setContentTitle(contentTitle)
                .setContentText(contentText)
                .setSubText(contentSubText)
                .setAutoCancel(autoCancel)

            /*
                Android oreo version need add notification channel
            */

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                /* Create or update. */

                val channel = NotificationChannel(
                    channel_id,
                    channel_name,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.description = channel_name
                mBuilder.setChannelId(channel_id)
                notificationManager.createNotificationChannel(channel)
            }

            notification = mBuilder.build()

            return this
        }

        fun launch() {
            notificationManager.notify(notification_id, notification)
        }

    }



}