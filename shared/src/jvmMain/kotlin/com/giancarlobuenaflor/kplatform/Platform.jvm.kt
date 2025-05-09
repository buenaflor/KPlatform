package com.giancarlobuenaflor.kplatform

internal actual fun getOperatingSystem(): String {
  val os = System.getProperty("os.name")
  if (os.contains("Windows", ignoreCase = true)) return "windows"
  if (os.contains("Mac", ignoreCase = true)) return "mac"
  if (os.contains("Linux", ignoreCase = true)) return "linux"
  return "unknown"
}

internal actual fun getOperatingSystemVersion(): String {
  return System.getProperty("os.version")
}
