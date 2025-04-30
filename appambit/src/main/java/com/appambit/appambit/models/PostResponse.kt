package com.appambit.appambit.models

import org.json.JSONObject

data class PostResponse(val rawResponse: String) {
    private val jsonObject: JSONObject by lazy { JSONObject(rawResponse) }

    fun getToken(): String {
        return try {
            jsonObject.getString("token")
        } catch (_: Exception) {
            ""
        }
    }

    fun getAll(): String {
        return try {
            jsonObject
        } catch (_: Exception) {
            ""
        }.toString()
    }

    fun getSessionId(): String {
        return try {
            jsonObject.getString("session_id")
        } catch (_: Exception) {
            ""
        }
    }
}