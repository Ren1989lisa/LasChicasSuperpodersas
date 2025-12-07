package com.example.saltasalta.ui.theme.componets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun GameCharacter(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height
        val centerX = width / 2
        val centerY = height / 2

        // Cuerpo (forma ovalada)
        drawOval(
            color = Color.White,
            topLeft = Offset(width * 0.2f, height * 0.3f),
            size = Size(width * 0.6f, height * 0.7f)
        )

        // Ojos grandes y ovalados negros
        val eyeWidth = width * 0.12f
        val eyeHeight = width * 0.15f
        val eyeY = height * 0.35f
        val eyeSpacing = width * 0.2f

        // Ojo izquierdo
        drawOval(
            color = Color.Black,
            topLeft = Offset(centerX - eyeSpacing - eyeWidth / 2, eyeY),
            size = Size(eyeWidth, eyeHeight)
        )

        // Ojo derecho
        drawOval(
            color = Color.Black,
            topLeft = Offset(centerX + eyeSpacing - eyeWidth / 2, eyeY),
            size = Size(eyeWidth, eyeHeight)
        )

        // Mejillas rosadas pequeñas
        val cheekSize = width * 0.08f
        val cheekY = height * 0.5f
        val cheekXOffset = width * 0.25f

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

        // Boca sonriente (V invertida más grande y sonriente)
        val mouthY = height * 0.6f
        val mouthWidth = width * 0.15f
        val mouthHeight = height * 0.05f

        val mouthPath = Path().apply {
            moveTo(centerX - mouthWidth / 2, mouthY)
            quadraticBezierTo(
                centerX, mouthY + mouthHeight * 2,
                centerX + mouthWidth / 2, mouthY
            )
            lineTo(centerX + mouthWidth / 2, mouthY + mouthHeight)
            quadraticBezierTo(
                centerX, mouthY + mouthHeight * 3,
                centerX - mouthWidth / 2, mouthY + mouthHeight
            )
            close()
        }
        drawPath(
            path = mouthPath,
            color = Color(0xFF666666) // Gris oscuro
        )
    }
}

