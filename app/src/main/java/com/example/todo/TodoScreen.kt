package com.example.todo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun TodoScreen(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    onToggleTheme: (Boolean) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var taskText by remember { mutableStateOf("") }
    val taskList = remember { mutableStateListOf<Task>() }

    LaunchedEffect(Unit) {
        TaskDataStore.getTasks(context).collect {
            taskList.clear()
            taskList.addAll(it)
        }
    }

    Column(modifier.padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Your Tasks", style = MaterialTheme.typography.titleLarge)
            Row {
                Text(if (isDark) "🌙" else "☀️")
                Switch(checked = isDark, onCheckedChange = onToggleTheme)
            }
        }

        Spacer(Modifier.height(8.dp))

        TextField(
            value = taskText,
            onValueChange = { taskText = it },
            label = { Text("New task") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if (taskText.isNotBlank()) {
                val newTask = Task(
                    id = taskList.size + 1,
                    text = taskText
                )
                taskList.add(newTask)
                scope.launch {
                    TaskDataStore.saveTasks(context, taskList)
                }
                taskText = ""
            }
        }) {
            Text("Add Task ➕")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(taskList) { task ->
                Text("• ${task.text}")
            }
        }
    }
}
