package com.appambit.appambit.models

import org.json.JSONObject

data class Consumer(
    val app_key: String,
    val device_id: String,
    val device_model: String,
    val user_id: String,
    val user_email: String,
    val os: String,
    val country: String,
    val language: String
) : NewPost {
    override fun toString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("app_key", app_key)
        jsonObject.put("device_id", device_id)
        jsonObject.put("device_model", device_model)
        jsonObject.put("user_id", user_id)
        jsonObject.put("user_email", user_email)
        jsonObject.put("os", os)
        jsonObject.put("country", country)
        jsonObject.put("language", language)
        return jsonObject.toString()
    }
}