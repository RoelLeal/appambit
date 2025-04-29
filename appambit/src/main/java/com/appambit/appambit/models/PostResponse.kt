package com.appambit.appambit.models

import org.json.JSONObject

data class PostResponse(val rawResponse: String) {
    private val jsonObject: JSONObject by lazy { JSONObject(rawResponse) }

    fun getToken(): String {
        return try {
            jsonObject.getString("token")
        } catch (e: Exception) {
            ""
        }
    }

    fun getAll(): String {
        return try {
            jsonObject
        } catch (e: Exception) {
            ""
        }.toString()
    }

    fun getSessionId(): String {
        return try {
            jsonObject.getString("session_id")
        } catch (e: Exception) {
            ""
        }
    }
}