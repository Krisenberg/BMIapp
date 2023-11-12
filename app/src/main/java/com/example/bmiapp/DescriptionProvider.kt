package com.example.bmiapp

import android.content.Context

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

    private val categories_id_description = mapOf(
        1 to R.string.cat_1_description,
        2 to R.string.cat_2_description,
        3 to R.string.cat_3_description,
        4 to R.string.cat_4_description,
        5 to R.string.cat_5_description,
        6 to R.string.cat_6_description,
        7 to R.string.cat_7_description,
        8 to R.string.cat_8_description
    )

    // Function to map provided value with the category ID. This ID is required to retrieve
    // other information related to this category.
    private fun getCategoryIDBasedOnValue(bmi_value: Double, context: Context): Int? {
        for ((category_id, cat_upper_bound) in categories_id_upper_bound) {
            val upperBound = context.getString(cat_upper_bound).toDoubleOrNull()

            if (upperBound != null && bmi_value < upperBound) {
                return category_id
            }
        }
        // in case that no category was matched - return the ID of the last category.
        return categories_id_upper_bound.keys.toList().lastOrNull()
    }

    // This function returns the name of the category stored in the strings.xml file
    // based on the BMI value. It uses above function to map the value to the
    // category ID in the first place.
    fun getCategoryNameBasedOnValue(bmi_value: Double, context: Context): String? {
        val category_id = getCategoryIDBasedOnValue(bmi_value, context)
        if (category_id != null)
            return context.getString(categories_id_name[category_id]!!)
        return null
    }

    // This function returns the color of the category stored in the colors.xml file
    // based on the BMI value. It also uses function to map the value to the
    // category ID in the first place.
    fun getCategoryColorBasedOnValue(bmi_value: Double, context: Context): Int {
        val category_id = getCategoryIDBasedOnValue(bmi_value, context)
        if (category_id != null)
            return categories_id_color[category_id]!!
        return R.color.black
    }

    // This function returns the description of the category stored in the strings.xml file
    // based on the BMI value. It uses above function to map the value to the
    // category ID in the first place.
    fun getCategoryDescriptionBasedOnValue(bmi_value: Double, context: Context): String? {
        val category_id = getCategoryIDBasedOnValue(bmi_value, context)
        if (category_id != null)
            return context.getString(categories_id_description[category_id]!!)
        return null
    }


}