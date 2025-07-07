package com.example.todo

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

object OpenAISuggestions {
    private const val API_KEY = "" // 🛑 Put your real OpenAI API key here
    private const val ENDPOINT = "https://api.openai.com/v1/chat/completions"

    fun suggestTasksFromPlan(
        weeklyPlan: String,
        onResult: (List<String>) -> Unit,
        onError: (String) -> Unit
    ) {
        val client = OkHttpClient()

        val requestBody = JSONObject().apply {
            put("model", "gpt-3.5-turbo")
            put("messages", JSONArray().apply {
                put(JSONObject().apply {
                    put("role", "system")
                    put("content", "You are a helpful assistant that extracts actionable to-do tasks from a weekly plan.")
                })
                put(JSONObject().apply {
                    put("role", "user")
                    put("content", weeklyPlan)
                })
            })
            put("temperature", 0.7)
        }

        val mediaType = "application/json".toMediaType()
        val body = requestBody.toString().toRequestBody(mediaType)

        val request = Request.Builder()
            .url(ENDPOINT)
            .addHeader("Authorization", "Bearer $API_KEY")
            .addHeader("Content-Type", "application/json")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onError("❌ Network failure: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val resBody = response.body?.string()

                    if (!response.isSuccessful || resBody == null) {
                        onError("❌ HTTP ${response.code}: ${resBody ?: "No response body"}")
                        return
                    }

                    val json = JSONObject(resBody)
                    val choices = json.optJSONArray("choices")

                    if (choices == null || choices.length() == 0) {
                        onError("❌ No choices in response: $json")
                        return
                    }

                    val message = choices.getJSONObject(0)
                        .getJSONObject("message")
                    val content = message.optString("content")

                    if (content.isNullOrBlank()) {
                        onError("❌ Empty content in response: $json")
                        return
                    }

                    // Split content into list of tasks
                    val tasks = content.split("\n")
                        .map { it.trim().removePrefix("-").removePrefix("•").trim() }
                        .filter { it.isNotBlank() }

                    onResult(tasks)

                } catch (e: Exception) {
                    onError("❌ Exception parsing response: ${e.message}")
                }
            }
        })
    }
}
