package com.example.saltasalta.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saltasalta.data.models.UserResponse
import com.example.saltasalta.ui.theme.componets.CustomTextField
import com.example.saltasalta.ui.theme.componets.MascotCharacter
import com.example.saltasalta.ui.theme.componets.WhiteButton

@Composable
fun EditProfileScreen(
    currentUser: com.example.saltasalta.data.models.UserResponse,
    onBackClick: () -> Unit = {},
    onSaveClick: (String, String, String) -> Unit = { _, _, _ -> },
    onLogoutClick: () -> Unit = {}
) {
    var user by remember { mutableStateOf(currentUser.username) }
    var password by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
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

            // Tarjeta principal - centrada verticalmente
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .padding(horizontal = 24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF111111)
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Título
                        Text(
                            text = "Editar perfíl",
                            color = Color.White,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Usuario
                        Text(
                            text = "Usuario",
                            color = Color(0xFFCCCCCC),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )

                        CustomTextField(
                            value = user,
                            onValueChange = { user = it },
                            hint = "Usuario"
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Contraseña
                        Text(
                            text = "Contraseña",
                            color = Color(0xFFCCCCCC),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )

                        CustomTextField(
                            value = password,
                            onValueChange = { password = it },
                            hint = "********",
                            isPassword = true
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Cambiar contraseña
                        Text(
                            text = "Cambiar contraseña",
                            color = Color(0xFFCCCCCC),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 4.dp)
                        )

                        CustomTextField(
                            value = newPassword,
                            onValueChange = { newPassword = it },
                            hint = "Nueva contraseña",
                            isPassword = true
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón guardar
                        WhiteButton(
                            onClick = { onSaveClick(user, password, newPassword) },
                            modifier = Modifier.fillMaxWidth(),
                            label = "Guardar"
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Cerrar sesión
                        Text(
                            text = "Cerrar sesión",
                            color = Color(0xFFCCCCCC),
                            modifier = Modifier
                                .clickable { onLogoutClick() }
                                .padding(5.dp)
                        )
                    }
                }
            }

            // Mascota - posicionada en la esquina inferior derecha
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .size(110.dp)
                    .padding(end = 24.dp, bottom = 24.dp)
            ) {
                MascotCharacter()
            }
        }
    }
}
@Preview(
    name = "Edit Profile Screen - Dark Mode",
    showBackground = true,
    showSystemUi = true,
    backgroundColor = 0xFF000000 // Color de fondo negro para el preview
)
@Composable
fun EditProfileScreenPreview() {
    // Creamos un usuario de prueba (dummy data)
    val dummyUser = UserResponse(
        123,
        "GotaAventurera",
         "gota@example.com"
    )

    // Llamamos a la pantalla pasándole los datos de prueba y lambdas vacías
    EditProfileScreen(
        currentUser = dummyUser,
        onBackClick = { /* Simulación de clic en regresar */ },
        onSaveClick = { user, oldPass, newPass ->
            // Podemos imprimir en consola para verificar que funciona
            println("Guardar: User=$user, OldPass=$oldPass, NewPass=$newPass")
        },
        onLogoutClick = { /* Simulación de clic en cerrar sesión */ }
    )
}
