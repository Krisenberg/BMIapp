package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

private const val shared_pref_file_key = "sharedPreferencesUnitAdapter"
class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val actionBar = supportActionBar
        actionBar?.title = "Settings"

        val height_units_spinner = findViewById<Spinner>(R.id.heightUnitSpinner)
        height_units_spinner.setSelection(UnitAdapterViewModel.getHeightUnitPosition())

        val weight_units_spinner = findViewById<Spinner>(R.id.weightUnitSpinner)
        weight_units_spinner.setSelection(UnitAdapterViewModel.getWeightUnitPosition())

        height_units_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                UnitAdapterViewModel.saveNewHeightUnit(adapterView?.getItemAtPosition(position).toString())
                Toast.makeText(this@Settings,"You selected ${UnitAdapterViewModel.loadHeightUnit()}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        weight_units_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                UnitAdapterViewModel.saveNewWeightUnit(adapterView?.getItemAtPosition(position).toString())
                Toast.makeText(this@Settings,"You selected ${UnitAdapterViewModel.loadWeightUnit()}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }
}