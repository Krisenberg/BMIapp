package com.example.bmiapp

import android.content.Context
import android.provider.Settings.Global.getString

object DescriptionProvider {

    private val categories_id_name = mapOf(
        1 to R.string.cat_1_name,
        2 to R.string.cat_2_name,
        3 to R.string.cat_3_name,
        4 to R.string.cat_4_name,
        5 to R.string.cat_5_name,
        6 to R.string.cat_6_name,
        7 to R.string.cat_7_name,
        8 to R.string.cat_8_name
    )

    private val categories_id_upper_bound = mapOf(
        1 to R.string.cat_1_upper_bound,
        2 to R.string.cat_2_upper_bound,
        3 to R.string.cat_3_upper_bound,
        4 to R.string.cat_4_upper_bound,
        5 to R.string.cat_5_upper_bound,
        6 to R.string.cat_6_upper_bound,
        7 to R.string.cat_7_upper_bound,
        8 to R.string.cat_8_upper_bound
    )

    private val categories_id_color = mapOf(
        1 to R.color.red,
        2 to R.color.light_red,
        3 to R.color.yellow,
        4 to R.color.green,
        5 to R.color.yellow,
        6 to R.color.light_red,
        7 to R.color.red,
        8 to R.color.dark_red
    )
    private fun getCategoryIDBasedOnValue(bmi_value: Double, context: Context): Int? {
        for ((category_id, cat_upper_bound) in categories_id_upper_bound) {
            val upperBound = context.getString(cat_upper_bound).toDoubleOrNull()

            if (upperBound != null && bmi_value < upperBound) {
                return category_id
            }
        }
        return categories_id_upper_bound.keys.toList().lastOrNull()
    }
    fun getCategoryNameBasedOnValue(bmi_value: Double, context: Context): String? {
        val category_id = getCategoryIDBasedOnValue(bmi_value, context)
        if (category_id != null)
            return context.getString(categories_id_name[category_id]!!)
        return null
    }
    fun getCategoryColorBasedOnValue(bmi_value: Double, context: Context): Int {
        val category_id = getCategoryIDBasedOnValue(bmi_value, context)
        if (category_id != null)
            return categories_id_color[category_id]!!
        return R.color.black
    }


}