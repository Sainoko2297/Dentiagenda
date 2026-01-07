package mx.ipn.upiicsa.dentiagenda.views.admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.entity.Dentista
import com.example.app_citas_barberia.viewmodel.CitaViewModel

@Composable
fun AdminDentistasScreen(
    navController: NavController,
    citaViewModel: CitaViewModel
) {
    val dentistas by citaViewModel.dentistas.collectAsState()

    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var especialidad by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFC7E3FA),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .background(Color(0xFFC7E3FA))
        ) {

            Text(
                text = "Administrar Dentistas",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = especialidad,
                onValueChange = { especialidad = it },
                label = { Text("Especialidad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    citaViewModel.agregarDentista(
                        Dentista(
                            id = dentistas.size + 1,
                            nombre = nombre,
                            apellido = apellido,
                            especialidad = especialidad
                        )
                    )
                    nombre = ""
                    apellido = ""
                    especialidad = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Dentista")
            }

            Spacer(Modifier.height(16.dp))

            LazyColumn {
                items(dentistas) { dentista ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text("${dentista.nombre} ${dentista.apellido}")
                                Text(
                                    text = dentista.especialidad,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            IconButton(
                                onClick = {
                                    citaViewModel.eliminarDentista(dentista.id)
                                }
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Eliminar"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
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
fun AdminDentistasScreenPreview() {

    val navController = rememberNavController()

    val fakeViewModel = object : CitaViewModel() {
        init {
            agregarDentista(
                Dentista(1, "Juan", "Pérez", "Ortodoncia")
            )
            agregarDentista(
                Dentista(2, "Ana", "Gómez", "Endodoncia")
            )
        }
    }

    MaterialTheme {
        AdminDentistasScreen(
            navController = navController,
            citaViewModel = fakeViewModel
        )
    }
}
