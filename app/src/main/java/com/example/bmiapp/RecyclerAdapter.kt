package com.example.bmiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter (private var entries: List<HistoryEntry> ): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryDate: TextView = itemView.findViewById(R.id.entry_date)
        val entryHeightValue: TextView = itemView.findViewById(R.id.entry_heightValueTV)
        val entryWeightValue: TextView = itemView.findViewById(R.id.entry_weightValueTV)
        val entryHeightUnit: TextView = itemView.findViewById(R.id.entry_heightUnitTV)
        val entryWeightUnit: TextView = itemView.findViewById(R.id.entry_weightUnitTV)
        val entryBMI: TextView = itemView.findViewById(R.id.entry_bmiValueTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_entry, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.entryDate.text = entries[position].date
        holder.entryHeightValue.text = entries[position].height_value.toString()
        holder.entryWeightValue.text = entries[position].weight_value.toString()
        holder.entryHeightUnit.text = entries[position].height_unit
        holder.entryWeightUnit.text = entries[position].weight_unit
        holder.entryBMI.text = entries[position].bmi.toString()
        holder.entryBMI.setTextColor(entries[position].bmi_color)
    }

}