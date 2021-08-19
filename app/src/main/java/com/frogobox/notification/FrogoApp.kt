package com.frogobox.notification

import android.app.Application
import android.os.Build
import android.app.NotificationChannel
import android.app.NotificationManager

/*
 * Created by faisalamir on 19/08/21
 * FrogoNotification
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

class FrogoApp : Application() {

    companion object {
        const val NOTIFICATION_ID = 2
        const val CHANNEL_ID = "CHANNEL_$NOTIFICATION_ID"
        const val CHANNEL_NAME = "CHANNEL_NAME_$CHANNEL_ID"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }
    }

}