package com.giancarlobuenaflor.kplatform

/**
 * The [Platform] implementation for the system that delegates to the specific implementations.
 *
 * Simply create an instance of this class and use it to get the platform information.
 *
 * ```kotlin
 * val platform = KPlatform()
 * println(platform.operatingSystem.isAndroid)
 * println(platform.compilationTarget)
 * ```
 */
public class KPlatform : Platform {
  public override val operatingSystem: OperatingSystem
    get() = OperatingSystem.from(getOperatingSystemString(), getOperatingSystemVersionString())

  public override val compilationTarget: CompilationTarget
    get() = getCompilationTarget()

  override val environment: Map<String, String>
    get() = getEnvironmentMap()

  override val isDebug: Boolean
    get() = getIsDebug()
}

internal expect fun getOperatingSystemString(): String

internal expect fun getOperatingSystemVersionString(): String

internal expect fun getCompilationTarget(): CompilationTarget

internal expect fun getEnvironmentMap(): Map<String, String>

internal expect fun getIsDebug(): Boolean
