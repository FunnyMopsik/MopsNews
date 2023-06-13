package com.newsapp.mopsnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.newsapp.mopsnews.R

class Settings : AppCompatActivity() {

    lateinit var news : ImageView
    lateinit var account : ImageView
    lateinit var alerts : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        news = findViewById(R.id.news)
        account = findViewById(R.id.account)
        alerts = findViewById(R.id.send)

        news.setOnClickListener {
            val intent = Intent(this@Settings, ShowNews::class.java)
            startActivity(intent)
        }
        account.setOnClickListener {
            val intent = Intent(this@Settings, Account::class.java)
            startActivity(intent)
        }
        alerts.setOnClickListener {
            val intent = Intent(this@Settings, Alert::class.java)
            startActivity(intent)
        }
    }
}