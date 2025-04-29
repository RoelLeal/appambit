package com.appambit.appambit.models

import org.json.JSONObject

class JsonConsumer(private val jsonString: String) : NewPost {

    init {
        JSONObject(jsonString)
    }

    override fun toString(): String {
        return jsonString
    }
}