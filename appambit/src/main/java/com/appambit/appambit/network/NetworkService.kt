package com.appambit.appambit.network

import com.appambit.appambit.models.NewPost
import com.appambit.appambit.models.PostResponse
import java.net.HttpURLConnection
import java.net.URL

class NetworkService {

    fun createPost(
        url: URL,
        post: NewPost,
        callback: (Result<PostResponse>) -> Unit,
        headers: Map<String, String> = emptyMap()) {
        Thread {
            try {
                val connection = URL(url.toString()).openConnection() as HttpURLConnection
                connection.apply {
                    requestMethod = "POST"
                    doOutput = true
                    setRequestProperty("Content-Type", "application/json")
                    setRequestProperty("Accept", "application/json")

                    headers.forEach { (key, value) ->
                        setRequestProperty(key, value)
                    }

                    outputStream.use {
                        it.write(post.toString().toByteArray())
                    }

                    if(responseCode in 200..299) {
                        inputStream.use { it ->
                            val text = it.bufferedReader().use {
                                it.readText()
                            }
                            callback(Result.success(PostResponse(text)))
                        }
                    }else {
                        errorStream?.use { it ->
                            val errorText = it.bufferedReader().use {
                                it.readText()
                            }
                            callback(Result.failure(Exception("HTTP $responseCode: $errorText")))
                        } ?: callback(Result.failure(Exception("HTTP error $responseCode")))
                    }
                }
            }catch (e: Exception) {
                callback(Result.failure(e))
            }
        }.start()
    }
}