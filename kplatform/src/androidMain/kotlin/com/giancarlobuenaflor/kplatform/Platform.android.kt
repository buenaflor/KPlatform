package com.giancarlobuenaflor.kplatform

internal actual fun getOperatingSystemString(): String {
  return "android"
}

internal actual fun getOperatingSystemVersionString(): String {
  return android.os.Build.VERSION.SDK_INT.toString()
}

internal actual fun getCompilationTarget(): CompilationTarget {
  return CompilationTarget.ANDROID
}