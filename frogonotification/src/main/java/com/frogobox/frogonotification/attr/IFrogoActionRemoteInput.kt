package com.frogobox.frogonotification.attr

import android.app.PendingIntent

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
interface IFrogoActionRemoteInput {

    fun setRemoteInputResultKey(): String

    fun setRemoteInputLabel(): String

    fun setActionIcon(): Int

    fun setActionTitle(): String

    fun setActionIntent(): PendingIntent?

    fun setAllowGeneratedReplies(): Boolean

}