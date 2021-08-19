package com.frogobox.notification.core

import android.content.Context
import android.widget.RemoteViews

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
interface FrogoNotifCustomContentViewListener {

    fun setupCustomView() : Int

    fun setupComponent(context: Context, customView: RemoteViews)

}