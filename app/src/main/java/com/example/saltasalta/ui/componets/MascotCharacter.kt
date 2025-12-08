package com.example.saltasalta.ui.theme.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun MascotCharacter(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2

        // Cuerpo (forma redondeada/ovalada)
        drawOval(
            color = Color.White,
            topLeft = Offset(width * 0.2f, height * 0.5f),
            size = Size(width * 0.6f, height * 0.5f)
        )

        // Cabeza (círculo)
        drawCircle(
            color = Color.White,
            radius = width * 0.35f,
            center = Offset(centerX, height * 0.35f)
        )

        // Ojos grandes y oscuros
        val eyeSize = width * 0.08f
        val eyeY = height * 0.28f
        val eyeSpacing = width * 0.15f

        drawCircle(
            color = Color.Black,
            radius = eyeSize,
            center = Offset(centerX - eyeSpacing, eyeY)
        )
        drawCircle(
            color = Color.Black,
            radius = eyeSize,
            center = Offset(centerX + eyeSpacing, eyeY)
        )

        // Mejillas rosadas pequeñas
        val cheekSize = width * 0.06f
        val cheekY = height * 0.35f
        val cheekXOffset = width * 0.2f

        drawCircle(
            color = Color(0xFFFFB6C1), // Rosa claro
            radius = cheekSize,
            center = Offset(centerX - cheekXOffset, cheekY)
        )
        drawCircle(
            color = Color(0xFFFFB6C1), // Rosa claro
            radius = cheekSize,
            center = Offset(centerX + cheekXOffset, cheekY)
        )

        // Boca/nariz (V invertida pequeña)
        val mouthY = height * 0.4f
        val mouthWidth = width * 0.08f
        val mouthHeight = height * 0.03f

        val mouthPath = Path().apply {
            moveTo(centerX - mouthWidth / 2, mouthY)
            lineTo(centerX, mouthY + mouthHeight)
            lineTo(centerX + mouthWidth / 2, mouthY)
            close()
        }
        drawPath(
            path = mouthPath,
            color = Color(0xFF888888) // Gris claro
        )
    }
}

