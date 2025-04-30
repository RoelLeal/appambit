package com.appambit.appambit

import com.appambit.appambit.models.JsonConsumer
import com.appambit.appambit.network.NetworkService
import java.net.URL

class Test {

    private val baseUrl = "https://staging-appambit.com/api/"

    fun storeConsumer(
        consumerJson: String,
        headers: Map<String, String> = emptyMap(),
        callback: (Result<String>) -> Unit
    ) {
        try {
            val jsonConsumer = JsonConsumer(consumerJson)

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
        body: String,
        headers: Map<String, String> = emptyMap(),
        callback: (Result<String>) -> Unit) {

        val request = JsonConsumer(body)

        NetworkService().createPost(
            url = URL("${baseUrl}session/start"),
            post = request,
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