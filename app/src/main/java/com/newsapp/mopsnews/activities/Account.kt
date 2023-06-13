package com.newsapp.mopsnews.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.newsapp.mopsnews.R

class Account : AppCompatActivity() {

    lateinit var news : ImageView
    lateinit var check : ImageView
    lateinit var settings : ImageView
    lateinit var logout : Button
    lateinit var changePass: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        news = findViewById(R.id.news)
        check = findViewById(R.id.send)
        settings = findViewById(R.id.settings)
        logout = findViewById(R.id.exitBtn)
        changePass = findViewById(R.id.changeBtn)

        news.setOnClickListener {
            val intent = Intent(this@Account, ShowNews::class.java)
            startActivity(intent)
        }
        check.setOnClickListener {
            val intent = Intent(this@Account, Alert::class.java)
            startActivity(intent)
        }
        settings.setOnClickListener {
            val intent = Intent(this@Account, Settings::class.java)
            startActivity(intent)
        }
        logout.setOnClickListener {
            val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putBoolean("logined", false).apply()
            val intent = Intent(this@Account, Login::class.java)
            startActivity(intent)
            finish()
        }
        changePass.setOnClickListener {
            val intent = Intent(this@Account, ChangePass::class.java)
            startActivity(intent)
        }



    }
}