package com.frogobox.appnotif.custom

import android.content.Intent
import android.os.Bundle
import com.frogobox.appnotif.core.BaseActivity
import com.frogobox.appnotif.databinding.ActivityCustomNotifBinding

class CustomNotifActivity : BaseActivity<ActivityCustomNotifBinding>() {

    override fun setupViewBinding(): ActivityCustomNotifBinding {
        return ActivityCustomNotifBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupDetailActivity("Custom Notif")
        binding.buttonShowNotification.setOnClickListener {
            startService(Intent(this, NotificationService::class.java))
        }

    }

}