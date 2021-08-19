package com.frogobox.notification.core

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
interface FrogoNotifInboxStyleListener {

    // Initialize for Inbox Style Add Line
    fun addLine1(): String

    // Initialize for Inbox Style Add Line
    fun addLine2(): String

    // Initialize for Inbox Style Big Content Title
    fun setBigContentTitle(): String

    // Initialize for Inbox Style Summary Text
    fun setSummaryText(): String

}