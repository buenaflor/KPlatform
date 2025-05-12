package com.giancarlobuenaflor.kplatform

import kotlin.experimental.ExperimentalNativeApi

@OptIn(ExperimentalNativeApi::class)
internal actual fun getIsDebug(): Boolean {
  return kotlin.native.Platform.isDebugBinary
}
