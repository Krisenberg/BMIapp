package com.example.bmiapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private const val string_set_key = "historyEntries"
private const val history_file_key = "sharedPreferencesHistory"
private const val max_list_size = 10
object HistoryHandler {
    private var shared_pref_hist_handler: SharedPreferences? = null
    private lateinit var history_list: MutableList<HistoryEntry>

    @RequiresApi(Build.VERSION_CODES.O)
    fun setup(context: Context) {
        shared_pref_hist_handler = context.getSharedPreferences(history_file_key, Context.MODE_PRIVATE)
        history_list = loadSortedHistoryList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadSortedHistoryList(): MutableList<HistoryEntry>{
        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())
        val entriesList = mutableListOf<HistoryEntry>()

        if (savedSet != null) {

            for (i in 0..<savedSet.size) {
                val serializedEntry = savedSet.elementAt(i)
                val deserializedEntry = Json.decodeFromString<HistoryEntry>(serializedEntry)
                entriesList.add(deserializedEntry)
            }
        }

        //val sortedList = entriesList.toMutableList()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")
        val sortedList = entriesList.sortedByDescending {
            LocalDateTime.parse(it.date, formatter)
            //LocalDateTime.parse(it.date, formatter)
        }.toMutableList()
        return sortedList
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    fun addEntry(entry: HistoryEntry){
//        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())!!
//        var newSet: MutableSet<String> = mutableSetOf()
//
//        var newSetSize = savedSet.size
//
//        if (newSetSize == 10)
//            newSetSize--
//
//
//        for (i in 0..<newSetSize) {
//            newSet.add(savedSet.elementAt(i))
//        }
        val historyListSize = history_list.size
        var deletedEntry: HistoryEntry? = null

        if (historyListSize > 0) {
            if (historyListSize == max_list_size) {
                deletedEntry = history_list[max_list_size-1]
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

//        for (i in historyListSize downTo 1) {
//            history_list[i] = history_list[i-1]
//        }
//
//        if (historyListSize > 0)
//            history_list[0] = entry
//        else
//            history_list.add(entry)

        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())
//        shared_pref_hist_handler?.edit()?.remove(string_set_key)?.commit()
        var newSet: MutableSet<String> = mutableSetOf()
        if (savedSet != null)
            newSet = savedSet.toMutableSet()

        if (deletedEntry != null)
            newSet.remove(Json.encodeToString(deletedEntry))

        newSet.add(Json.encodeToString(entry))

        val editor = shared_pref_hist_handler?.edit()
        editor?.apply{
            putStringSet(string_set_key, newSet)
        }?.apply()
    }

    fun getEntries(): List<HistoryEntry>{
//        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())
//        var entriesList = mutableListOf<HistoryEntry>()
//
//        if (savedSet != null) {
//
//            for (i in 0..<savedSet.size) {
//                val serializedEntry = savedSet.elementAt(i)
//                val deserializedEntry = Json.decodeFromString<HistoryEntry>(serializedEntry)
//                entriesList.add(deserializedEntry)
//            }
//        }
//
//        return entriesList
        return history_list
    }

    fun deleteHistory() {
        shared_pref_hist_handler?.edit()?.remove(string_set_key)?.apply()
        history_list.clear()
//        val editor = shared_pref_hist_handler?.edit()
//        editor?.apply{
//            putStringSet(string_set_key, setOf())
//        }?.apply()
    }
}