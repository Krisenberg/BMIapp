package com.example.bmiapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {

    private lateinit var historyRecyclerAdapter: RecyclerAdapter
    private lateinit var history_entries: MutableList<HistoryEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_entries)

        val actionBar = supportActionBar
        actionBar?.title = "History"


        // In the following lines we want to setup the recycler view to display
        // the list of saved data. We need a RecyclerAdapter and also a list of entries.
        val history_recycler = findViewById<RecyclerView>(R.id.historyRV)
        history_recycler.layoutManager = LinearLayoutManager(this)

        history_entries = HistoryHandler.getEntries().toMutableList()
        historyRecyclerAdapter = RecyclerAdapter(history_entries)
        history_recycler.adapter = historyRecyclerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_history,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_delete -> {
                // Ask user whether the history should be deleted
                showAlertDialog()
            }
            android.R.id.home -> {
                // finish this activity after clicking back icon
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog() {

        // Create a dialog that will pop onto the screen
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.alert_dialog)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val buttonPositive = dialog.findViewById<Button>(R.id.alert_positiveBTN)
        val buttonNegative = dialog.findViewById<Button>(R.id.alert_negativeBTN)

        buttonPositive?.setOnClickListener{
            // If the user chose 'YES' then the history has to be deleted and RecyclerView updated
            HistoryHandler.deleteHistory()
            history_entries.clear()
            historyRecyclerAdapter.notifyDataSetChanged()
            Toast.makeText(this,"History has been deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        buttonNegative?.setOnClickListener {
            Toast.makeText(this,"History has been NOT deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()

    }
}