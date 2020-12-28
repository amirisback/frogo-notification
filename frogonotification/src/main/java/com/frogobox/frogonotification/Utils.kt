package com.frogobox.frogonotification

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
object Utils {

    const val FROGO_NOTIFICATION_ID = 0
    fun FROGO_CHANNEL_ID(notificationID: Int) = "FROGO_CHANNEL_ID_${notificationID.plus(1)}"
    fun FROGO_CHANNEL_NAME(notificationID: Int) = "FROGO_CHANNEL_NAME_${FROGO_CHANNEL_ID(notificationID)}_FROGO"

}