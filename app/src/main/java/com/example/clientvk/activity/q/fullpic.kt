package com.example.clientvk.activity.q

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.clientvk.R
import com.example.clientvk.adapters.fullpicAdapter

class fullpic : AppCompatActivity() {
    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var fpic: ViewPager2

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullpic)

        Toast.makeText(this, R.string.SlideToast, Toast.LENGTH_SHORT).show()
        fpic = findViewById(R.id.fpic)
        val imageResource = intent.getStringArrayListExtra(R.string.linkpic.toString())
        fpic.adapter = fullpicAdapter(imageResource!!)
        fpic.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        supportActionBar?.hide()
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        fpic.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                fpic.currentItem = position
            }
        })

        gestureDetector = GestureDetectorCompat(this, GestureListener())

        fpic.setOnTouchListener { v, event ->
            gestureDetector.onTouchEvent(event)
        }

    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (Math.abs(e1.y - e2.y) > Math.abs(e1.x - e2.x)) {
                if (e1.y - e2.y > 120 && Math.abs(velocityY) > 200) {
                    onBackPressed()
                    return true
                } else if (e2.y - e1.y > 120 && Math.abs(velocityY) > 200) {
                    onBackPressed()
                    return true
                }
            }
            return false
        }
    }
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event!!)
        return super.dispatchTouchEvent(event)
    }
}