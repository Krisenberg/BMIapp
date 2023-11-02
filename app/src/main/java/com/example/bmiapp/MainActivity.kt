package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            val resultTV = findViewById<TextView>(R.id.bmiTV)
            resultTV.text = "RESULT: DUPA"
        }
    }
}