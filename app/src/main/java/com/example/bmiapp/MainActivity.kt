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
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val mainViewModel: MainViewModel by viewModels()

    //private var viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    //val mainViewModel: MainViewModel by viewModels()
    //lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        //Toast.makeText(this@MainActivity, "DUPA ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        android.R.id.home

        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //val mainViewModel: MainViewModel by viewModels()


//        mainViewModel.current_height_unit.observe(this, Observer {
//            findViewById<TextView>(R.id.heightUnitTV).text = it.toString()
//        })
//
//        mainViewModel.current_weight_unit.observe(this, Observer {
//            findViewById<TextView>(R.id.weightUnitTV).text = it.toString()
//        })

        //setup the Unit Adapter so it can use the SharedPreferences
        UnitAdapter.setup(applicationContext)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect {uiState ->
                    // Update UI elements
                    val update_height_result = mainViewModel.checkUnitAndUpdateHeight()
                    val update_weight_result = mainViewModel.checkUnitAndUpdateWeight()
                    mainViewModel.checkUnitAndUpdateWeight()
                    updateUIonCreate(uiState, update_height_result, update_weight_result)

                }
            }
        }

//        checkIfHeightUnitChanged()
//        checkIfWeightUnitChanged()

//        val heightInput = findViewById<EditText>(R.id.heightInput)
//
//        val has_height_unit_changed = checkIfHeightUnitChanged()
//        if (mainViewModel.current_height_value.value != null && !has_height_unit_changed){
//            heightInput.setText(mainViewModel.current_height_value.value.toString())
//            Toast.makeText(this@MainActivity, "TUTAJ JESTEM", Toast.LENGTH_SHORT).show()
//        } else {
//            heightInput.addTextChangedListener(object: TextWatcher{
//                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                }
//
//                override fun afterTextChanged(str: Editable?) {
//                    mainViewModel.current_height_value.value=str.toString().toDoubleOrNull()
//                    Toast.makeText(this@MainActivity, "Wzrost to: ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
        val heightInput = findViewById<EditText>(R.id.heightInput)
        heightInput.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(editableVal: Editable?) {
                val newValue = editableVal.toString().toDoubleOrNull()
                // Call the ViewModel function to update UI state
                mainViewModel.updateHeightValue(newValue)
//                mainViewModel.current_height_value.value = str.toString().toDoubleOrNull()
//                if(mainViewModel.current_height_value.value != null)
//                    Toast.makeText(this@MainActivity, "Wzrost to: ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
            }
        })

        //change the action bar title here
        val actionBar = supportActionBar
        actionBar?.title = "BMI calculator"

        val button = findViewById<Button>(R.id.calculateButton)
        button.setOnClickListener{
            val resultTV = findViewById<TextView>(R.id.bmiTV)
            resultTV.text = "RESULT: some value"
        }
    }

//    override fun onResume() {
//        super.onResume()
//        updateUIonResume(mainViewModel.uiState.value)
//    }

//    override fun onResume() {
//        Toast.makeText(this@MainActivity, "HeightUnit ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
//        super.onResume()
//
//        // Check if the height unit changed
//        val has_height_unit_changed = checkIfHeightUnitChanged()
//
//        val heightInput = findViewById<EditText>(R.id.heightInput)
//        heightInput.setText(mainViewModel.current_height_value.value.toString())
////        if(!has_height_unit_changed)
////            heightInput.setText("DUPA")
////        if (mainViewModel.current_height_value.value != null && !has_height_unit_changed) {
////            heightInput.setText(mainViewModel.current_height_value.value.toString())
////        }
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_history -> Toast.makeText(this, "Twoja stara", Toast.LENGTH_SHORT).show()
            R.id.nav_settings -> {
//                Toast.makeText(this@MainActivity, "HeightUnit ${mainViewModel.current_height_value.value}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Settings::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateUIonCreate(uiState: CalculatorUiState, heightUpdateResult: Boolean, weightUpdateResult: Boolean) {
        // Example: Update TextViews with the dice values and roll count
        val heightUnit = findViewById<TextView>(R.id.heightUnitTV)
        val weightUnit = findViewById<TextView>(R.id.weightUnitTV)
        val heightInput = findViewById<EditText>(R.id.heightInput)
        val weightInput = findViewById<EditText>(R.id.weightInput)

        // Set the values from uiState to the TextViews
        heightUnit.text = uiState.heightUnit
        weightUnit.text = uiState.weightUnit

        if(heightUpdateResult)
            heightInput.setText("")
        if(weightUpdateResult)
            weightInput.setText("")
    }

//    private fun updateUIonResume(uiState: CalculatorUiState) {
//        // Example: Update TextViews with the dice values and roll count
//        val heightInput = findViewById<EditText>(R.id.heightInput)
//        val weightInput = findViewById<EditText>(R.id.weightInput)
//
//        if (uiState.heightValue != null)
//            heightInput.setText(uiState.heightValue?.toString())
//        if (uiState.weightValue != null)
//            heightInput.setText(uiState.weightValue?.toString())
//    }

//    private fun checkIfHeightUnitChanged(): Boolean {
//        val height_unit = UnitAdapter.loadHeightUnit()
//        if (mainViewModel.current_height_unit.value!=height_unit) {
//            mainViewModel.current_height_unit.value=height_unit
//            return true
//        }
//        return false
//    }
//
//    private fun checkIfWeightUnitChanged(): Boolean {
//        val weight_unit = UnitAdapter.loadWeightUnit()
//        if (mainViewModel.current_weight_unit.value!=weight_unit) {
//            mainViewModel.current_weight_unit.value=weight_unit
//            return true
//        }
//        return false
//    }
}