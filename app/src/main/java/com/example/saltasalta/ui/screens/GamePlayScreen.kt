package com.example.saltasalta.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saltasalta.ui.componets.LoginAvatar

data class PlatformRect(
    val xPercent: Float,
    val yPercent: Float,
    val widthDp: Float = 110f,
    val heightDp: Float = 16f
)

@Composable
fun GamePlayScreen() {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F0F0F))
    ) {
        val screenW = maxWidth
        val screenH = maxHeight

        // Plataformas rectangulares gris oscuro
        val platforms = listOf(
            PlatformRect(0.62f, 0.0f, 150f),
            PlatformRect(0.0f, 0.12f, 140f),
            PlatformRect(0.15f, 0.22f, 150f),
            PlatformRect(0.55f, 0.30f, 130f),
            PlatformRect(0.25f, 0.42f, 150f),
            PlatformRect(0.05f, 0.7f, 140f),
            PlatformRect(0.62f, 0.54f, 150f)
        )

        platforms.forEach { p ->
            Box(
                modifier = Modifier
                    .offset(
                        x = screenW * p.xPercent,
                        y = screenH * p.yPercent
                    )
                    .width(p.widthDp.dp)
                    .height(p.heightDp.dp)
                    .background(Color(0xFF3A3A3A), RoundedCornerShape(4.dp))
            )
        }

        // Personaje en plataforma inferior izquierda
        Box(
            modifier = Modifier
                .offset(
                    x = screenW * 0.53f,
                    y = screenH * 0.71f
                )
                .size(95.dp),
            contentAlignment = Alignment.Center
        ) {
            LoginAvatar(modifier = Modifier.fillMaxWidth())
        }

        // Plataforma donde está el personaje
        Box(
            modifier = Modifier
                .offset(
                    x = screenW * 0.50f,
                    y = screenH * 0.82f
                )
                .width(150.dp)
                .height(16.dp)
                .background(Color(0xFF3A3A3A), RoundedCornerShape(4.dp))
        )

        // Montañas inferiores
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .align(Alignment.BottomCenter)
        ) {
            val terrainColor = Color(0xFF1A1A1A)
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 0.15f, size.height * 0.55f)
                lineTo(size.width * 0.35f, size.height * 0.85f)
                lineTo(size.width * 0.6f, size.height * 0.6f)
                lineTo(size.width * 0.85f, size.height * 0.9f)
                lineTo(size.width, size.height * 0.65f)
                lineTo(size.width, size.height)
                close()
            }
            drawPath(path, terrainColor)
        }

        // Texto de score en la esquina inferior izquierda
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 12.dp, bottom = 8.dp)
        ) {
            Text(text = "score:", color = Color.White)
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFF000000,
    device = "spec:width=411dp,height=891dp"
)
@Composable
fun PreviewGamePlayScreen() {
    GamePlayScreen()
}

