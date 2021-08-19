package com.frogobox.notification

import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.frogobox.notification.core.FrogoNotifActionRemoteInputListener
import com.frogobox.notification.core.FrogoNotifInboxStyleListener

/*
 * Created by Faisal Amir on 28/12/2020
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
interface IFrogoNotification {

    // Intialize for Channel ID
    fun setChannelId(channelId: String): FrogoNotification.Inject

    // Initialize for Channel Name
    fun setChannelName(channelName: String): FrogoNotification.Inject

    // Initialize for Content Intent
    fun setContentIntent(intent: PendingIntent): FrogoNotification.Inject

    // Initialize for Small Icon
    fun setSmallIcon(smallIcon: Int): FrogoNotification.Inject

    // Initialize for Large Icon
    fun setLargeIcon(largeIcon: Int): FrogoNotification.Inject

    // Initialize for Content Title
    fun setContentTitle(contentTitle: CharSequence): FrogoNotification.Inject

    // Initialize for Content Text
    fun setContentText(contentText: CharSequence): FrogoNotification.Inject

    // Initialize for Sub Text
    fun setSubText(contentSubText: CharSequence): FrogoNotification.Inject

    // Initialize for Auto Cancel
    fun setAutoCancel(autoCancel: Boolean): FrogoNotification.Inject

    // Initialize for Style
    fun setStyle(style: NotificationCompat.Style): FrogoNotification.Inject

    // Initialize for Show When
    fun showWhen(show: Boolean): FrogoNotification.Inject

    // Initialize for Group
    fun setGroup(groupKey: String): FrogoNotification.Inject

    // Initialize for Group Summary
    fun setGroupSummary(): FrogoNotification.Inject

    // Setup Vibration
    fun setupWithVibration(): FrogoNotification.Inject

    // Setup Action Remote Input (Reply Usually)
    fun setupActionRemoteInput(listener: FrogoNotifActionRemoteInputListener): FrogoNotification.Inject

    // Setup Inbox Style (For Stack Notification)
    fun setupInboxStyle(listener: FrogoNotifInboxStyleListener): FrogoNotification.Inject

    // Setup Frogo Simple Style Notification
    fun setupWithFrogoStyle(): FrogoNotification.Inject

    // Build the Frogo Notification
    fun build(): FrogoNotification.Inject

    // Notify the Frogo Notification
    fun launch(notificationId: Int)

}