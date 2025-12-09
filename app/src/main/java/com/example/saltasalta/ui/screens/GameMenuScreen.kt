package com.example.saltasalta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameMenuScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(top = 40.dp)
        ) {

            // Row de iconos
            Row(horizontalArrangement = Arrangement.spacedBy(30.dp)) {
                MenuIcon(text = "BLOB", icon = Icons.Default.Person)
                MenuIcon(text = "RUN", icon = Icons.Default.PlayArrow)
            }

            Spacer(Modifier.height(30.dp))

            // Carita
            FaceIcon()

            Spacer(Modifier.height(60.dp))

            // Botón JUGAR
            PlayButton()
        }
    }
}

@Composable
fun MenuIcon(text: String, icon: ImageVector) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = text, tint = Color.Black)
        }
        Spacer(Modifier.height(4.dp))
        Text(text, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun FaceIcon() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Carita sencilla
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        Modifier.size(14.dp).background(Color.Black, CircleShape)
                    )
                    Box(
                        Modifier.size(14.dp).background(Color.Black, CircleShape)
                    )
                }
                Spacer(Modifier.height(4.dp))
            }
        }
    }
}

@Composable
fun PlayButton() {
    Box(
        modifier = Modifier
            .size(140.dp)
            .graphicsLayer {
                shadowElevation = 40f
                shape = CircleShape
                clip = false
            }
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text("JUGAR", color = Color.Black, fontSize = 20.sp)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewGameMenuScreen() {
    GameMenuScreen()
    }
