package com.frogobox.notification.simple

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

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

class MainReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Image clicked", Toast.LENGTH_SHORT).show()
        NotificationManagerCompat.from(context).cancel(1)
    }

}