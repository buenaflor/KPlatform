# KPlatform

A lightweight Kotlin Multiplatform library that lets you use runtime information of the system.  

---

## Reduce Boilerplate

Although being able to use runtime information conveniently is nice, one of its great benefits is 
leting you write platform-specific logic from `commonMain` without having to scatter `expect/actual`
declarations or juggle extra source-sets **if you do not need access to platform specific APIs**.

```kotlin
val platform = KPlatform()

if (platform.isAndroid) {
  // Android specifics in commonMain
}
```

## Installation

Add the dependency to the **commonMain** source set in your multiplatform `build.gradle(.kts)`:

```kts
implementation("com.giancarlobuenaflor:kplatform:0.1.0")
```

## Usage

```kotlin
val platform = KPlatform()
```

### Testing 

If you need to fake the platform for tests you can implement the `Platform` interface:

```kotlin
class FakePlatform : Platform {
  // implement members
}
```

## Supported Targets

| Target                    | Kotlin preset                                              |
|---------------------------|------------------------------------------------------------|
| Android                   | `android()`                                                |
| JVM                       | `jvm()`                                                    |
| iOS                       | `iosArm64()` / `iosX64()` / `iosSimulatorArm64()`          |
| macOS                     | `macosX64()` / `macosArm64()`                              |
| tvOS                      | `tvosX64()` / `tvosArm64` / `tvosSimulatorArm64`           |
| watchOS                   | `watchosX64()` / `watchosArm64` / `watchosSimulatorArm64`  |
| WasmJS (browser & node)   | `wasmJs()`                                                 |
| JS (browser & node)       | `js()`                                                     |

> Need another target?  [Open an issue](https://github.com/your-org/kplatform/issues).
