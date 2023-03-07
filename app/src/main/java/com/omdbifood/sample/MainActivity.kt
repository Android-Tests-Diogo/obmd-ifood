package com.omdbifood.sample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omdbifood.home.presentation.view.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}
