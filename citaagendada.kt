package com.example.app_citas_barberia.Views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun ConfirmacionCita(
    navController: NavController,
    citaViewModel: CitaViewModel
) {

    val servicio by citaViewModel.servicioSeleccionado.collectAsState()
    val dentista by citaViewModel.dentistaSeleccionado.collectAsState()
    val fecha by citaViewModel.fecha.collectAsState()
    val hora by citaViewModel.hora.collectAsState()
    val isPreview = LocalInspectionMode.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC7E3FA))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Confirmación de cita", style = MaterialTheme.typography.titleLarge)

        Text("Servicio : ${servicio?.nombre}")
        Text("Dentista : ${dentista?.nombre} ${dentista?.apellido}")
        Text("Fecha: $fecha")
        Text("Hora: $hora")


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

        Button(
            onClick = {
                citaViewModel.confirmarCita()
                navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar cita")
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReservaPreview() {
    val navController = rememberNavController()
    val citaViewModel = CitaViewModel()

    MaterialTheme {
        ConfirmacionCita(
            navController = navController,
            citaViewModel = citaViewModel
        )
    }
}

