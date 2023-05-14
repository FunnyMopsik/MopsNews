package com.newsapp.mopsnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.newsapp.mopsnews.R

class ChangePass : AppCompatActivity() {

    lateinit var password: EditText
    lateinit var Cpass : EditText
    lateinit var btn : Button
    lateinit var back : TextView
    lateinit var  firebase: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pass)


        password = findViewById(R.id.password)
        Cpass = findViewById(R.id.Cpassword)
        btn  = findViewById(R.id.changeBtn)
        back = findViewById(R.id.back)
        firebase = FirebaseAuth.getInstance()
        val user : FirebaseUser? = firebase.currentUser

        btn.setOnClickListener {
            if(password.text.toString().isNotEmpty() && Cpass.text.toString().isNotEmpty()){
                if (password.text.toString() == Cpass.text.toString()){
                    user?.updatePassword(password.text.toString())
                    user?.let { it1 ->
                        firebase.updateCurrentUser(it1).addOnCompleteListener {
                            if(it.isSuccessful){
                                Toast.makeText(this,"Пароль змінено",Toast.LENGTH_LONG).show()
                                setContentView(R.layout.activity_account)
                                val intent = Intent(this@ChangePass, Account::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this,"Помилка",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(this,"Поля не співпадають",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Не всі поля заповнені",Toast.LENGTH_LONG).show()
            }
        }

    }
}