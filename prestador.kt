package mx.ipn.upiicsa.dentiagenda.ui.theme.views

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PrestadorScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título superior
        Text(
            text = "DentiAgenda",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menú desplazable horizontal
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            MenuTab(text = "Datos Personales", background = Color(0xFFDFF5F8))
            MenuTab(text = "Historial de Citas", background = Color(0xFFE1F5F8))
        }

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { /* TODO: Navegar a y */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF96DBEE)),
            modifier = Modifier
                .width(240.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "CHECAR SERVICIOS PRESTADOS",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Navegar a n */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97DCEF)),
            modifier = Modifier
                .width(240.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "EDITAR PERFIL",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón principal
        Button(
            onClick = { /* TODO: Navegar a x */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF97DCEF)),
            modifier = Modifier
                .width(240.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "SERVICIOS OFRECIDOS",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* TODO: Navegar a x */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF98DDF1)),
            modifier = Modifier
                .width(240.dp)
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "CHECAR HORARIO/DISPONIBILIDAD",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        // Menú inferior con íconos
        MyBottomMenu()
    }
}

@Composable
fun MenuTab(text: String, background: Color) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .background(background, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MyBottomMenu() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(R.drawable.ic_menu_slideshow),
                contentDescription = "Inicio",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(R.drawable.ic_menu_search),
                contentDescription = "Perfil",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(R.drawable.sym_action_chat),
                contentDescription = "Ajustes",
                tint = Color.Black
            )
        }
        IconButton(onClick = { /* TODO */ }) {
            Icon(
                painter = painterResource(R.drawable.stat_sys_phone_call),
                contentDescription = "Información",
                tint = Color.Black
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrestadorScreenPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        PrestadorScreen(navController = navController)
    }
}

