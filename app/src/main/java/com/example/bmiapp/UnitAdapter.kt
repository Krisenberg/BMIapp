package com.example.bmiapp

import android.content.Context
import android.content.SharedPreferences

private const val shared_pref_height_unit_key = "heightUnit"
private const val shared_pref_weight_unit_key = "weightUnit"
private const val shared_pref_file_key = "sharedPreferencesUnitAdapter"
object UnitAdapter{

    val height_units = mapOf(
        "cm" to 0.01,
        "m" to 1.0,
        "in" to 0.0254
    )

    val weight_units = mapOf(
        "kg" to 1.0,
        "lb" to 0.4536,
    )


    private var shared_pref_units: SharedPreferences? = null
    fun setup(context: Context) {
        shared_pref_units = context.getSharedPreferences(shared_pref_file_key, Context.MODE_PRIVATE)
    }

    fun saveNewHeightUnit(new_height_unit : String) {
        val editor = shared_pref_units?.edit()
        editor?.apply{
            putString(shared_pref_height_unit_key, new_height_unit)
        }?.apply()
    }

    fun saveNewWeightUnit(new_weight_unit : String) {
        val editor = shared_pref_units?.edit()
        editor?.apply{
            putString(shared_pref_weight_unit_key, new_weight_unit)
        }?.apply()
    }

    fun loadHeightUnit() : String {
        return shared_pref_units?.getString(shared_pref_height_unit_key, "cm")!!
    }

    fun loadWeightUnit() : String? {
        return shared_pref_units?.getString(shared_pref_weight_unit_key, "kg")!!
    }

//    fun loadWeightUnit() : String? {
//        if (shared_pref_units != null)
//            return shared_pref_units!!.getString(shared_pref_weight_unit_key, "kg")!!
//        return "kg"
//    }

//    fun getHeightUnitScalingFactor(): Double {
//        if (height_units[selected_height_unit] != null)
//            return height_units[selected_height_unit]!!
//        return 1.0
//    }
//
//    fun getWeightUnitScalingFactor(): Double? {
//        if (weight_units[selected_weight_unit] != null)
//            return weight_units[selected_weight_unit]!!
//        return 1.0
//    }
}