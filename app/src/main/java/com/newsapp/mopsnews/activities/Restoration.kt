package com.newsapp.mopsnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.newsapp.mopsnews.R

class Restoration : AppCompatActivity() {

    lateinit var firebase: FirebaseAuth
    lateinit var resBtn : Button

    lateinit var email : EditText

    lateinit var login: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restoration)

        firebase = FirebaseAuth.getInstance()
        resBtn = findViewById(R.id.resbtn)
        login = findViewById(R.id.login)
        email = findViewById(R.id.email)

        login.setOnClickListener {
            setContentView(R.layout.activity_login)
            val intent = Intent(this@Restoration, Login::class.java)
            startActivity(intent)

        }

        resBtn.setOnClickListener {
            if (email.text.toString().isNotEmpty()){
                firebase.sendPasswordResetEmail(email.text.toString().lowercase()).addOnCompleteListener {
                    if(it.isSuccessful){

                        setContentView(R.layout.activity_login)
                        val intent = Intent(this@Restoration, Login::class.java)
                        startActivity(intent)
                        Toast.makeText(this, "Перевірте свій email", Toast.LENGTH_LONG).show()

                    }else{
                        Toast.makeText(this, "Помилка", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                Toast.makeText(this, "не всі поля заповнені", Toast.LENGTH_LONG).show()
            }
        }



    }
}