package com.newsapp.mopsnews.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.newsapp.mopsnews.R
import com.newsapp.mopsnews.service.NewsParsingService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val serviceIntent = Intent(this, NewsParsingService::class.java)
        startService(serviceIntent)
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        if (sharedPreferences.contains("logined")) {
            val logined = sharedPreferences.getBoolean("logined", false)
            if (logined == true) {
                setContentView(R.layout.activity_show_news)
                val intent = Intent(this@MainActivity, ShowNews::class.java)
                startActivity(intent)

            } else {
                setContentView(R.layout.activity_login)
                val intent = Intent(this@MainActivity, Login::class.java)
                startActivity(intent)

            }
        } else {
            val editor = sharedPreferences.edit()
            editor.putBoolean("logined", false)
            editor.putBoolean("politic", false)
            editor.putBoolean("sport", false)
            editor.putBoolean("humor", false)
            editor.putBoolean("games", false)
            editor.apply()
            editor.commit()
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
            finish()

        }
    }
}