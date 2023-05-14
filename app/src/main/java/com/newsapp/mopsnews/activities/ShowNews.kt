package com.newsapp.mopsnews.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.newsapp.mopsnews.MyAdapter
import com.newsapp.mopsnews.News
import com.newsapp.mopsnews.R
import news_desc_web

class ShowNews : AppCompatActivity() {

    private  lateinit var dbref: DatabaseReference
    private lateinit var newsRecyclerView: RecyclerView
    private  lateinit var newsArrayList: ArrayList<News>
    lateinit var check: ImageView
    lateinit var settings : ImageView
    lateinit var account : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_news)

        newsRecyclerView = findViewById(R.id.newsList)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)
        newsRecyclerView.setHasFixedSize(true)

        newsArrayList = arrayListOf<News>()

        check = findViewById(R.id.send)
        settings = findViewById(R.id.settings)
        account = findViewById(R.id.account)

        check.setOnClickListener {
            val intent = Intent(this@ShowNews, Alert::class.java)
            startActivity(intent)
        }
        settings.setOnClickListener {
            val intent = Intent(this@ShowNews, Settings::class.java)
            startActivity(intent)
        }
        account.setOnClickListener {
            setContentView(R.layout.activity_account)
            val intent = Intent(this@ShowNews, Account::class.java)
            startActivity(intent)
        }

        getNewsData()
    }

    private fun getNewsData() {
        dbref = FirebaseDatabase.getInstance().getReference("news")
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(newsSnapshot in snapshot.children){
                        val news = newsSnapshot.getValue(News::class.java)
                        newsArrayList.add(news!!)
                    }

                    var adapter = MyAdapter(newsArrayList)
                    newsRecyclerView.adapter = adapter
                    adapter.setOnItemClickListener(object:MyAdapter.onItemClickListener{
                        @SuppressLint("SuspiciousIndentation")
                        override fun onItemClick(position: Int) {

                            var array = newsArrayList[position].toString()
                            var url:String = array.substring(array.indexOf("link="))
                            url =url.dropLast(3)
                            url = url.drop(5)
                            Log.i("enter to fun", url)

                            Log.i("activity is changet","true")

                            val intent = Intent(this@ShowNews,news_desc_web::class.java)
                            intent.putExtra("url",url)
                            startActivity(intent)



                        }
                    })
                }




            }


            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }


}