package com.example.bmiapp

import java.util.Date

data class HistoryEntry(
    val date: Date,
    val height_value: Double,
    val height_unit: String,
    val weight_value: Double,
    val weight_unit: String,
    val bmi: Double,
    val bmi_color: Int
)
