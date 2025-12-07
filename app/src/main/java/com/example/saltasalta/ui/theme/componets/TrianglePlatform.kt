package com.example.saltasalta.ui.theme.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun TrianglePlatform(
    modifier: Modifier = Modifier,
    size: Float = 80f,
    color: Color = Color(0xFF333333)
) {
    Canvas(modifier = modifier.size(size.dp)) {
        val width = size
        val height = size

        // Triángulo apuntando hacia arriba (plataforma)
        val trianglePath = Path().apply {
            moveTo(width / 2, height) // Vértice inferior
            lineTo(0f, 0f) // Esquina superior izquierda
            lineTo(width, 0f) // Esquina superior derecha
            close()
        }

        drawPath(
            path = trianglePath,
            color = color
        )
    }
}

