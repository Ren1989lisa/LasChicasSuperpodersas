package com.example.saltasalta.ui.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.saltasalta.R
import com.example.saltasalta.ui.components.cards.TopPlayersCard

@Composable
fun TopPlayersScreen(onBack: () -> Unit) {

    val playersList = listOf(
        "1  -  PepaRizA",
        "2  -  Peme_Se_Jodee",
        "3  -  NotEfelis",
        "4  -  CiUdErS",
        "5  -  PayuKytle"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E0E0E))
    ) {

        /** BOTÓN DE REGRESO */
        IconButton(
            onClick = onBack,
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.bobolon),
                contentDescription = "back",
                tint = Color.White
            )
        }

        /** CARD PRINCIPAL */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 120.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopPlayersCard(players = playersList)
        }

        /** MUÑEQUITOS DECORATIVOS */
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            verticalAlignment = Alignment.Bottom
        ) {

            Image(
                painter = painterResource(id = R.drawable.bobolon),
                contentDescription = "player1",
                modifier = Modifier
                    .size(65.dp)
                    .padding(end = 30.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.bobolon),
                contentDescription = "player2",
                modifier = Modifier
                    .size(65.dp)
                    .padding(end = 30.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.bobolon),
                contentDescription = "player3",
                modifier = Modifier.size(65.dp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewTopPlayersScreen() {
    TopPlayersScreen(onBack = { })
    }