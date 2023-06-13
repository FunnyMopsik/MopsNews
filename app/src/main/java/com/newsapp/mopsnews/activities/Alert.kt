package com.newsapp.mopsnews.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences.Editor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import com.newsapp.mopsnews.R

class Alert : AppCompatActivity() {

    lateinit var news : ImageView
    lateinit var account : ImageView
    lateinit var settings : ImageView
    lateinit var politic : Switch
    lateinit var humor: Switch
    lateinit var sport : Switch
    lateinit var games: Switch


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert)

        news = findViewById(R.id.news)
        account = findViewById(R.id.account)
        settings = findViewById(R.id.settings)
        politic = findViewById(R.id.Spolitic)
        humor = findViewById(R.id.Shumor)
        sport = findViewById(R.id.Ssport)
        games = findViewById(R.id.Sgame)
        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val sportS = sharedPreferences.getBoolean("sport",false)
        val humorS = sharedPreferences.getBoolean("humor",false)
        val gamesS = sharedPreferences.getBoolean("games",false)
        val politicS = sharedPreferences.getBoolean("politic",false)
        if(sportS == true){
            sport.isChecked =true
        }
        if(humorS == true){
            humor.isChecked = true
        }
        if(gamesS == true){
            games.isChecked = true
        }
        if(politicS == true){
            politic.isChecked = true
        }

        news.setOnClickListener {
            val intent = Intent(this@Alert, ShowNews::class.java)
            startActivity(intent)
            checkalerts(editor)
            finish()
        }
        account.setOnClickListener {
            val intent = Intent(this@Alert, Account::class.java)
            startActivity(intent)
            checkalerts(editor)
            finish()
        }
        settings.setOnClickListener {
            val intent = Intent(this@Alert, Settings::class.java)
            startActivity(intent)
            checkalerts(editor)
            finish()
        }


        }

    fun checkalerts(editor: Editor){
        if(politic.isChecked){
            editor.putBoolean("politic", true).apply()
        }else{
            editor.putBoolean("politic", false).apply()
        }
        if(humor.isChecked){
            editor.putBoolean("humor", true).apply()
        }else{
            editor.putBoolean("humor", false).apply()
        }
        if(games.isChecked){
            editor.putBoolean("games", true).apply()
        }else{
            editor.putBoolean("games", false).apply()
        }
        if (sport.isChecked){
            editor.putBoolean("sport", true).apply()
        }else{
            editor.putBoolean("sport", false).apply()
        }
    }
}



