package com.giancarlobuenaflor.kplatform

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UnsafeNumber
import kotlinx.cinterop.useContents
import platform.Foundation.NSProcessInfo

@OptIn(ExperimentalForeignApi::class, UnsafeNumber::class)
internal actual fun getOperatingSystemVersionString(): String {
  val osVersion = NSProcessInfo.processInfo.operatingSystemVersion
  osVersion.useContents {
    val major = majorVersion
    val minor = minorVersion
    val patch = patchVersion
    return "$major.$minor.$patch"
  }
}
