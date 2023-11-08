package com.example.bmiapp

import kotlin.math.pow
import kotlin.math.roundToInt

object BMIcalculator {
    fun calculateBMI(heightValue: Double, heightScalingFactor: Double,
                     weightValue: Double, weightScalingFactor: Double): Double {

        val nominator = (weightValue * weightScalingFactor)
        val denominator = (heightValue * heightScalingFactor).pow(2)
        val result = nominator/denominator
        val round_result = (result * 100.0).roundToInt() / 100.0
        return round_result
    }
}