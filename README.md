### Appambit Android Library📱

The Android library for interacting with the Appambit API. This library provides a simple way to perform operations such as storing consumers and initiating sessions via HTTP POST requests.🚀

### Appambit Android Library

## 📋Index

- [Requirements](#requirements)
- [Installation](#installation)
- [Library usage](#library-usage)

- [Store a consumer](#storing-a-consumer)
- [Start a session](#logging-in)
- [Handling custom headers](#handling-custom-headers)

- [Running the demo application](#how-to-run-the-demo-application)

## 📌Requirements
- Android SDK minimum: API 26 (Android 8.0 Oreo) 🍪
- Java 8 or higher ☕
- Kotlin 1.8 or higher 🅚

## 📥Installation

### First Option: Using the AAR file 📦

1. Download the `appambit-debug.aar` or `appambit-release.aar` file.
2. Place the file in the `libs` directory of your project.
3. Add the following dependency in your `build.gradle` file at module level:
```
dependencies {
    implementation(files("../libs/appambit-debug.aar"))
}
```

### Second Option: Using the library module 🧩

1. Add the library module to your project
2. Include the module in your `settings.gradle` file:
```
include ':app', ':appambit'
```
Add the dependency in your `build.gradle` file at module level:
```
dependencies {
    implementation(project(":appambit"))
}
```
## 📚Library usage

### Storing a consumer

To store a consumer and obtain a token:
```
import com.appambit.appambit.Test

// Create a Test instance
val test = Test()

Consumer data in JSON format
val consumerJson = """
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

// Custom headers (Optional)
val headers = mapOf(
    "X-Custom-Header" to "Custom value",
    "X-App-Version" to "1.0.0"
)

// Call the method storeConsumer
test.storeConsumer(
    consumerJson = consumerJson,
    headers = headers
) { result ->
    result.fold(
        onSuccess = { token ->
            // Handle received token
            println("Token: $token")
        },
        onFailure = { error ->
            // Handle the error
            println("Error: ${error.message}")
        }
    )
}
```

### 🔑Logging in

To log in with a token:

```
// Session data in JSON format
val sessionJson = """
{
    "timestamp": "2023-01-01T00:00:00Z"
}
""".trimIndent()

// Customized headers, including the authorization token
val headers = mapOf(
    "Authorization" to "Bearer $token",
    "X-Session-Type" to "mobile"
)

// Call the method startSession
test.startSession(
    body = sessionJson,
    headers = headers
) { result ->
    result.fold(
        onSuccess = { sessionResponse ->
            // Manage session response
            println("Sesión iniciada: $sessionResponse")
        },
        onFailure = { error ->
            //  Manage error response
            println("Error: ${error.message}")
        }
    )
}
```

### Handling custom headers

The library allows adding custom HTTP headers to requests:

```
val headers = mapOf(
    "Authorization" to "Bearer $token",
    "X-App-Version" to "1.0.0",
    "X-Device-Type" to "Android",
    "X-Custom-Header" to "Custom-Value"
)

test.storeConsumer(consumerJson, headers) { result ->
    // Handle result
}
```

## ▶️How to run the demo application

The demo application shows how to use the appambit library in a real project.👨‍💻

### 📌Requirements

- Android Studio Arctic Fox (2020.3.1) or later 🦊
- Android device with API 26 or higher, or an emulator. 📱
- AGP 8.9.2 🔧

### 🛠️Steps to run the application

Clone the repository or download the source code 2.
2. Open the project in Android Studio
3. Make sure that the appambit library is correctly configured as a dependency.
4. Run the application on a device or emulator.


### 🎛️Functionalities of the demo application

- Store Consumer” button: Store a consumer and get a token
- Start Session” button: Starts a session using the obtained token
- Displays the status of the operation and the received responses
