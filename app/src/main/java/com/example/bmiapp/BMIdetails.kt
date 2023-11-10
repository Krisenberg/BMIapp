package com.example.bmiapp;

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class BMIdetails : AppCompatActivity() {

    private lateinit var historyRecyclerAdapter: RecyclerAdapter
    private lateinit var history_entries: MutableList<HistoryEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bmi_details)

        val actionBar = supportActionBar
        actionBar?.title = "Result details"

        val bmi_value = intent.getDoubleExtra("BMI_VALUE", 0.0)

        val resultTV = findViewById<TextView>(R.id.details_bmi_resultTV)
        val categoryTV = findViewById<TextView>(R.id.category_nameTV)
        val descriptionTV = findViewById<TextView>(R.id.category_descriptionTV)

        val result_str = String.format("%.2f", bmi_value)
        val result_color = DescriptionProvider.getCategoryColorBasedOnValue(bmi_value, this)
        val color_TV = ContextCompat.getColor(this, result_color)
        val category_name = DescriptionProvider.getCategoryNameBasedOnValue(bmi_value, this)
        val category_description = DescriptionProvider.getCategoryDescriptionBasedOnValue(bmi_value, this)

        resultTV.text=result_str
        resultTV.setTextColor(color_TV)

        categoryTV.text=category_name
        categoryTV.setTextColor(color_TV)

        descriptionTV.text=category_description
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
