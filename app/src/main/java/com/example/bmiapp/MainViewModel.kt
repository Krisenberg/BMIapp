package com.example.bmiapp

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

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
        val stored_unit = UnitAdapter.loadHeightUnit()
        if (_height_unit != stored_unit) {
            _height_unit = stored_unit
            _height_value = null
            _bmi_value.value = null
            return true
        }
        return false
    }

    fun checkUnitAndUpdateWeight(): Boolean {
        val stored_unit = UnitAdapter.loadHeightUnit()
        if (_weight_unit != stored_unit) {
            _weight_unit = stored_unit
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun addEntryToTheHistory(context: Context) {
        val bmi_color = DescriptionProvider.getCategoryColorBasedOnValue(_bmi_value.value!!, context)
        val date_formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
        val current_date = ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).format(date_formatter)
        val newEntry = HistoryEntry(current_date.toString(), _height_value!!, _height_unit,
            _weight_value!!, _weight_unit, _bmi_value.value!!, ContextCompat.getColor(context, bmi_color))

        HistoryHandler.addEntry(newEntry)
    }
}