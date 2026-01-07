package mx.ipn.upiicsa.dentiagenda.views.admin

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
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
fun admin(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFBEDDFA))
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

        Spacer(modifier = Modifier.height(40.dp))


        Button(
            onClick = { navController.navigate("admin_dentistas") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF418EDF)),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "GESTIONAR DOCTORES",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón principal


        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {navController.navigate("admin_servicios") },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF418FE0)),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = "GESTIONAR SERVICIOS",
                fontSize = 18.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.weight(1f))

        // -------- MENÚ INFERIOR --------
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate("login") {
                popUpTo(0)
            }}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Inicio")

            }


        }
    }
}



@Composable
fun BottomNavigationB(navController: NavController) {
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
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun adminPreview() {
    val navController = rememberNavController()
    MaterialTheme {
        admin(navController = navController)
    }
}

