package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.app_citas_barberia.viewmodel.CitaViewModel
import mx.ipn.upiicsa.dentiagenda.R

@Composable
fun CitasScreen(navController: NavController,
                citaViewModel: CitaViewModel) {

    var selectedDoctor1 by remember { mutableStateOf("") }
    var selectedDoctor2 by remember { mutableStateOf("") }
    var selectedDoctor3 by remember { mutableStateOf("") }
    var selectedDoctor4 by remember { mutableStateOf("") }

    val doctorList = listOf("Dr. Pérez", "Dra. López", "Dr. Hernández", "Dra. Díaz")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        // ---------- TÍTULO SUPERIOR ----------
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "DentiAgenda",
                fontSize = 34.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { /* TODO: Acción imagen */ }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "Logo",
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))


        Spacer(modifier = Modifier.height(24.dp))

        // ---------- TEXTO DESCRIPTIVO ----------
        Text(
            text = "En esta sección puedes checar tus próximas citas como las anteriores",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- SPINNER 1 ----------
        SpinnerCard(
            label = "Doctores Seleccionados",
            options = doctorList,
            selectedValue = selectedDoctor1,
            onValueChange = { selectedDoctor1 = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- SPINNER 2 ----------
        SpinnerCard(
            label = "Selecciona un doctor",
            options = doctorList,
            selectedValue = selectedDoctor2,
            onValueChange = { selectedDoctor2 = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- SPINNER 3 ----------
        SpinnerCard(
            label = "Selecciona un doctor",
            options = doctorList,
            selectedValue = selectedDoctor3,
            onValueChange = { selectedDoctor3 = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Una vez escogido el doctor, en el recuadro puedes ver su teléfono",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ---------- SPINNER 4 ----------
        SpinnerCard(
            label = "Selecciona un doctor",
            options = doctorList,
            selectedValue = selectedDoctor4,
            onValueChange = { selectedDoctor4 = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- BOTÓN TELÉFONO ----------
        Button(
            onClick = { /* TODO: Mostrar teléfono */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D6D6D)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Teléfono del Doctor", fontSize = 20.sp, color = Color.White)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ---------- MENÚ INFERIOR ----------
        BottomMenuBar()
    }
}

@Composable
fun MenuButton(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Text(
            text = text.uppercase(),
            color = Color.Black,
            fontSize = 16.sp,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            modifier = Modifier.width(120.dp)
        )
    }
}

@Composable
fun SpinnerCard(
    label: String,
    options: List<String>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        Column {
            Text(
                text = label,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.arrow_down_float),
                            contentDescription = "Abrir menú"
                        )
                    }
                }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                options.forEach { doctor ->
                    DropdownMenuItem(
                        text = { Text(doctor) },
                        onClick = {
                            onValueChange(doctor)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomMenuBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_slideshow),
                contentDescription = "Inicio",
                tint = Color.Black
            )
        }

        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.ic_menu_search),
                contentDescription = "Perfil",
                tint = Color.Black
            )
        }

        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.sym_action_chat),
                contentDescription = "Ajustes",
                tint = Color.Black
            )
        }

        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(id = android.R.drawable.stat_sys_phone_call),
                contentDescription = "Información",
                tint = Color.Black
            )
        }
    }
}