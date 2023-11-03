package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = supportActionBar
        actionBar?.title = "Settings"

        val height_units_spinner = findViewById<Spinner>(R.id.heightUnitSpinner)
        ArrayAdapter.createFromResource(this, R.array.height_units_array,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                height_units_spinner.adapter = adapter}

        val weight_units_spinner = findViewById<Spinner>(R.id.weightUnitSpinner)
        ArrayAdapter.createFromResource(this, R.array.weight_units_array,
            android.R.layout.simple_spinner_item).also {
                adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            weight_units_spinner.adapter = adapter}
    }
}