package com.example.bmiapp

import android.content.Context
import android.content.SharedPreferences

private const val SHARED_PREF_HEIGHT_UNIT_KEY = "heightUnit"
private const val SHARED_PREF_WEIGHT_UNIT_KEY = "weightUnit"
private const val SHARED_PREF_FILE_KEY = "sharedPreferencesUnitAdapter"
object UnitAdapter{

    private val height_units = mapOf(
        "cm" to 0.01,
        "m" to 1.0,
        "in" to 0.0254
    )

    private val weight_units = mapOf(
        "kg" to 1.0,
        "lb" to 0.4536,
    )

    private var shared_pref_units: SharedPreferences? = null
    fun setup(context: Context) {
        shared_pref_units = context.getSharedPreferences(SHARED_PREF_FILE_KEY, Context.MODE_PRIVATE)
    }

    fun saveNewHeightUnit(new_height_unit : String) {
        val editor = shared_pref_units?.edit()
        editor?.apply{
            putString(SHARED_PREF_HEIGHT_UNIT_KEY, new_height_unit)
        }?.apply()
    }

    fun saveNewWeightUnit(new_weight_unit : String) {
        val editor = shared_pref_units?.edit()
        editor?.apply{
            putString(SHARED_PREF_WEIGHT_UNIT_KEY, new_weight_unit)
        }?.apply()
    }

    fun loadHeightUnit() : String {
        return shared_pref_units?.getString(SHARED_PREF_HEIGHT_UNIT_KEY, "cm")!!
    }

    fun loadWeightUnit() : String {
        return shared_pref_units?.getString(SHARED_PREF_WEIGHT_UNIT_KEY, "kg")!!
    }

    fun getHeightUnitScalingFactor(): Double {
        if (height_units[shared_pref_units?.getString(SHARED_PREF_HEIGHT_UNIT_KEY, "cm")] != null)
            return height_units[shared_pref_units?.getString(SHARED_PREF_HEIGHT_UNIT_KEY, "cm")]!!
        return 1.0
    }

    fun getWeightUnitScalingFactor(): Double {
        if (weight_units[shared_pref_units?.getString(SHARED_PREF_WEIGHT_UNIT_KEY, "kg")] != null)
            return weight_units[shared_pref_units?.getString(SHARED_PREF_WEIGHT_UNIT_KEY, "kg")]!!
        return 1.0
    }
}