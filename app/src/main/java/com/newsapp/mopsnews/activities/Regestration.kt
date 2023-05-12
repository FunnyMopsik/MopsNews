package com.newsapp.mopsnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.newsapp.mopsnews.R

lateinit var firebase: FirebaseAuth
lateinit var regBtn : Button

lateinit var email : EditText
lateinit var  password : EditText
lateinit var  Cpassword : EditText
lateinit var login: TextView
lateinit var hint : TextView


class Regestration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regestration)

        firebase = FirebaseAuth.getInstance()
        regBtn = findViewById(R.id.regbtn)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        Cpassword = findViewById(R.id.Cpassword)
        login = findViewById(R.id.login)
        hint = findViewById(R.id.hint)

        regBtn.setOnClickListener {
            if(password.text.toString().isNotEmpty() && email.text.toString().isNotEmpty() && Cpassword.text.toString().isNotEmpty()) {
                if (isValidPassword(password.text.toString())) {
                    if (EmailValidator.isEmailValid(email.text.toString().lowercase())) {


                    if (password.text.toString() == Cpassword.text.toString()) {
                        firebase.createUserWithEmailAndPassword(
                            email.text.toString().lowercase(),
                            password.text.toString()
                        ).addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    "Акаунт успішно зареєстровано",
                                    Toast.LENGTH_LONG
                                ).show()
                                setContentView(R.layout.activity_login)
                                val intent = Intent(this@Regestration, Login::class.java)
                                startActivity(intent)

                            } else {
                                Toast.makeText(this, "Помилка", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Паролі не співпадають", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Пароль введено не за вимогами", Toast.LENGTH_LONG).show()
                    hint.visibility = View.VISIBLE
                }
            }else{
                    Toast.makeText(this, "Невірний email", Toast.LENGTH_LONG).show()
            }
            }
            else{
                Toast.makeText(this, "не всі поля заповнені", Toast.LENGTH_LONG).show()

            }
        }
        login.setOnClickListener{
            setContentView(R.layout.activity_login)
            val intent = Intent(this@Regestration, Login::class.java)
            startActivity(intent)
        }

    }

    internal fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.filter { it.isDigit() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

        return true
    }

    class EmailValidator {
        companion object {
            @JvmStatic
            val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";

            fun isEmailValid(email: String): Boolean {
                return EMAIL_REGEX.toRegex().matches(email);
            }
        }
    }

}