package com.giancarlobuenaflor.kplatform

internal actual fun getOperatingSystem(): String {
  return "android"
}

internal actual fun getOperatingSystemVersion(): String {
  return android.os.Build.VERSION.SDK_INT.toString()
}
