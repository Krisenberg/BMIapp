package com.example.bmiapp

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.ViewModel
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//data class CalculatorUiState(
//    val heightValue: Double? = null,
//    val weightValue: Double? = null,
//    val heightUnit: String = "m",
//    val weightUnit: String = "kg",
//    val bmiValue: Double? = null
//)

class MainViewModel : ViewModel() {

//    private val _uiState = MutableStateFlow(CalculatorUiState())
//    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
//
//    fun checkUnitAndUpdateHeight(): Boolean {
//        if (uiState.value.heightUnit != UnitAdapter.loadHeightUnit()) {
//            _uiState.update { currentState ->
//                currentState.copy(
//                    heightValue = null,
//                    bmiValue = null,
//                    heightUnit = UnitAdapter.loadHeightUnit()
//                )
//            }
//            return true
//        }
//        return false
//    }
//
//    fun checkUnitAndUpdateWeight(): Boolean {
//        if (uiState.value.weightUnit != UnitAdapter.loadWeightUnit()) {
//            _uiState.update { currentState ->
//                currentState.copy(
//                    weightValue = null,
//                    bmiValue = null,
//                    weightUnit = UnitAdapter.loadWeightUnit()
//                )
//            }
//            return true
//        }
//        return false
//    }
//
//    fun updateHeightValue(newValue: Double?) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                heightValue = newValue
//            )
//        }
//    }
//
//    fun updateWeightValue(newValue: Double?) {
//        _uiState.update { currentState ->
//            currentState.copy(
//                weightValue = newValue
//            )
//        }
//    }
//
//    fun calculateBMI(): Boolean {
//        if (uiState.value.heightValue!=null && uiState.value.weightValue!=null) {
//            _uiState.update { currentState ->
//                currentState.copy(
//                    bmiValue = BMIcalculator.calculateBMI(
//                        uiState.value.heightValue!!,
//                        UnitAdapter.getHeightUnitScalingFactor(),
//                        uiState.value.weightValue!!,
//                        UnitAdapter.getWeightUnitScalingFactor()
//                    )
//                )
//            }
//            return true
//        }
//        return false
//    }
//
//    fun getBMIcolor(): Int{
//        return R.color.red
//    }

    var height_unit: String = "cm"
    var weight_unit: String = "kg"
    var height_value: Double? = null
    var weight_value: Double? = null
    var bmi_value: Double? = null

    val current_height_unit: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val current_weight_unit: MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    val current_height_value: MutableLiveData<Double?> by lazy{
        MutableLiveData<Double?>()
    }
    val current_weight_value: MutableLiveData<Double?> by lazy{
        MutableLiveData<Double?>()
    }
    val current_bmi_value: MutableLiveData<Double?> by lazy{
        MutableLiveData<Double?>()
    }

    fun updateHeightValue(newValue: Double?) {
        if (newValue == null)
        {
            height_value = null
            bmi_value = null
            current_bmi_value.value = null
        } else {
            height_value = newValue
        }
        current_height_value.value = height_value
    }

    fun updateWeightValue(newValue: Double?) {
        if (newValue == null)
        {
            weight_value = null
            bmi_value = null
            current_bmi_value.value = null
        } else {
            weight_value = newValue
        }
        current_weight_value.value = weight_value
    }

    fun calculateBMI(): Boolean {
        if (height_value!=null && weight_value!=null) {
            bmi_value = BMIcalculator.calculateBMI(
                height_value!!,
                UnitAdapter.getHeightUnitScalingFactor(),
                weight_value!!,
                UnitAdapter.getWeightUnitScalingFactor()
            )
            current_bmi_value.value = bmi_value
            return true
        }
        bmi_value = null
        current_bmi_value.value = null
        return false
    }
}