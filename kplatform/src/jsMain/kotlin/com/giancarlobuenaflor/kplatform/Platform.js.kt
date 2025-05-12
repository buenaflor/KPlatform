package com.giancarlobuenaflor.kplatform

internal actual fun getOperatingSystemString(): String {
  val userAgent = js("navigator.userAgent") as String
  return when {
    userAgent.contains("Android", ignoreCase = true) -> "android"
    userAgent.contains("iPhone", ignoreCase = true) ||
        userAgent.contains("iPad", ignoreCase = true) -> "ios"
    userAgent.contains("Mac OS", ignoreCase = true) -> "macos"
    userAgent.contains("Windows", ignoreCase = true) -> "windows"
    userAgent.contains("Linux", ignoreCase = true) -> "linux"
    else -> "unknown"
  }
}

internal actual fun getOperatingSystemVersionString(): String {
  // Node.js ➜ parse from process
  if (jsTypeOf(js("process")) != "undefined") {
    return js("process.version") as String
  }

  // Browser ➜ parse from navigator.userAgent
  val ua = js("navigator.userAgent") as String

  fun clean(version: String): String = version.replace('_', '.')

  // Android
  val androidRegex = Regex("(?:android|adr) (\\d+([._]\\d+)*)", RegexOption.IGNORE_CASE)
  val androidMatch = androidRegex.find(ua)
  if (androidMatch != null) {
    return clean(androidMatch.groupValues[1])
  }

  // iOS (iPhone / iPad)
  val iosRegex = Regex("os ((\\d+[._])+\\d+) like mac os x", RegexOption.IGNORE_CASE)
  val iosMatch = iosRegex.find(ua)
  if (iosMatch != null) {
    return clean(iosMatch.groupValues[1])
  }

  // macOS
  val macRegex = Regex("os x ((\\d+[._])+\\d+)\\b", RegexOption.IGNORE_CASE)
  val macMatch = macRegex.find(ua)
  if (macMatch != null) {
    return clean(macMatch.groupValues[1])
  }

  // Windows
  val winRegex =
      Regex(
          "win(?:dows)?(?: phone)?[ _]?(?:(?:nt|9x) )?((?:(\\d+\\.)*\\d+)|xp|me|ce)\\b",
          RegexOption.IGNORE_CASE)
  val winMatch = winRegex.find(ua)
  if (winMatch != null) {
    return when (val raw = winMatch.groupValues[1].lowercase()) {
      "6.4",
      "10.0" -> "10.0" // some FF versions used 6.4 for Win10
      "6.3",
      "8.1" -> "8.1"
      "6.2",
      "8.0" -> "8"
      "6.1",
      "7.0" -> "7"
      "6.0" -> "Vista"
      "5.2" -> "Server 2003"
      "5.1" -> "XP"
      "5.01" -> "2000 SP1"
      "5.0" -> "2000"
      "4.0" -> "4.0"
      else -> raw
    }
  }

  val linuxRegex = Regex("(?!.*android.*)(linux|x11|ubuntu)", RegexOption.IGNORE_CASE)
  if (linuxRegex.containsMatchIn(ua)) {
    return "unknown"
  }

  return "unknown"
}

internal actual fun getCompilationTarget(): CompilationTarget {
  return CompilationTarget.JS
}

internal actual fun getEnvironmentMap(): Map<String, String> {
  return emptyMap()
}

internal actual fun getIsDebug(): Boolean {
  // The check happens at **runtime**, but the value was hard-coded by webpack
  return js("process.env.NODE_ENV") == "development"
}