package com.example.bmiapp

import android.content.Context
import android.content.SharedPreferences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.LinkedList
import java.util.Queue


private const val string_set_key = "historyEntries"
private const val history_file_key = "sharedPreferencesHistoryHandler"
object HistoryHandler {
    private var shared_pref_hist_handler: SharedPreferences? = null
    private var history_list: Queue<HistoryEntry> = LinkedList()
    fun setup(context: Context) {
        shared_pref_hist_handler = context.getSharedPreferences(history_file_key, Context.MODE_PRIVATE)
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    fun addEntry(entry: HistoryEntry){
        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())!!
        var newSet: MutableSet<String> = mutableSetOf()

        var newSetSize = savedSet.size

        if (newSetSize == 10)
            newSetSize--


        for (i in 0..<newSetSize) {
            newSet.add(savedSet.elementAt(i))
        }
        newSet.add(Json.encodeToString(entry))

        val editor = shared_pref_hist_handler?.edit()
        editor?.apply{
            putStringSet(string_set_key, newSet)
        }?.apply()
//        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//        val current = LocalDateTime.now().format(formatter)
    }

    fun getEntries(): List<HistoryEntry>{
        val savedSet = shared_pref_hist_handler?.getStringSet(string_set_key, setOf())!!

        var entriesList = mutableListOf<HistoryEntry>()

        for (i in 0..<savedSet.size) {
            val serializedEntry = savedSet.elementAt(i)
            val deserializedEntry = Json.decodeFromString<HistoryEntry>(serializedEntry)
            entriesList.add(deserializedEntry)
        }


        return entriesList
    }

    fun deleteHistory() {
        shared_pref_hist_handler?.edit()?.remove(string_set_key)?.commit()
//        val editor = shared_pref_hist_handler?.edit()
//        editor?.apply{
//            putStringSet(string_set_key, setOf())
//        }?.apply()
    }
}