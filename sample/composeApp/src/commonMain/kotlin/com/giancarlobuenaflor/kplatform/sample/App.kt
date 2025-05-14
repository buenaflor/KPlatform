package com.giancarlobuenaflor.kplatform.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.giancarlobuenaflor.kplatform.KPlatform
import org.jetbrains.compose.ui.tooling.preview.Preview

private val platform = KPlatform()

@Composable
@Preview
fun App() {
  MaterialTheme {
    Box(
        modifier =
            Modifier.fillMaxSize()
                .background(
                    brush =
                        Brush.verticalGradient(
                            colors =
                                listOf(Color(0xFF000000), Color(0xFF693A5D), Color(0xFFFF7236))))
                .padding(16.dp)
                .wrapContentSize(Alignment.Center)) {
          val compilationTargetText =
              if (platform.compilationTarget.isMobile) {
                "This app is running on a mobile device."
              } else if (platform.compilationTarget.isWeb) {
                "This app is running in a web browser."
              } else {
                "This app is running on ${platform.compilationTarget.name}"
              }
          val operatingSystemText = "Operating System: ${platform.operatingSystem}"
          val isDebugText = "Is debug build: ${platform.isDebug}"
          Column(
              verticalArrangement = Arrangement.spacedBy(10.dp),
              horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = compilationTargetText,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = operatingSystemText,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = isDebugText,
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                )
              }
        }
  }
}
