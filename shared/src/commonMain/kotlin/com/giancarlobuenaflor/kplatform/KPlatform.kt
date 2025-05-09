package com.giancarlobuenaflor.kplatform

/** The [Platform] implementation for the system that delegates to the specific implementations. */
public class KPlatform : Platform {
  override val operatingSystem: String
    get() = getOperatingSystem()

  override val operatingSystemVersion: String
    get() = getOperatingSystemVersion()
}
