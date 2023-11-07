package com.example.bmiapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel : ViewModel() {

    private var _height_unit: String = UnitAdapter.loadHeightUnit()
    private var _weight_unit: String = UnitAdapter.loadHeightUnit()
    private var _height_value: Double? = null
    private var _weight_value: Double? = null
    private val _bmi_value = MutableLiveData<Double?>(null)

    fun height_unit(): String { return _height_unit }
    fun weight_unit(): String { return _weight_unit }
    fun height_value(): Double? { return _height_value }
    fun weight_value(): Double? { return _weight_value }
    fun bmi_value(): LiveData<Double?> { return _bmi_value }

    fun checkUnitAndUpdateHeight(): Boolean {
        if (_height_unit != UnitAdapter.loadHeightUnit()) {
            _height_unit = UnitAdapter.loadHeightUnit()
            _height_value = null
            _bmi_value.value = null
            return true
        }
        return false
    }

    fun checkUnitAndUpdateWeight(): Boolean {
        if (_weight_unit != UnitAdapter.loadWeightUnit()) {
            _weight_unit = UnitAdapter.loadWeightUnit()
            _weight_value = null
            _bmi_value.value = null
            return true
        }
        return false
    }

    fun updateHeightValue(newValue: Double?) {
        if (newValue == null)
        {
            _bmi_value.value = null
            _height_value = null
        } else {
            if (_height_value != newValue) {
                _bmi_value.value = null
                _height_value = newValue
            }
        }
    }

    fun updateWeightValue(newValue: Double?) {
        if (newValue == null)
        {
            _weight_value = null
            _bmi_value.value = null
        } else {
            if (_weight_value != newValue) {
                _weight_value = newValue
                _bmi_value.value = null
            }
        }
    }

    fun calculateBMI(): Boolean {
        if (_height_value !=null && _weight_value !=null) {
            _bmi_value.value = BMIcalculator.calculateBMI(
                _height_value!!,
                UnitAdapter.getHeightUnitScalingFactor(),
                _weight_value!!,
                UnitAdapter.getWeightUnitScalingFactor()
            )
            return true
        }
        _bmi_value.value = null
        return false
    }

    fun getBMIcolor(): Int{
        return R.color.red
    }
}