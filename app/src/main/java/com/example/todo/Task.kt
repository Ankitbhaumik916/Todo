package com.example.todo

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Int = 0,
    val text: String,
    val dueDate: String = "",
    val isDone: Boolean = false
)
