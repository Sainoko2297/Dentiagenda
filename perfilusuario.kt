package mx.ipn.upiicsa.dentiagenda.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PerfilUsuario(navController : NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFBEDDFA))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {

            // -------- TÍTULO SUPERIOR --------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                IconButton(
                    onClick = { navController.popBackStack()},
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(36.dp),
                        tint = Color.Black
                    )
                }

                Text(
                    text = "DentiAgenda",
                    fontSize = 34.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }


            // -------- IMAGEN DE PERFIL --------
            Image(
                Icons.Default.AccountBox,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(175.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 30.dp)
            )

            // -------- INFORMACIÓN DEL USUARIO --------
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Usuario",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "NieR",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Telefono",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "5518807077 (numero)",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Correo Electronico",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "NieR@gmail.com",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(30.dp))

            // -------- BOTÓN EDITAR --------


            Spacer(modifier = Modifier.weight(1f))

            // -------- MENÚ INFERIOR --------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = "Inicio",
                        tint = Color.Black,
                        modifier = Modifier.size(47.dp)
                    )
                }
                IconButton(onClick = { navController.navigate("CitasScreen") }) {
                    Icon(
                       Icons.Default.DateRange,
                        contentDescription = "Perfil",
                        tint = Color.Black,
                        modifier = Modifier.size(47.dp)
                    )
                }
                IconButton(onClick = {  navController.navigate("login") {
                    popUpTo(0) }
                }) {
                    Icon(
                        Icons.Default.ExitToApp,
                        contentDescription = "Ajustes",
                        tint = Color.Black,
                        modifier = Modifier.size(47.dp)
                    )
                }


                }
            }
        }
    }


@Composable
fun MenuItemPerfil(text: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .width(120.dp)
            .height(75.dp)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilUsuario() {
    val navController = rememberNavController()
    PerfilUsuario(navController = navController)
}
