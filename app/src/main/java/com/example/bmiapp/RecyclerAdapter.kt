package com.example.bmiapp

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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}