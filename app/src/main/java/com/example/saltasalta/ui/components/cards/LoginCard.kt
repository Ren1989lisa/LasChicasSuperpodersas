package com.example.saltasalta.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginCard(
    modifier: Modifier = Modifier
){
    //Card(
    //        modifier = modifier
    //            .width(80.dp)
    //            .height(150.dp),
    //        colors = CardDefaults.cardColors(
    //            containerColor = Color(0xFF2a2c33)
    //        ),
    //    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPeliFavCard() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1b1d23)),
        contentAlignment = Alignment.Center
    ) {
        LoginCard(  modifier = Modifier.width(100.dp).height(150.dp))
    }
}