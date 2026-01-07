package mx.ipn.upiicsa.dentiagenda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.app_citas_barberia.Views.ConfirmacionCita
import com.example.app_citas_barberia.viewmodel.CitaViewModel
import mx.ipn.upiicsa.dentiagenda.views.CrearCuentaScreen
import mx.ipn.upiicsa.dentiagenda.views.DentiAgendaLoginScreen
import mx.ipn.upiicsa.dentiagenda.views.PaginaInicio
import mx.ipn.upiicsa.dentiagenda.ui.theme.DentiagendaTheme
import mx.ipn.upiicsa.dentiagenda.ui.theme.views.PrestadorScreen
import mx.ipn.upiicsa.dentiagenda.views.admin.AdminDentistasScreen
import mx.ipn.upiicsa.dentiagenda.views.admin.AdminServiciosScreen
import mx.ipn.upiicsa.dentiagenda.views.CitasScreen
import mx.ipn.upiicsa.dentiagenda.views.PerfilUsuario
import mx.ipn.upiicsa.dentiagenda.views.ReservaScreen
import mx.ipn.upiicsa.dentiagenda.views.SeleccionDentistaScreen
import mx.ipn.upiicsa.dentiagenda.views.ServiciosScreen
import mx.ipn.upiicsa.dentiagenda.views.admin.admin


class   MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DentiagendaTheme(){
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    val citaViewModel: CitaViewModel = viewModel()


                    // Controlador de navegación principal
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {

                        composable("login") {
                            DentiAgendaLoginScreen(navController)
                        }

                        composable("registro") {
                            CrearCuentaScreen(navController)
                        }

                        composable("home") {
                            PaginaInicio(navController)
                        }

                        composable("servicios") {
                            ServiciosScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }
                        composable("PerfilUsuario") {
                            PerfilUsuario(
                                navController = navController,

                                )
                        }

                        composable("dentistas") {
                            SeleccionDentistaScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }

                        composable("reserva") {
                            ReservaScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }

                        composable("confirmacion") {
                            ConfirmacionCita(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }

                        composable("CitasScreen") {
                            CitasScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }

                        composable("perfil") {
                            PerfilUsuario(navController)
                        }

                        composable("prestador") {
                            PrestadorScreen(navController)
                        }

                        /* ---------- ADMIN ---------- */
                        composable("admin") {
                            admin(navController)
                        }
                        composable("admin_dentistas") {
                            AdminDentistasScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }

                        composable("admin_servicios") {
                            AdminServiciosScreen(
                                navController = navController,
                                citaViewModel = citaViewModel
                            )
                        }
                    }

                }
            }
        }
    }
}