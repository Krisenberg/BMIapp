package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "Settings"

        val height_units_spinner = findViewById<Spinner>(R.id.heightUnitSpinner)
        //create adapter that will allow to set a style of the spinner and use getPosition function
        val height_adapter = ArrayAdapter<String>(this, R.layout.list_item, resources.getStringArray(R.array.height_units_array))
        height_units_spinner.adapter = height_adapter
        //set value stored by UnitAdapter in the spinner
        height_units_spinner.setSelection(height_adapter.getPosition(UnitAdapter.loadHeightUnit()))

        val weight_units_spinner = findViewById<Spinner>(R.id.weightUnitSpinner)
        //create adapter that will allow to set a style of the spinner and use getPosition function
        val weight_adapter = ArrayAdapter<String>(this, R.layout.list_item, resources.getStringArray(R.array.weight_units_array))
        weight_units_spinner.adapter = weight_adapter
        //set value stored by UnitAdapter in the spinner
        weight_units_spinner.setSelection(weight_adapter.getPosition(UnitAdapter.loadWeightUnit()))

        //change the unit in UnitAdapter after a new selection
        height_units_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                UnitAdapter.saveNewHeightUnit(adapterView?.getItemAtPosition(position).toString())
                Toast.makeText(this@Settings,"You selected ${UnitAdapter.loadHeightUnit()}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        //change the unit in UnitAdapter after a new selection
        weight_units_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                UnitAdapter.saveNewWeightUnit(adapterView?.getItemAtPosition(position).toString())
                Toast.makeText(this@Settings,"You selected ${UnitAdapter.loadWeightUnit()}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }

    //finish this activity after clicking back icon
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}