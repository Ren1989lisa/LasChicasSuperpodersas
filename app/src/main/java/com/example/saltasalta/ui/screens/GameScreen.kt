package com.example.saltasalta.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.saltasalta.ui.theme.componets.GameCharacter
import com.example.saltasalta.ui.theme.componets.TrianglePlatform

data class PlatformPosition(
    val xPercent: Float, // 0.0 a 1.0
    val yPercent: Float, // 0.0 a 1.0
    val sizeDp: Float = 80f
)

@Composable
fun GameScreen(
    onBackClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val screenWidth = maxWidth
        val screenHeight = maxHeight

        // Botón regresar - circular blanco con flecha
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = 24.dp, top = 24.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_revert),
                contentDescription = "Regresar",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }

        // Línea azul vertical en el borde izquierdo
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(4.dp)
                .background(Color(0xFF2196F3)) // Azul brillante
        )

        // Plataformas triangulares distribuidas por la pantalla (más grandes)
        val platforms = listOf(
            PlatformPosition(0.15f, 0.15f, 120f),
            PlatformPosition(0.4f, 0.3f, 140f),
            PlatformPosition(0.7f, 0.2f, 130f),
            PlatformPosition(0.25f, 0.45f, 150f),
            PlatformPosition(0.6f, 0.5f, 140f),
            PlatformPosition(0.85f, 0.4f, 130f),
            PlatformPosition(0.3f, 0.65f, 160f),
            PlatformPosition(0.65f, 0.7f, 150f),
            PlatformPosition(0.1f, 0.8f, 140f),
            PlatformPosition(0.5f, 0.85f, 170f),
            PlatformPosition(0.8f, 0.8f, 140f)
        )

        platforms.forEach { platform ->
            Box(
                modifier = Modifier
                    .offset(
                        x = (screenWidth * platform.xPercent) - (platform.sizeDp.dp / 2),
                        y = (screenHeight * platform.yPercent) - platform.sizeDp.dp
                    )
                    .size(platform.sizeDp.dp)
            ) {
                TrianglePlatform(
                    size = platform.sizeDp,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        // Personaje en la parte inferior izquierda sobre una plataforma
        Box(
            modifier = Modifier
                .offset(
                    x = screenWidth * 0.1f,
                    y = screenHeight * 0.75f
                )
                .size(80.dp)
        ) {
            GameCharacter()
        }

        // Formas geométricas grandes en la parte inferior (terreno)
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.15f)
                .offset(y = screenHeight * 0.85f)
        ) {
            val terrainColor = Color(0xFF1a1a1a)
            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(size.width * 0.2f, size.height * 0.3f)
                lineTo(size.width * 0.4f, 0f)
                lineTo(size.width * 0.6f, size.height * 0.2f)
                lineTo(size.width * 0.8f, size.height * 0.1f)
                lineTo(size.width, size.height * 0.25f)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            drawPath(path, terrainColor)
        }
    }
}

