package com.oleksii.android_app_006_self_promo

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
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

        binding.buttonSubmit.setOnClickListener {
            displayFireworks()
        }

    }

    fun displayFireworks() {
        val rootLayout = binding.root as ViewGroup

        val fireworksView = FireworksView(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            elevation = 10f  // render above other views
        }
        rootLayout.addView(fireworksView)

        // Launch bursts at random positions across the screen
        val width = rootLayout.width.toFloat()
        val height = rootLayout.height.toFloat()
        val handler = Handler(Looper.getMainLooper())

        listOf(
            0L to Pair(width * 0.3f, height * 0.3f),
            300L to Pair(width * 0.7f, height * 0.4f),
            600L to Pair(width * 0.5f, height * 0.25f)
        ).forEach { (delay, pos) ->
            handler.postDelayed({
                fireworksView.launch(pos.first, pos.second)
            }, delay)
        }

        // Remove view after animation completes
        handler.postDelayed({
            rootLayout.removeView(fireworksView)
        }, 3000)    }
}