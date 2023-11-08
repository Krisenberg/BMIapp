package com.example.bmiapp

import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class HistoryEntry(
    val date: String,
    val height_value: Double,
    val height_unit: String,
    val weight_value: Double,
    val weight_unit: String,
    val bmi: Double,
    val bmi_color: Int)
