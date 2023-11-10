package com.example.bmiapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Author : AppCompatActivity() {

    private lateinit var historyRecyclerAdapter: RecyclerAdapter
    private lateinit var history_entries: MutableList<HistoryEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_page)

        val actionBar = supportActionBar
        actionBar?.title = "About the author"

        val gitHub_button = findViewById<Button>(R.id.author_githubBTN)

        gitHub_button.setOnClickListener{
//            var url = "https://github.com/Krisenberg"
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // finish this activity after clicking back icon
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}