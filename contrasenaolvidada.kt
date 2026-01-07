package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import mx.ipn.upiicsa.dentiagenda.R

@Composable
fun ContrasenaOlvidadaScreen(
    onObtenerCodigoClick: () -> Unit = {},
    onValidarCodigoClick: () -> Unit = {},
    onVolverInicioClick: () -> Unit = {},
    onReenviarCodigoClick: () -> Unit = {}
) {

    var correo by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3FA))
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título principal
        Text(
            text = "DentiAgenda",
            fontSize = 34.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Texto explicativo
        Text(
            text = "Si olvidaste tu contraseña puedes escribir aquí abajo el correo que ocupaste para que recibas un código.",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Campo de correo electrónico
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón: Obtener Código
        Button(
            onClick = onObtenerCodigoClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .width(270.dp)
                .height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Obtener Código",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Texto: Si no obtuviste el código boton
        Text(
            text = "Si no obtuviste el código",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón: Volver a mandar código boton
        Button(
            onClick = onReenviarCodigoClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .width(270.dp)
                .height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Volver a mandar código",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Campo para ingresar código botton
        Text(
            text = "Ingresar Código:",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = codigo,
            onValueChange = { codigo = it },
            label = { Text("Código") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón: Validar Código
        Button(
            onClick = onValidarCodigoClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .width(270.dp)
                .height(50.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Validar Código",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(60.dp))

        // Texto inferior: volver a inicio
        TextButton(onClick = onVolverInicioClick) {
            Text(
                text = "VOLVER A INICIO DE SESIÓN",
                fontSize = 20.sp,
                color = Color(0xFF3F51B5),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
