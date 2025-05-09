package com.giancarlobuenaflor.kplatform

/**
 * Provides an API for retrieving platform information. Allowing the Platform to be an interface
 * allows for the use of these APIs in tests, where you can provide mock implementations.
 */
public interface Platform {
  /**
   * A string representing the operating system.
   *
   * The possible return values are available from [operatingSystemValues], and there are constants
   * for each of the platforms to use in switch statements or conditionals (See [ANDROID], [IOS],
   * [MACOS], [TVOS], [WATCHOS], [WINDOWS] and [LINUX]).
   *
   * Example:
   * ```kotlin
   * val platform = KPlatform()
   *
   * when (platform.operatingSystem) {
   *   Platform.ANDROID -> println("This is an Android device")
   *   Platform.IOS -> println("This is an iOS device")
   * }
   * ```
   */
  public val operatingSystem: String

  /** A string representing the version of the operating system or platform. */
  public val operatingSystemVersion: String

  /** True if the operating system is Android. */
  public val isAndroid: Boolean
    get() = operatingSystem == ANDROID

  /** True if the operating system is iOS. */
  public val isIOS: Boolean
    get() = operatingSystem == IOS

  /** True if the operating system is macOS. */
  public val isMacOS: Boolean
    get() = operatingSystem == MACOS

  /** True if the operating system is Android. */
  public val isTvOS: Boolean
    get() = operatingSystem == MACOS

  /** True if the operating system is watchOS. */
  public val isWatchOS: Boolean
    get() = operatingSystem == WATCHOS

  /** True if the operating system is Windows. */
  public val isWindows: Boolean
    get() = operatingSystem == WINDOWS

  /** True if the operating system is Linux. */
  public val isLinux: Boolean
    get() = operatingSystem == LINUX

  public companion object {
    /** A list of the possible values that [operatingSystem] can return. */
    public val operatingSystemValues: List<String> =
        listOf(LINUX, MACOS, WINDOWS, ANDROID, IOS, TVOS, WATCHOS)

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is Android.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is Android, use [isAndroid].
     */
    public const val ANDROID: String = "android"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is iOS.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is iOS, use [isIOS].
     */
    public const val IOS: String = "ios"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is macOS.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is macOS, use [isMacOS].
     */
    public const val MACOS: String = "macos"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is tvOS.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is tvOS, use [isTvOS].
     */
    public const val TVOS: String = "tvos"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is watchOS.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is watchOS, use [isWatchOS].
     */
    public const val WATCHOS: String = "watchos"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is Windows.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is Windows, use [isWindows].
     */
    public const val WINDOWS: String = "windows"

    /**
     * A string constant to compare with [operatingSystem] to see if the platform is Linux.
     *
     * Useful in case statements when switching on [operatingSystem].
     *
     * To just check if the platform is Linux, use [isLinux].
     */
    public const val LINUX: String = "linux"
  }
}

internal expect fun getOperatingSystem(): String

internal expect fun getOperatingSystemVersion(): String
