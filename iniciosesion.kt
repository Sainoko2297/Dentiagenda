package mx.ipn.upiicsa.dentiagenda.views

import android.util.Base64
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.entity.Rol
import mx.ipn.upiicsa.dentiagenda.R
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DentiAgendaLoginScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3FA))
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Spacer(Modifier.height(40.dp))

        Text(
            text = "DentiAgenda",
            color = Color(0xFF000000),
            fontSize = 34.sp,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Bienvenido a DentiAgenda, una aplicación para agendar citas con tu dentista.",
            color = Color(0xFF000000),
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )

        Spacer(Modifier.height(32.dp))

        Button(
            onClick = {
                Log.d("LOGIN", "BOTÓN PRESIONADO")

                if (email.isBlank() || password.isBlank()) {
                    Log.d("LOGIN", "Campos vacíos")
                    return@Button
                }

                val passwordHashed = mx.ipn.upiicsa.dentiagenda.views.sha512Base64(password)

                // 🔐 Hash REAL de "admin123" (SHA-512 + Base64)
                val ADMINHASH =
                    "f89Lo5HEh4Tt3lmYidbj8eR6J9s27MBQzJLyWb+sOK+tLGihroBNdwdej7ciUD8+yissEAbub2x7dijLRf/9HQ=="
                Log.d("LOGIN", "Hash ingresado: $passwordHashed")

                val USERHASH =
                    "BoRP5mtf03U97i7nwV958eKZVqCi5yS/6458+0KKEILLDrkCaUiBDEBjIaT7cyrWgQXrLcEZ1fLGT0FNM58SlA=="

                val PRESTADORHAS =
                    "BoRP5mtf03U97i7nwV958eKZVqCi5yS/6458+0KKEILLDrkCaUiBDEBjIaT7cyrWgQXrLcEZ1fLGT0FNM58SlA=="


                val rol = when {
                    email == "admin@gmail.com" && passwordHashed == ADMINHASH ->
                        Rol.ADMIN
                    email == "NieR@gmail.com" && passwordHashed == USERHASH ->
                        Rol.USUARIO
                    email == "Prestador@gmail.com" && passwordHashed == PRESTADORHAS ->
                        Rol.PRESTADOR
                    else -> null
                }
                when (rol){
                    Rol.ADMIN->{
                        Log.d("LOGIN","Login ADMIN correcto")
                        navController.navigate("admin"){
                            popUpTo("login"){inclusive = true}
                        }
                    }
                    Rol.USUARIO -> {
                        Log.d("LOGIN", "Login USUARIO correcto")
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                    Rol.PRESTADOR ->
                    {
                        Log.d("LOGIN", "Login USUARIO correcto")
                        navController.navigate("Prestador") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                    null -> {
                        Log.d("LOGIN", "Usuario o contraseña incorrectos")
                    }
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(Modifier.height(24.dp))



        Spacer(Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = {
            navController.navigate("Registro"){
                popUpTo("Login") {inclusive = true}
            }
        }
        ) {
            Text(
                text = "¿No tienes cuenta? registrate",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF1E88E5))
        }
    }
}

fun sha512Base64(texto: String): String {
    val md = MessageDigest.getInstance("SHA-512")
    val digest = md.digest(texto.toByteArray(Charsets.UTF_8))
    return Base64.encodeToString(digest, Base64.NO_WRAP)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DentiAgendaLoginPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        DentiAgendaLoginScreen(navController = navController)
    }
}
