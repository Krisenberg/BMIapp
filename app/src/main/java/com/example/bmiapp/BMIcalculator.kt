package com.example.bmiapp

import kotlin.math.pow

object BMIcalculator {
    fun calculateBMI(heightValue: Double, heightScalingFactor: Double,
                     weightValue: Double, weightScalingFactor: Double): Double {

        val nominator = (weightValue * weightScalingFactor)
        val denominator = (heightValue * heightScalingFactor).pow(2)

        return nominator/denominator
    }
}