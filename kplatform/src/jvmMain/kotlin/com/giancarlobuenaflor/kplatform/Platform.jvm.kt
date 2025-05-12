package com.giancarlobuenaflor.kplatform

internal actual fun getOperatingSystemString(): String {
  val os = System.getProperty("os.name")
  if (os.contains("Windows", ignoreCase = true)) return "windows"
  if (os.contains("Mac", ignoreCase = true)) return "mac"
  if (os.contains("Linux", ignoreCase = true)) return "linux"
  return "unknown"
}

internal actual fun getOperatingSystemVersionString(): String {
  return System.getProperty("os.version")
}

internal actual fun getCompilationTarget(): CompilationTarget {
  return CompilationTarget.JVM
}

internal actual fun getEnvironmentMap(): Map<String, String> {
  return System.getenv()
}

internal actual fun getIsDebug(): Boolean {
  // JVM does not differentiate between debug and release builds.
  return true
}