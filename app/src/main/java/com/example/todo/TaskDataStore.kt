package com.example.todo

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// Enable DataStore for this context
val Context.taskDataStore by preferencesDataStore(name = "tasks")

object TaskDataStore {
    private val TASK_LIST_KEY = stringPreferencesKey("task_list")

    fun getTasks(context: Context): Flow<List<Task>> {
        return context.taskDataStore.data.map { preferences ->
            val jsonString = preferences[TASK_LIST_KEY] ?: "[]"
            try {
                Json.decodeFromString(jsonString)
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    suspend fun saveTasks(context: Context, tasks: List<Task>) {
        val jsonString = Json.encodeToString(tasks)
        context.taskDataStore.edit { preferences ->
            preferences[TASK_LIST_KEY] = jsonString
        }
    }
}
