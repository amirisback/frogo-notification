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
interface IFNActionRemoteInput {

    // Initialize for Remote Input Result Key
    fun setRemoteInputResultKey(): String

    // Initialize for Remote Input Label
    fun setRemoteInputLabel(): String

    // Initialize for Action Icon
    fun setActionIcon(): Int

    // Initialize for Action Title
    fun setActionTitle(): String

    // Initialize for Action Intent
    fun setActionIntent(): PendingIntent?

    // Initialize for Action Allow Generated Replies
    fun setAllowGeneratedReplies(): Boolean

}