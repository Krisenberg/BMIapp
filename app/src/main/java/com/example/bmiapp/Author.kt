package com.example.bmiapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Author : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.author_page)

        val actionBar = supportActionBar
        actionBar?.title = "About the author"

        val gitHub_button = findViewById<ImageButton>(R.id.author_githubBTN)
        val gitHub_link = "https://github.com/Krisenberg"

        gitHub_button.setOnClickListener{
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(gitHub_link)))
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