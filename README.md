# KPlatform

[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org/docs/multiplatform.html)
[![Maven Central](https://img.shields.io/maven-central/v/com.giancarlobuenaflor/kplatform)](https://central.sonatype.com/artifact/com.giancarlobuenaflor/kplatform)

**KPlatform** is a lightweight, pluggable Kotlin Multiplatform (KMP) library that exposes
information about _where_ your code is running such as the operating system or compilation target 
so **you can write platform-specific logic right from `commonMain`** without scattering `expect/actual`
declarations or creating extra source-sets when you don’t actually need platform APIs.

---

## Features

- ✅ **Zero-config** — Works out-of-the-box with all major Kotlin Multiplatform targets.
- ✅ **Reduce Boilerplate** — Keep platform branches close in `commonMain`.

## Table of Contents

1. [Installation](#installation)
2. [Quick Start](#quick-start)
3. [Testing](#testing)
4. [Supported Targets](#supported-targets)

## Installation

Add the dependency to the **`commonMain`** source-set in your Multiplatform `build.gradle(.kts)`
file:

```kotlin
commonMain.dependencies {
  implementation("com.giancarlobuenaflor:kplatform:<version>")
}
```

## Quick Start

To start, simply create an instance of `KPlatform`. That's it.

```kotlin
val platform = KPlatform()
```

❗ For an in-depth view of what API is available [check out the API documentation](https://buenaflor.github.io/KPlatform/).

### Operating System and Compilation Target

The API surfaces the operating system and compilation target information. 
Sometimes it's important to make a distinction between the operating system and the compilation target.

Consider a web build (`js` target) that may execute on Windows, Linux, or macOS. The compilation
target remains `js`, while the runtime operating system changes per end-user.  
Knowing both values allows you to fine-tune behaviour when either dimension matters.

```kotlin
val platform = KPlatform()

val operatingSystem  = platform.operatingSystem
val compilationTarget = platform.compilationTarget
```

### Reduce Boilerplate

Sometimes you don't need platform specific APIs but want to write custom code based on the operating
system and/or compilation target. 

```kotlin
val platform = KPlatform()

fun foo() {
  if (platform.operatingSystem.isAndroid) {
    // Execute Android specific code
  }
  if (platform.compilationTarget.isWeb) {
    // Execute Web specific code
  }
}
```

You can use a switch as well.

```kotlin
val platform = KPlatform()

when (platform.operatingSystem.family) {
  OperatingSystem.Family.WINDOWS -> {
    // Execute this code block only on Windows
  }
  OperatingSystem.Family.LINUX -> {
    // Execute this code block only on Linux
  }
  else -> {
    // Do something else
  }
}

when (platform.compilationTarget) {
  CompilationTarget.ANDROID -> {
    // Execute this code block only for Android builds
  }
  CompilationTarget.JS -> {
    // Execute this code block only for JS builds
  }
  else -> {
      // Do something else
  }
}
```

## Testing

Create a custom implementation of the `Platform` interface and inject it wherever your code needs 
faked platform data.

```kotlin
class FakePlatform(
  override val compilationTarget: CompilationTarget = CompilationTarget.JVM,
  override val operatingSystem: OperatingSystem = OperatingSystem.from(
    OperatingSystem.Family.WINDOWS,
    "10"
  ),
  override val environment: Map<String, String> = mapOf("MY_ENV" to "123"),
  override val isDebug: Boolean = false
) : Platform

val fakePlatform = FakePlatform()
```

## Supported Targets

| Target           | Kotlin preset                                              |
|------------------|------------------------------------------------------------|
| Android          | `android()`                                                |
| JVM              | `jvm()`                                                    |
| iOS              | `iosArm64()` / `iosX64()` / `iosSimulatorArm64()`          |
| macOS            | `macosX64()` / `macosArm64()`                              |
| tvOS             | `tvosX64()` / `tvosArm64` / `tvosSimulatorArm64`           |
| watchOS          | `watchosX64()` / `watchosArm64` / `watchosSimulatorArm64`  |
| WasmJS (browser) | `wasmJs()`                                                 |
| JS (browser)     | `js()`                                                     |

> Need another target?  [Open an issue](https://github.com/your-org/kplatform/issues).
