package com.frogobox.notification.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.frogobox.notification.R

class CustomNotifActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_custom_notif)
        val buttonNotif = findViewById<Button>(R.id.button_show_notification)
        buttonNotif.setOnClickListener {
            startService(Intent(this, NotificationService::class.java))
        }
    }
}