package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NuevaContrasenaScreen(
    onCambiarClick: () -> Unit = {},
    onVolverClick: () -> Unit = {}
) {
    var nuevaContrasena by remember { mutableStateOf("") }
    var repetirContrasena by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3FA))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "DentiAgenda",
                fontSize = 34.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Escribe una nueva contraseña para iniciar sesión",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nuevaContrasena,
                onValueChange = { nuevaContrasena = it },
                label = { Text("Nueva contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Vuelve a escribir la contraseña",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = repetirContrasena,
                onValueChange = { repetirContrasena = it },
                label = { Text("Repite tu contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onCambiarClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF040404)),
                modifier = Modifier
                    .width(269.dp)
                    .height(49.dp)
            ) {
                Text(
                    text = "Cambiar contraseña",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Cuando cambies tu contraseña, la aplicación te redirigirá automáticamente a iniciar sesión",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "VOLVER A INICIO DE SESIÓN",
                fontSize = 20.sp,
                color = Color(0xFF3F51B5),
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.clickable { onVolverClick() },
                textAlign = TextAlign.Center
            )
        }
    }
}
