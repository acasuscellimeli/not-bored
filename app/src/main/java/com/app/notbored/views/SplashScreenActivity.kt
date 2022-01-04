package com.app.notbored.views
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.notbored.databinding.ActivitySplashScreenBinding
import android.content.Intent
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, StartActivity::class.java))
        }, 1500)

    }
}