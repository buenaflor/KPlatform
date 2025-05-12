package com.giancarlobuenaflor.kplatform

/**
 * Provides an API for retrieving platform information. Allowing the Platform to be an interface
 * allows for the use of these APIs in tests, where you can provide mock implementations.
 */
public interface Platform {
  /**
   * The compilation target of the current platform.
   *
   * Example usage:
   * ```kotlin
   * val platform = KPlatform()
   *
   * if (platform.compilationTarget.isWeb) {
   *   // Execute code that should only run on web targets
   * }
   *
   * // or use a switch
   *
   * when (platform.compilationTarget) {
   *   CompilationTarget.JS -> {
   *     // Execute code that should only run on JS targets
   *   }
   *   else -> {
   *     // Do something else
   *   }
   * }
   */
  public val compilationTarget: CompilationTarget

  /**
   * The runtime operating system of the current platform.
   *
   * This is not necessarily the same as the compilation target. For example, a JVM compiled app can
   * run on any operating system that supports a JVM.
   *
   * Example usage:
   * ```kotlin
   * val platform = KPlatform()
   *
   * if (platform.operatingSystem.isWindows) {
   *   // Execute code that should only run on Windows
   * }
   *
   * // or use a switch
   *
   * when (platform.operatingSystem) {
   *   is OperatingSystem.Windows -> {
   *     // Execute code that should only run on Windows
   *   }
   *   else -> {
   *     // Do something else
   *   }
   * }
   * ```
   */
  public val operatingSystem: OperatingSystem

  /** Returns true if binary was compiled in debug mode. */
  public val isDebug: Boolean

  /**
   * The environment for this process.
   *
   * The returned environment is a read-only map.
   */
  public val environment: Map<String, String>
}

/** Enumeration of Kotlin Multiplatform compilation targets. */
public enum class CompilationTarget(public val targetName: String) {
  JVM("jvm"),
  ANDROID("androidTarget"),
  JS("js"),
  WASMJS("wasmjs"),
  IOSX64("iosX64"),
  IOSARM64("iosArm64"),
  IOSSIMULATORARM64("iosSimulatorArm64"),
  MACOSARM64("macosArm64"),
  MACOSX64("macosX64"),
  TVOSX64("tvosX64"),
  TVOSARM64("tvosArm64"),
  TVOSSIMULATORARM64("tvosSimulatorArm64"),
  WATCHOSX64("watchosX64"),
  WATCHOSARM64("watchosArm64"),
  WATCHOSSIMULATORARM64("watchosSimulatorArm64");

  /** Returns true if this compilation target is an Android target. */
  public val isAndroid: Boolean
    get() = this == ANDROID

  /** Returns true if this compilation target is a web target. */
  public val isWeb: Boolean
    get() = this == JS || this == WASMJS

  /** Returns true if this compilation target is an iOS target. */
  public val isIOS: Boolean
    get() = this == IOSX64 || this == IOSARM64 || this == IOSSIMULATORARM64

  /** Returns true if this compilation target is a macOS target. */
  public val isMacOS: Boolean
    get() = this == MACOSARM64 || this == MACOSX64

  /** Returns true if this compilation target is a tvOS target. */
  public val isTvOS: Boolean
    get() = this == TVOSX64 || this == TVOSARM64 || this == TVOSSIMULATORARM64

  /** Returns true if this compilation target is a watchOS target. */
  public val isWatchOS: Boolean
    get() = this == WATCHOSX64 || this == WATCHOSARM64 || this == WATCHOSSIMULATORARM64

  /** Returns true if this compilation target is a mobile target. */
  public val isMobile: Boolean
    get() = this == ANDROID || isIOS

  override fun toString(): String {
    return targetName
  }
}

/** Enumeration of runtime operating systems. */
public sealed class OperatingSystem(public val family: Family, public val version: String) {
  public enum class Family(private val displayName: String) {
    ANDROID("Android"),
    IOS("iOS"),
    MACOS("macOS"),
    TVOS("tvOS"),
    WATCHOS("watchOS"),
    LINUX("Linux"),
    WINDOWS("Windows"),
    UNKNOWN("Unknown");

    /** Returns a human-readable name for this family. */
    override fun toString(): String = displayName
  }

  public class Android(version: String) : OperatingSystem(Family.ANDROID, version)

  public class Ios(version: String) : OperatingSystem(Family.IOS, version)

  public class MacOs(version: String) : OperatingSystem(Family.MACOS, version)

  public class TvOs(version: String) : OperatingSystem(Family.TVOS, version)

  public class WatchOs(version: String) : OperatingSystem(Family.WATCHOS, version)

  public class Linux(version: String) : OperatingSystem(Family.LINUX, version)

  public class Windows(version: String) : OperatingSystem(Family.WINDOWS, version)

  public class Unknown(version: String) : OperatingSystem(Family.UNKNOWN, version)

  public val isAndroid: Boolean
    get() = family == Family.ANDROID

  public val isIOS: Boolean
    get() = family == Family.IOS

  public val isMacOS: Boolean
    get() = family == Family.MACOS

  public val isTvOS: Boolean
    get() = family == Family.TVOS

  public val isWatchOS: Boolean
    get() = family == Family.WATCHOS

  public val isLinux: Boolean
    get() = family == Family.LINUX

  public val isWindows: Boolean
    get() = family == Family.WINDOWS

  public companion object {
    /**
     * Build an [OperatingSystem] from the raw strings returned by the platform-specific `expect`
     * functions.
     */
    public fun from(osName: String, version: String): OperatingSystem =
        when (osName.lowercase()) {
          "android" -> Android(version)
          "ios" -> Ios(version)
          "macos" -> MacOs(version)
          "tvos" -> TvOs(version)
          "watchos" -> WatchOs(version)
          "linux" -> Linux(version)
          "windows" -> Windows(version)
          else -> Unknown(version)
        }

    /** Convenience method to build an [OperatingSystem] from a [Family] and a version string. */
    public fun from(family: Family, version: String): OperatingSystem =
        when (family) {
          Family.ANDROID -> Android(version)
          Family.IOS -> Ios(version)
          Family.MACOS -> MacOs(version)
          Family.TVOS -> TvOs(version)
          Family.WATCHOS -> WatchOs(version)
          Family.LINUX -> Linux(version)
          Family.WINDOWS -> Windows(version)
          else -> Unknown(version)
        }
  }

  override fun toString(): String = "$family $version"
}
