import com.giancarlobuenaflor.kplatform.KPlatform
import com.giancarlobuenaflor.kplatform.OperatingSystem
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PlatformTest {
  private lateinit var platform: KPlatform

  @BeforeTest
  fun setUp() {
    platform = KPlatform()
  }

  @Test
  fun `operatingSystem returns correct values`() {
    // TODO: these are primitive tests
    val operatingSystem = platform.operatingSystem
    val environment = platform.environment
    val osName = environment["OS_NAME"]
    // This is the workflow config target, not the compilation target
    val targetName = environment["TARGET_NAME"]

    val simulatorDeviceName = environment["SIMULATOR_DEVICE_NAME"]
    if (simulatorDeviceName != null) {
      if (simulatorDeviceName.contains("iPhone")) {
        assertEquals(OperatingSystem.Family.IOS, operatingSystem.family)
      }
      if (simulatorDeviceName.contains("Apple TV")) {
        assertEquals(OperatingSystem.Family.TVOS, operatingSystem.family)
      }
      if (simulatorDeviceName.contains("Apple Watch")) {
        assertEquals(OperatingSystem.Family.WATCHOS, operatingSystem.family)
      }
    }

    if (osName == null || targetName == null) {
      return
    }

    if (targetName == "android") {
      assertEquals(OperatingSystem.Family.ANDROID, operatingSystem.family)
    } else {
      if (osName.contains("ubuntu")) {
        assertEquals(OperatingSystem.Family.LINUX, operatingSystem.family)
      }
      if (osName.contains("macos")) {
        assertEquals(OperatingSystem.Family.MACOS, operatingSystem.family)
      }
    }
  }

  @Test
  fun `compilationTarget returns correct values`() {
    // TODO: these are primitive tests
    val environment = platform.environment
    val osName = environment["OS_NAME"]
    // This is the workflow config target, not the compilation target
    val targetName = environment["TARGET_NAME"]

    val simulatorDeviceName = environment["SIMULATOR_DEVICE_NAME"]
    if (simulatorDeviceName != null) {
      if (simulatorDeviceName.contains("iPhone")) {
        assertTrue(platform.compilationTarget.isIOS)
        assertTrue(platform.compilationTarget.isMobile)
      }
      if (simulatorDeviceName.contains("Apple TV")) {
        assertTrue(platform.compilationTarget.isTvOS)
      }
      if (simulatorDeviceName.contains("Apple Watch")) {
        assertTrue(platform.compilationTarget.isWatchOS)
      }
    }

    if (osName == null || targetName == null) {
      return
    }

    if (targetName == "android") {
      assertTrue(platform.compilationTarget.isAndroid)
      assertTrue(platform.compilationTarget.isMobile)
    }
  }
}
