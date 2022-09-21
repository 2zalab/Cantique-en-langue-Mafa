package com.sintel.cantiquesenmafa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class HomeActivity : AppCompatActivity() {

    internal val time_out=3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Handler().postDelayed(
            {
                startActivity(Intent(this@HomeActivity,MainActivity::class.java))
                finish()
            },time_out.toLong()
        )
    }
}