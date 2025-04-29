package com.appambit.appambit.models

import org.json.JSONObject

data class Session(
    val token: String
) : NewPost {
    override fun toString(): String {
        val jsonObject = JSONObject()
        jsonObject.put("token", token)
        return jsonObject.toString()
    }
}