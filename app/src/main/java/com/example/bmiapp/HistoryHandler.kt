package com.example.bmiapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private const val STRING_SET_KEY = "historyEntries"
private const val HISTORY_FILE_KEY = "sharedPreferencesHistory"
private const val MAX_LIST_SIZE = 10
object HistoryHandler {
    private var shared_pref_hist_handler: SharedPreferences? = null
    private lateinit var history_list: MutableList<HistoryEntry>

    @RequiresApi(Build.VERSION_CODES.O)
    fun setup(context: Context) {
        shared_pref_hist_handler = context.getSharedPreferences(HISTORY_FILE_KEY, Context.MODE_PRIVATE)
        history_list = loadSortedHistoryList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadSortedHistoryList(): MutableList<HistoryEntry>{
        val savedSet = shared_pref_hist_handler?.getStringSet(STRING_SET_KEY, setOf())
        val entriesList = mutableListOf<HistoryEntry>()

        if (savedSet != null) {

            for (i in 0..<savedSet.size) {
                val serializedEntry = savedSet.elementAt(i)
                val deserializedEntry = Json.decodeFromString<HistoryEntry>(serializedEntry)
                entriesList.add(deserializedEntry)
            }
        }

        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
        val sortedList = entriesList.sortedByDescending {
            LocalDateTime.parse(it.date, formatter)
        }.toMutableList()
        return sortedList
    }

    fun addEntry(entry: HistoryEntry){
        val historyListSize = history_list.size
        var deletedEntry: HistoryEntry? = null

        if (historyListSize > 0) {
            if (historyListSize == MAX_LIST_SIZE) {
                deletedEntry = history_list[MAX_LIST_SIZE-1]
                for (i in historyListSize-1 downTo 1) {
                    history_list[i] = history_list[i-1]
                }
            } else {
                history_list.add(history_list[historyListSize-1])
                for (i in historyListSize-1 downTo 1) {
                    history_list[i] = history_list[i-1]
                }
            }
            history_list[0] = entry
        } else {
            history_list.add(entry)
        }

        val savedSet = shared_pref_hist_handler?.getStringSet(STRING_SET_KEY, setOf())
        var newSet: MutableSet<String> = mutableSetOf()
        if (savedSet != null)
            newSet = savedSet.toMutableSet()

        if (deletedEntry != null)
            newSet.remove(Json.encodeToString(deletedEntry))

        newSet.add(Json.encodeToString(entry))

        val editor = shared_pref_hist_handler?.edit()
        editor?.apply{
            putStringSet(STRING_SET_KEY, newSet)
        }?.apply()
    }

    fun getEntries(): List<HistoryEntry>{
        return history_list
    }

    fun deleteHistory() {
        shared_pref_hist_handler?.edit()?.remove(STRING_SET_KEY)?.apply()
        history_list.clear()
    }
}