package com.cassioluciano.pokedexegsys.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.cassioluciano.pokedexegsys.R
import com.cassioluciano.pokedexegsys.view.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.activity_splash_screen)
        //4second splash time
        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            //finish this activity
            finish()
        }, 5000)
    }
}