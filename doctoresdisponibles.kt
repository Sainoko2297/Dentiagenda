package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.viewmodel.CitaViewModel
import com.example.app_citas_barberia.entity.Dentista

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeleccionDentistaScreen(
    navController: NavController,
    citaViewModel: CitaViewModel = viewModel()
) {

    val dentistas by citaViewModel.dentistas.collectAsState()
    val dentistaSeleccionado by citaViewModel.dentistaSeleccionado.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Selecciona un dentista") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        },
        bottomBar = {
            Column {

                // 🔵 BOTÓN CONTINUAR (solo si hay selección)

                Button(
                    onClick = {
                        dentistaSeleccionado?.let {
                            navController.navigate("reserva")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    enabled = dentistaSeleccionado != null
                ) {
                    Text("Siguiente")
                }
                // 🔽 BARRA DE ICONOS
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
                        icon = { Icon(Icons.Default.ArrowBack, null) },
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
                .padding(16.dp)
        ) {

            dentistas.forEach { dentista ->
                DentistaCard(
                    dentista = dentista,
                    selected = dentistaSeleccionado?.id == dentista.id,
                    onClick = {
                        citaViewModel.seleccionarDentista(dentista)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}



@Composable
fun DentistaCard(
    dentista: Dentista,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primaryContainer
            else
                MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${dentista.nombre} ${dentista.apellido}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dentista.especialidad ?: "Odontología general",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SeleccionDentistaPreview() {
    val navController = rememberNavController()
    SeleccionDentistaScreen(navController = navController)
}
