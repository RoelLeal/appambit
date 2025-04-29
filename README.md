### Appambit Android Library

The Android library for interacting with the Appambit API. This library provides a simple way to perform operations such as storing consumers and initiating sessions via HTTP POST requests.

### Appambit Android Library

## Index

- Requirements](#requirements)
- Installation](#installation)
- [Library usage](#library-usage)

- [Store a consumer](#store-a-consumer)
- Start a session](#start-a-session)
- Handling custom headers](#handling-custom-headers)

- Running the demo-application](#running-the-demo-application)
- Design Considerations](#design-considerations)
- [Project structure](#project-structure)
- Contribution](#contribution)
- License](#license)

## Requirements
- Android SDK minimum: API 26 (Android 8.0 Oreo)
- Java 8 or higher
- Kotlin 1.8 or higher

## Installation

### First Option: Using the AAR file

1. Download the `appambit-debug.aar` or `appambit-release.aar` file.
2. Place the file in the `libs` directory of your project.
3. Add the following dependency in your `build.gradle` file at module level:

dependencies {
    implementation(files("../libs/appambit-debug.aar"))
    implementation("com.google.android.material:material:1.12.0")
}

### Second Option: Using the library module

1. Add the library module to your project
2. Include the module in your `settings.gradle` file:

include ':app', ':appambit'

Add the dependency in your `build.gradle` file at module level:

dependencies {
    implementation(project(":appambit"))
}
