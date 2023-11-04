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

data class CalculatorUiState(
    val heightValue: Double? = null,
    val weightValue: Double? = null,
    val heightUnit: String = "m",
    val weightUnit: String = "kg",
    val bmiValue: Double? = null
)

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    fun checkUnitAndUpdateHeight(): Boolean {
        if (uiState.value.heightUnit != UnitAdapter.loadHeightUnit()) {
            _uiState.update { currentState ->
                currentState.copy(
                    heightValue = null,
                    heightUnit = UnitAdapter.loadHeightUnit()
                )
            }
            return true
        }
        return false
    }

    fun checkUnitAndUpdateWeight(): Boolean {
        if (uiState.value.weightUnit != UnitAdapter.loadWeightUnit()) {
            _uiState.update { currentState ->
                currentState.copy(
                    weightValue = null,
                    weightUnit = UnitAdapter.loadWeightUnit()
                )
            }
            return true
        }
        return false
    }

    fun updateHeightValue(newValue: Double?) {
        _uiState.update { currentState ->
            currentState.copy(
                heightValue = newValue
            )
        }
    }

    fun updateWeightValue(newValue: Double?) {
        _uiState.update { currentState ->
            currentState.copy(
                weightValue = newValue
            )
        }
    }

    fun calculateBMI(): Boolean {
        if (uiState.value.heightValue!=null && uiState.value.weightValue!=null) {
            _uiState.update { currentState ->
                currentState.copy(
                    bmiValue = BMIcalculator.calculateBMI(
                        uiState.value.heightValue!!,
                        UnitAdapter.getHeightUnitScalingFactor(),
                        uiState.value.weightValue!!,
                        UnitAdapter.getWeightUnitScalingFactor()
                    )
                )
            }
            return true
        }
        return false
    }

//    var height_unit = null
//    var weight_unit = null
//    var height_value: Double? = 5.0
//    var weight_value: Double? = null

//    val current_height_unit: MutableLiveData<String> by lazy{
//        MutableLiveData<String>()
//    }
//    val current_weight_unit: MutableLiveData<String> by lazy{
//        MutableLiveData<String>()
//    }
//    val current_height_value: MutableLiveData<Double?> by lazy{
//        MutableLiveData<Double?>()
//    }
//    val current_weight_value: MutableLiveData<Double?> by lazy{
//        MutableLiveData<Double?>()
//    }


}