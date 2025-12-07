package com.example.saltasalta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.ui.theme.componets.WhiteButton

@Composable
fun HomeScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateToGame: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "SaltaSalta",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            WhiteButton(
                onClick = onNavigateToProfile,
                modifier = Modifier.fillMaxWidth(),
                label = "Editar Perfil"
            )

            WhiteButton(
                onClick = onNavigateToGame,
                modifier = Modifier.fillMaxWidth(),
                label = "Jugar"
            )
        }
    }
}

