package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mx.ipn.upiicsa.dentiagenda.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginaInicio(navController: NavController) {

        val isPreview = LocalInspectionMode.current
    var citaSeleccionada by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agenda tu cita") }
            )
        },
        bottomBar = {
            Column {

                // 🔵 BOTÓN CONTINUAR
                Button(
                    onClick = {
                        navController.navigate("servicios")
                    },
                    enabled = citaSeleccionada,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text("Continuar")
                }

                // 🔽 BARRA DE NAVEGACIÓN
                NavigationBar {

                    NavigationBarItem(
                        selected = true,
                        onClick = {   navController.navigate("home")},
                        icon = { Icon(Icons.Default.Home, null) },
                        label = { Text("Inicio") }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("PerfilUsuario") },
                        icon = { Icon(Icons.Default.Person, null) },
                        label = { Text("Perfil") }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("CitasScreen") },
                        icon = { Icon(Icons.Default.DateRange, null) },
                        label = { Text("Citas") }
                    )

                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            navController.navigate("login") {
                                popUpTo(0)
                            }
                        },
                        icon = { Icon(Icons.Default.ExitToApp, null) },
                        label = { Text("Salir") }
                    )
                }
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFC7E3FA))
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Dentiagenda",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)

            )

            // 🦷 CARD SELECCIONABLE
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clickable {
                        citaSeleccionada = true
                    },
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (citaSeleccionada)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        Color.White
                ),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "Agenda tu cita",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold

                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // 🖼️ Imagen
            if (isPreview) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(Color.White, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen diente", color = Color.Gray)
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.diente),
                    contentDescription = "Imagen diente",
                    modifier = Modifier.size(180.dp)
                )
            }
        }
    }
}

/* ---------------- PREVIEW ---------------- */

@Preview(showBackground = true)
@Composable
fun PaginaInicioPreview() {
    PaginaInicio(navController = rememberNavController())
}
