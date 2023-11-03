package com.example.bmiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup the Unit Adapter so it can use the SharedPreferences
        UnitAdapter.setup(applicationContext)

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "BMI calculator"

        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            val resultTV = findViewById<TextView>(R.id.bmiTV)
            resultTV.text = "RESULT: some value"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_history -> Toast.makeText(this, "Twoja stara", Toast.LENGTH_SHORT).show()
            R.id.nav_settings -> startActivity(Intent(this, Settings::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}