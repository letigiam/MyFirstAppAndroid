package com.example.esameandroid_letiziagiammorcaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.esameandroid_letiziagimmorcaro.R

class MainActivity : AppCompatActivity() {

    private lateinit var login: TextView
    private lateinit var password: TextView
    private lateinit var loginBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            if(login.text.toString().equals( "admin") && password.text.toString().equals( "letizia.giammorcaro")){
                val intent = Intent(this, ActivitySecond::class.java)
                startActivity(intent)
            } else
             Toast.makeText(this, "Riprova", Toast.LENGTH_SHORT).show()
        }

    }
}