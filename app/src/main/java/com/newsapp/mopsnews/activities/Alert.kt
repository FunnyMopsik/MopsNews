package com.newsapp.mopsnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.newsapp.mopsnews.R

class Alert : AppCompatActivity() {

    lateinit var news : ImageView
    lateinit var account : ImageView
    lateinit var settings : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        news = findViewById(R.id.news)
        account = findViewById(R.id.account)
        settings = findViewById(R.id.settings)

        news.setOnClickListener {
            val intent = Intent(this@Alert, ShowNews::class.java)
            startActivity(intent)
        }
        account.setOnClickListener {
            val intent = Intent(this@Alert, Account::class.java)
            startActivity(intent)
        }
        settings.setOnClickListener {
            val intent = Intent(this@Alert, Settings::class.java)
            startActivity(intent)
        }
    }
}