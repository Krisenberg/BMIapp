package com.example.bmiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "BMI calculator"

        //setup the Unit Adapter so it can use the SharedPreferences
        UnitAdapter.setup(applicationContext)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            if (!mainViewModel.calculateBMI())
                Toast.makeText(this,"Please provide valid height and weight", Toast.LENGTH_SHORT).show()
        }

        mainViewModel.bmi_value().observe(this, Observer {
            val resultTV = findViewById<TextView>(R.id.bmiTV)
            val resultColor = mainViewModel.getBMIcolor()
            if(it == null) {
                resultTV.text = ""
            }
            else {
                resultTV.setTextColor(ContextCompat.getColor(this, resultColor))
                resultTV.text = String.format("%.2f", it)
            }
        })
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.checkUnitAndUpdateHeight()
        mainViewModel.checkUnitAndUpdateWeight()

        findViewById<TextView>(R.id.heightUnitTV).text = mainViewModel.height_unit()
        findViewById<TextView>(R.id.weightUnitTV).text = mainViewModel.weight_unit()

        val heightET = findViewById<EditText>(R.id.heightInput)
        if (mainViewModel.height_value() == null)
            heightET.setText("")
        else
            heightET.setText(mainViewModel.height_value().toString())

        val weightET = findViewById<EditText>(R.id.weightInput)
        if (mainViewModel.weight_value() == null)
            weightET.setText("")
        else
            weightET.setText(mainViewModel.weight_value().toString())

        getHeightInputAndUpdate()
        getWeightInputAndUpdate()
    }

    fun getHeightInputAndUpdate() {
        val heightInput = findViewById<EditText>(R.id.heightInput)

        heightInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editableVal: Editable?) {
                val newValue = editableVal.toString().toDoubleOrNull()
                mainViewModel.updateHeightValue(newValue)
            }
        })
    }

    fun getWeightInputAndUpdate() {
        val weightInput = findViewById<EditText>(R.id.weightInput)

        weightInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editableVal: Editable?) {
                val newValue = editableVal.toString().toDoubleOrNull()
                mainViewModel.updateWeightValue(newValue)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_history -> Toast.makeText(this, "Nothing is here", Toast.LENGTH_SHORT).show()
            R.id.nav_settings -> {
//                Toast.makeText(this@MainActivity, "HeightUnit ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Settings::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}