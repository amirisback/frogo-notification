package com.frogobox.frogonotification

import android.app.Notification
import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import com.frogobox.frogonotification.attr.IFNActionRemoteInput
import com.frogobox.frogonotification.attr.IFNInboxStyle

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

    //
    fun setChannelId(channelId: String): FrogoNotification.Inject

    //
    fun setChannelName(channelName: String): FrogoNotification.Inject

    //
    fun setContentIntent(intent: PendingIntent): FrogoNotification.Inject

    //
    fun setSmallIcon(smallIcon: Int): FrogoNotification.Inject

    //
    fun setLargeIcon(largeIcon: Int): FrogoNotification.Inject

    //
    fun setContentTitle(contentTitle: CharSequence): FrogoNotification.Inject

    //
    fun setContentText(contentText: CharSequence): FrogoNotification.Inject

    //
    fun setSubText(contentSubText: CharSequence): FrogoNotification.Inject

    //
    fun setAutoCancel(autoCancel: Boolean): FrogoNotification.Inject

    //
    fun setStyle(style: NotificationCompat.Style): FrogoNotification.Inject

    //
    fun showWhen(show: Boolean): FrogoNotification.Inject

    //
    fun setGroup(groupKey: String): FrogoNotification.Inject

    //
    fun setGroupSummary(): FrogoNotification.Inject

    //
    fun setupWithVibration(): FrogoNotification.Inject

    //
    fun setupActionRemoteInput(listenerIFNActionRemoteInput: IFNActionRemoteInput): FrogoNotification.Inject

    //
    fun setupInboxStyle(listenerIFNInboxStyle: IFNInboxStyle): FrogoNotification.Inject

    //
    fun setupWithFrogoStyle(): FrogoNotification.Inject

    //
    fun build(): FrogoNotification.Inject

    //
    fun launch(notificationId: Int)

}