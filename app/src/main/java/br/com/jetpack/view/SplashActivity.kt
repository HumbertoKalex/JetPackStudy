package br.com.jetpack.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import br.com.jetpack.R

class SplashActivity : AppCompatActivity(R.layout.activity_splash){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timer = object: CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                val intent = Intent(applicationContext, ProductActivity::class.java)
                startActivity(intent)
            }
        }
        timer.start()
    }
}