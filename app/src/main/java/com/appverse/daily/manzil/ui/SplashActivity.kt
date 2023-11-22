package com.appverse.daily.manzil.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.appverse.daily.manzil.MainActivity
import com.appverse.daily.manzil.databinding.ActivitySplashscreenBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding
    private  var TAG = "SplashActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_splashscreen)

        val alphaAnimation = AlphaAnimation(0.0f, 0.7f)
        alphaAnimation.duration = 2000

        val alphaAnimation2 = AlphaAnimation(0.0f, 1f)
        alphaAnimation2.duration = 2000

        binding.logoIV.startAnimation(alphaAnimation)
        alphaAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                Log.d("SplashActivity", "onAnimationEnd: ")
                binding.title.startAnimation(alphaAnimation2)
                binding.title.visibility = View.VISIBLE

                goNext()

            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    private fun goNext() {
        //hold screen for 3 to 5 seconds and move to main screen
        Handler(Looper.getMainLooper()).postDelayed({ //go to next activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 3000) // for 3

    }
}