package com.oleksii.android_app_006_self_promo

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.oleksii.android_app_006_self_promo.databinding.ActivityMainBinding
import com.oleksii.android_app_006_self_promo.databinding.ActivityMessagePreviewBinding

class MessagePreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMessagePreviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagePreviewBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        val message = intent.getStringExtra("MESSAGE")

        binding.textViewPreviewMessage.setText(message)

    }

}