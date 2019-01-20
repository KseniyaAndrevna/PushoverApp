package com.kseniyaa.pushoverapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kseniyaa.pushoverapp.utils.getFromSP

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (getFromSP("auth", this)!! == "true") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, AutchActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
