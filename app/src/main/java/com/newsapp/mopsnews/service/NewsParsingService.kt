package com.newsapp.mopsnews.service

import android.app.Service
import android.app.Service.START_STICKY
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.database.*
import com.newsapp.mopsnews.notif.Notif

class NewsParsingService : Service() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var newsArrayList: ArrayList<News>
    private var currentNewsCount = 0
    lateinit var  notif: Notif

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        notif = Notif(this)
        newsArrayList = ArrayList()
        dbRef = FirebaseDatabase.getInstance().getReference("news")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val newNewsCount = snapshot.childrenCount.toInt()

                    if (newNewsCount > currentNewsCount) {
                        for (newsSnapshot in snapshot.children) {
                            val news = newsSnapshot.getValue(News::class.java)
                            newsArrayList.add(news!!)
                        }

                        val newNews = newsArrayList.subList(currentNewsCount, newNewsCount)
                        currentNewsCount = newNewsCount

                        val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

                        val sportS = sharedPreferences.getBoolean("sport",false)
                        val humorS = sharedPreferences.getBoolean("humor",false)
                        val gamesS = sharedPreferences.getBoolean("games",false)
                        val politicS = sharedPreferences.getBoolean("politic",false)
                        if(sportS == true){
                            if(newsArrayList[currentNewsCount-1].category == "Спорт"){
                               notif.showNotification(newsArrayList[currentNewsCount-1].hendler.toString())
                            }
                        }
                        if(humorS == true){
                            if(newsArrayList[currentNewsCount-1].category == "Гумор"){
                                notif.showNotification(newsArrayList[currentNewsCount-1].hendler.toString())
                            }
                        }
                        if(gamesS == true){
                            if(newsArrayList[currentNewsCount-1].category == "Ігри"){
                                notif.showNotification(newsArrayList[currentNewsCount-1].hendler.toString())
                            }
                        }
                        if(politicS == true){
                            if(newsArrayList[currentNewsCount-1].category == "Політика"){
                                notif.showNotification(newsArrayList[currentNewsCount-1].hendler.toString())
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Обробка випадку відміни події, якщо потрібно
            }
        })

        // Повертаємо флаг, що сервіс повинен бути перезапущений, якщо його зупинили
        return START_STICKY
    }

    data class News(
        var hendler: String? = null,
        var image: String? = null,
        var link: String? = null,
        val category: String? = null,
        val author: String? = null
    )
}