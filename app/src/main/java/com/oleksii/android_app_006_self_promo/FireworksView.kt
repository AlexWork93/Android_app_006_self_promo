package com.oleksii.android_app_006_self_promo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.view.View
import java.util.Random
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class FireworksView(context: Context) : View(context) {
    data class Particle(
        var x: Float, var y: Float,
        var vx: Float, var vy: Float,
        var color: Int, var alpha: Int = 255,
        var radius: Float = 8f
    )

    private val particles = mutableListOf<Particle>()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()
    private val handler = Handler(Looper.getMainLooper())
    private val colors = listOf(
        Color.RED, Color.YELLOW, Color.CYAN,
        Color.GREEN, Color.MAGENTA, Color.WHITE
    )

    fun launch(cx: Float, cy: Float) {
        repeat(120) {
            val angle = random.nextFloat() * 2 * PI.toFloat()
            val speed = random.nextFloat() * 18 + 4
            particles += Particle(
                x = cx, y = cy,
                vx = cos(angle) * speed,
                vy = sin(angle) * speed,
                color = colors[random.nextInt(colors.size)]
            )
        }
        startAnimation()
    }

    private fun startAnimation() {
        handler.post(object : Runnable {
            override fun run() {
                particles.forEach {
                    it.x += it.vx
                    it.y += it.vy
                    it.vy += 0.4f
                    it.alpha -= 5
                    it.radius *= 0.97f
                }
                particles.removeAll { it.alpha <= 0 }
                invalidate()
                if (particles.isNotEmpty()) handler.postDelayed(this, 16)
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        particles.forEach {
            paint.color = it.color
            paint.alpha = it.alpha
            canvas.drawCircle(it.x, it.y, it.radius, paint)
        }
    }
}