package com.example.bmiapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {

    lateinit var historyRecyclerAdapter: RecyclerAdapter
    lateinit var history_entries: MutableList<HistoryEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_entries)

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "History"

        val history_recycler = findViewById<RecyclerView>(R.id.historyRV)
        history_entries = HistoryHandler.getEntries().toMutableList()

        history_recycler.layoutManager = LinearLayoutManager(this)

        historyRecyclerAdapter = RecyclerAdapter(history_entries)
        history_recycler.adapter = historyRecyclerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_history,menu)
        return true
    }

    //finish this activity after clicking back icon
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> {
                //Toast.makeText(this, "Nothing is here", Toast.LENGTH_SHORT).show()
                HistoryHandler.deleteHistory()
                history_entries.clear()
                historyRecyclerAdapter.notifyDataSetChanged()
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}