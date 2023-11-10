package com.example.bmiapp

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
                // delete the history after user clicks the 'Delete' icon
//                HistoryHandler.deleteHistory()
//                history_entries.clear()
//                historyRecyclerAdapter.notifyDataSetChanged()
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
        val alertBuilder = AlertDialog.Builder(this)
        val customView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
        alertBuilder.setView(customView)
        val dialog = alertBuilder.create()
//        val dialog = Dialog(this)
//        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        //dialog.setCancelable(false)
//        dialog.setContentView(R.layout.alert_dialog)
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//
        val buttonPositive = dialog.findViewById<Button>(R.id.alert_positiveBTN)
        val buttonNegative = dialog.findViewById<Button>(R.id.alert_negativeBTN)

        buttonPositive?.setOnClickListener{
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


//        val questionDialog = MaterialAlertDialogBuilder(this)
//            .setTitle("Warning")
//            .setMessage("Do you really want to delete all the stored data?")
//            .setNegativeButton("No") { dialog, which ->
//                Toast.makeText(this,"History has not been deleted", Toast.LENGTH_SHORT).show()
//            }
//            .setPositiveButton("Yes") { dialog, which ->
//                HistoryHandler.deleteHistory()
//                history_entries.clear()
//                historyRecyclerAdapter.notifyDataSetChanged()
//            }
//            .show()

//        val builder: AlertDialog.Builder = AlertDialog.Builder(this, R.style.MyAlertDialogTheme)
//        builder
//            .setMessage("I am the message")
//            .setTitle("I am the title")
//            .setPositiveButton("Positive") { dialog, which ->
//                // Do something.
//            }
//            .setNegativeButton("Negative") { dialog, which ->
//                // Do something else.
//            }
//
//        val dialog: AlertDialog = builder.create()
//        dialog.show()

//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Dialog Title")
//        builder.setMessage("Dialog Message")
//
//        builder.setPositiveButton("OK") { dialog, which ->
//            // Handle positive button click
//        }
//
//        builder.setNegativeButton("Cancel") { dialog, which ->
//            // Handle negative button click
//        }
//
//        val alertDialog = builder.create()
//
//        // Set a semi-transparent background color for the window
//        val window = alertDialog.window
//        window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#80000000"))) // Semi-transparent black background
//
//        alertDialog.show()


    }
}