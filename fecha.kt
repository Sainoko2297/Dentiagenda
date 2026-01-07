package mx.ipn.upiicsa.dentiagenda.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.viewmodel.CitaViewModel
import mx.ipn.upiicsa.dentiagenda.R
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservaScreen(
    navController: NavController,
    citaViewModel: CitaViewModel
) {

    val datePickerState = rememberDatePickerState()
    val Preview = LocalInspectionMode.current
    var showDatePicker by remember{ mutableStateOf(false)}
    var horaSeleccionada by remember { mutableStateOf<String?>(null) }

    val horas = listOf(
        "10:00 AM", "11:00 AM", "12:00 PM",
        "3:00 PM", "4:00 PM", "5:00 PM"
    )

    val habilitarSiguiente =
        datePickerState.selectedDateMillis != null &&
                horaSeleccionada != null
Scaffold(
    containerColor = Color(0xFFC7E3FA),
bottomBar={
    Column{
        Button(
        onClick = {
            val fechaMillis = datePickerState.selectedDateMillis ?: return@Button
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            var fechaFormateada = formatter.format(Date(fechaMillis))

            citaViewModel.seleccionarHorario(
                fecha = fechaFormateada,
                hora = horaSeleccionada!!
            )
            Log.d(
                "CITA",
                "servicio=${citaViewModel.servicioSeleccionado.value}, " +
                        "dentista=${citaViewModel.dentistaSeleccionado.value}, " +
                        "fecha=${citaViewModel.fecha.value}, " +
                        "hora=${citaViewModel.hora.value}"
            )

            if(citaViewModel.citaCompleta()){
                navController.navigate("confirmacion")
            }
        },
        enabled = habilitarSiguiente,
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .height(48.dp)

    ) {
        Text("Siguiente")
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
    } }
    ) {padding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Selecciona tu cita",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

       Button(
           onClick = { showDatePicker = true},
           modifier = Modifier
               .fillMaxWidth()
               .height(48.dp)
       ) {
           Text(
               text =
                   if (datePickerState.selectedDateMillis == null)
               "seleciona la fecha"
               else{
                   val formatter=
                       SimpleDateFormat("dd/MM/yyyy",Locale.getDefault())
                       formatter.format(Date(datePickerState.selectedDateMillis!!))
               }
           )
       }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Horas disponibles",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        // PRIMERA FILA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            horas.take(3).forEach { hora ->
                BotonHora(
                    hora = hora,
                    seleccionada = horaSeleccionada == hora
                ) {
                    horaSeleccionada = hora
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // SEGUNDA FILA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            horas.takeLast(3).forEach { hora ->
                BotonHora(
                    hora = hora,
                    seleccionada = horaSeleccionada == hora
                ) {
                    horaSeleccionada = hora
                }
            }
        }
        Spacer(modifier = Modifier.height(60.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            // 🖼️ Imagen
            if (Preview) {
                Box(
                    modifier = Modifier
                        .size(180.dp)
                        .background(Color.White, RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen calendario", color = Color.Gray)
                }
            } else {
                Image(
                    painter = painterResource(id = R.drawable.fecha),
                    contentDescription = "Imagen diente",
                    modifier = Modifier.size(180.dp)
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(40.dp))

    // 🖼️ Imagen

}

    if (showDatePicker){
        DatePickerDialog (
            onDismissRequest = {showDatePicker = false},
            confirmButton = {
                TextButton(
                    onClick = {showDatePicker = false}) {
                    Text("Aceptar")
                }
            },
            dismissButton ={
                TextButton(onClick = {showDatePicker = false}) {
                    Text("Cancelar")
                }
            }

        ){
            DatePicker(state = datePickerState)
        }
    }

}


@Composable
fun BotonHora(
    hora: String,
    seleccionada: Boolean,
    onClick: () -> Unit
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (seleccionada)
                MaterialTheme.colorScheme.primary
            else
                MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier.width(90.dp)
    ) {
        Text(
            text = hora,
            color = if (seleccionada)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onSecondaryContainer
        )
    }

}
@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReservaPreview() {
    val navController = rememberNavController()
    val citaViewModel = CitaViewModel()

    MaterialTheme {
        ReservaScreen(
            navController = navController,
            citaViewModel = citaViewModel
        )
    }
}
