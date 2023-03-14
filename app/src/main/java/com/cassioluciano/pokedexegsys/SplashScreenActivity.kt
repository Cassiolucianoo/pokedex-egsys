package com.cassioluciano.pokedexegsys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager



import android.graphics.ImageDecoder
import android.graphics.drawable.AnimatedImageDrawable


import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        CoroutineScope(SupervisorJob()).launch(Dispatchers.IO) {
            val source = ImageDecoder.createSource(
                resources, R.drawable.pokeball
            )
            val drawable = ImageDecoder.decodeDrawable(source)

            val imageView = findViewById<ImageView>(R.id.image_view)
            imageView.post {
                imageView.setImageDrawable(drawable)
                (drawable as? AnimatedImageDrawable)?.start()

            }
        }


        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash_screen)

        //4second splash time
        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            //finish this activity
            finish()
        },5000)
    }
}