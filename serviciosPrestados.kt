package mx.ipn.upiicsa.dentiagenda.views

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.entity.Servicio
import com.example.app_citas_barberia.viewmodel.CitaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiciosScreen(
    navController: NavController,
    citaViewModel: CitaViewModel
) {
    val servicios by citaViewModel.servicios.collectAsState()
    val servicioSeleccionado by citaViewModel.servicioSeleccionado.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona un servicio") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        },

        bottomBar = {
            Column {

                Button(
                    onClick = {
                        navController.navigate("dentistas")
                    },
                    enabled = servicioSeleccionado != null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text("Continuar")
                }

                NavigationBar {

                    NavigationBarItem(
                        selected = false,
                        onClick = { navController.navigate("home") },
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
                        selected = true,
                        onClick = { navController.navigate("CitasScreen")},
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
                        icon = { Icon(Icons.Default.ArrowBack, null) },
                        label = { Text("Cerrar Sesion") }
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
                .padding(16.dp)
        ) {
            servicios.forEach { servicio ->
                ServicioCard(
                    servicio = servicio,
                    seleccionado =servicio ==servicioSeleccionado,
                    onSelect = {
                        citaViewModel.seleccionarServicio(servicio)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

/* ---------------- CARD DE SERVICIO ---------------- */

@Composable
fun ServicioCard(
servicio: Servicio,
seleccionado: Boolean,
onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        colors = CardDefaults.cardColors(
            containerColor = if (seleccionado)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = servicio.nombre,
                style = MaterialTheme.typography.titleMedium
            )
            servicio.precio?.let {
                Text(
                    text = "$$it MXN",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun ServiciosScreenPreview() {
    val navController = rememberNavController()
    val citaViewModel = CitaViewModel()
    ServiciosScreen(navController = navController,
        citaViewModel= citaViewModel)
}
