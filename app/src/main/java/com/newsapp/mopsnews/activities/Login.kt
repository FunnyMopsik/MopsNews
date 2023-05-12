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




class Login : AppCompatActivity() {

    lateinit var firebase: FirebaseAuth
    lateinit var loginBtn : Button

    lateinit var email : EditText
    lateinit var  password : EditText
    lateinit var reg: TextView
    lateinit var  res: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginbtn)
        reg = findViewById(R.id.reg)
        res = findViewById(R.id.forgotpass)
        firebase = FirebaseAuth.getInstance()
        loginBtn.setOnClickListener {
            if(email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()){
                firebase.signInWithEmailAndPassword(email.text.toString().lowercase(),password.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){

                        setContentView(R.layout.activity_show_news)
                        val intent = Intent(this@Login, ShowNews::class.java)
                        startActivity(intent)

                    }else{
                        Toast.makeText(this, "Дані не вірні", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "не всі поля заповнені", Toast.LENGTH_LONG).show()
            }
        }

        reg.setOnClickListener {
            setContentView(R.layout.activity_regestration)
            val intent = Intent(this@Login, Regestration::class.java)
            startActivity(intent)

        }
        res.setOnClickListener {
            setContentView(R.layout.activity_restoration)
            val intent = Intent(this@Login, Restoration::class.java)
            startActivity(intent)

        }





    }
}