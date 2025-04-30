package com.appambit.appambit

import com.appambit.appambit.models.JsonConsumer
import com.appambit.appambit.network.NetworkService
import java.net.URL

class Test {

    private val baseUrl = "https://staging-appambit.com/api/"

    val consumerData = """
        {
            "app_key": "84e932d8-b9b9-4025-b574-0e411bbd86dd",
            "device_id": "00008101-000E17360C84001E",
            "device_model": "iPhone 16",
            "user_id": "00008101-000E17360C84001E",
            "os": "iOS 18.1",
            "country": "US",
            "language": "en"
        }
    """.trimIndent()

    val jsonConsumer = JsonConsumer(consumerData)

    val sessionData = """
        {
            "timestamp": "2023-01-01T00:00:00Z"
        }
    """.trimIndent()

    val jsonSession = JsonConsumer(sessionData)

    fun storeConsumer(
        headers: Map<String, String> = emptyMap(),
        callback: (Result<String>) -> Unit
    ) {
        try {
            NetworkService().createPost(
                url = URL("${baseUrl}consumer"),
                post = jsonConsumer,
                headers = headers,
                callback = { result ->
                    result.fold(
                        onSuccess = { response ->
                            val bodyResponse = response.getAll()
                            callback(Result.success(bodyResponse))
                        },
                        onFailure = { error ->
                            callback(Result.failure(error))
                        }
                    )
                }
            )
        } catch (e: Exception) {
            callback(Result.failure(Exception("Error parsing JSON consumer data: ${e.message}")))
        }
    }

    fun startSession(
        headers: Map<String, String> = emptyMap(),
        callback: (Result<String>) -> Unit) {

        NetworkService().createPost(
            url = URL("${baseUrl}session/start"),
            post = jsonSession,
            headers = headers,
            callback = { result ->
                result.fold(
                    onSuccess = { response ->
                        callback(Result.success(response.getAll()))
                    },
                    onFailure = { error ->
                        callback(Result.failure(error))
                    }
                )
            }
        )
    }

}