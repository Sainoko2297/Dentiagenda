package mx.ipn.upiicsa.dentiagenda.views.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
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

@Composable
fun AdminServiciosScreen(
    navController: NavController,
    citaViewModel: CitaViewModel
) {
    val servicios by citaViewModel.servicios.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = { BottomNavigationBa(navController) },
        containerColor = Color(0xFFC7E3FA)
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Administrar Servicios",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(8.dp))

            OutlinedTextField(nombre, { nombre = it }, label = { Text("Nombre") })
            OutlinedTextField(descripcion, { descripcion = it }, label = { Text("Descripción") })
            OutlinedTextField(duracion, { duracion = it }, label = { Text("Duración (min)") })
            OutlinedTextField(precio, { precio = it }, label = { Text("Precio") })

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    citaViewModel.agregarServicio(
                        Servicio(
                            id = servicios.size + 1,
                            nombre = nombre,
                            descripcion = descripcion,
                            duracion = duracion.toIntOrNull(),
                            precio = precio.toDoubleOrNull()
                        )
                    )
                    nombre = ""
                    descripcion = ""
                    duracion = ""
                    precio = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Servicio")
            }

            Spacer(Modifier.height(16.dp))

            servicios.forEach { servicio ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(servicio.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: $${servicio.precio}")
                        }

                        IconButton(
                            onClick = { citaViewModel.eliminarServicio(servicio.id) }
                        ) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBa(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = {  navController.navigate("admin")  },
            icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("login") {
                popUpTo(0)
            } },
            icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Salir") },
            label = { Text("Salir") }
        )
    }
}
@Preview(showBackground = true)
@Composable
fun AdminServiciosScreenPreview() {

    val navController = rememberNavController()

    val fakeViewModel = object : CitaViewModel() {
        init {
            agregarServicio(
                Servicio(
                    id = 1,
                    nombre = "Limpieza dental",
                    descripcion = "Limpieza profunda",
                    duracion = 30,
                    precio = 500.0
                )
            )
            agregarServicio(
                Servicio(
                    id = 2,
                    nombre = "Extracción",
                    descripcion = "Extracción simple",
                    duracion = 45,
                    precio = 800.0
                )
            )
        }
    }

    MaterialTheme {
        AdminServiciosScreen(
            navController = navController,
            citaViewModel = fakeViewModel
        )
    }
}
