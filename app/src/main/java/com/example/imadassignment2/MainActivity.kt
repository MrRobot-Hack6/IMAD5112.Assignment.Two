package com.example.imadassignment2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var txtDisplayQuestion : TextView
    lateinit var btnHack : Button
    lateinit var btnMyth : Button
    lateinit var btnNext : Button
    lateinit var btnBack : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        txtDisplayQuestion = findViewById(R.id.txtDisplayQuestion)
        btnHack = findViewById(R.id.btnHack)
        btnMyth = findViewById(R.id.btnMyth)
        btnNext = findViewById(R.id.btnNext)
        btnBack = findViewById(R.id.btnBack)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}