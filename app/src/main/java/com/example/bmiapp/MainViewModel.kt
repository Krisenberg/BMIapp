package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.os.Bundle
import android.view.View

class MainViewModel : ViewModel() {

    var current_height_unit = UnitAdapter.loadHeightUnit()
    var current_weight_unit = UnitAdapter.loadHeightUnit()

    fun checkIfUnitsChanged(){
        val new_height_unit = UnitAdapter.loadHeightUnit()
        val new_weight_unit = UnitAdapter.loadWeightUnit()

//        if (new_height_unit != current_height_unit)
//        {
//            current_height_unit = new_height_unit
//            val resultTV = findViewById<TextView>(R.id.bmiTV)
//            resultTV.text = "RESULT: some value"
//        }
    }

}