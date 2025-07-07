package com.example.todo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GeminiSuggestions {
    // Mock suggestion function
    fun suggestTasksFromPlan(
        plan: String,
        onResult: (List<String>) -> Unit,
        onError: (String) -> Unit
    ) {
        // You can replace this with Gemini / OpenAI API later
        try {
            val suggestions = mockSuggestions(plan)
            onResult(suggestions)
        } catch (e: Exception) {
            onError("Failed to suggest tasks: ${e.message}")
        }
    }

    private fun mockSuggestions(plan: String): List<String> {
        return listOf(
            "Revise AI notes",
            "Submit DAA assignment",
            "Gym session - legs day",
            "Team meeting at 4PM",
            "Buy groceries after college"
        ).filter { it.contains(plan, ignoreCase = true).not() }
    }
}
