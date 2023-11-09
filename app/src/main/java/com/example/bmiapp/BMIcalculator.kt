package com.example.bmiapp

import kotlin.math.pow
import kotlin.math.roundToInt

object BMIcalculator {

    // This function calculates the BMI value using the most popular
    // formula. It takes the values of height and weight but also
    // scaling factors, so it doesn't have to worry about differences
    // in units since every value can be scaled to the 'cm' or 'kg'.
    fun calculateBMI(heightValue: Double, heightScalingFactor: Double,
                     weightValue: Double, weightScalingFactor: Double): Double {

        val nominator = (weightValue * weightScalingFactor)
        val denominator = (heightValue * heightScalingFactor).pow(2)
        val result = nominator/denominator
        val round_result = (result * 100.0).roundToInt() / 100.0
        return round_result
    }
}