package mx.ipn.upiicsa.dentiagenda.model

import androidx.lifecycle.ViewModel
import com.example.app_citas_barberia.entity.Dentista
import com.example.app_citas_barberia.entity.Servicio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AdminViewModel : ViewModel() {

    // ========================
    // SERVICIOS
    // ========================
    private val _servicios = MutableStateFlow(
        listOf(
            Servicio(1, "Corte de cabello", "Corte clásico", 30, 150.0),
            Servicio(2, "Afeitado", "Afeitado profesional", 20, 100.0),
            Servicio(3, "Tinte", "Aplicación de tinte", 60, 300.0)
        )
    )
    val servicios: StateFlow<List<Servicio>> = _servicios

    fun agregarServicio(servicio: Servicio) {
        _servicios.value = _servicios.value + servicio
    }

    fun editarServicio(servicioEditado: Servicio) {
        _servicios.value = _servicios.value.map {
            if (it.id == servicioEditado.id) servicioEditado else it
        }
    }

    fun eliminarServicio(id: Int) {
        _servicios.value = _servicios.value.filterNot { it.id == id }
    }

    // ========================
    // BARBEROS
    // ========================
    private val _barberos = MutableStateFlow(
        listOf(
            Dentista(1, "Alan", "Martínez", "Cortes clásicos y modernos", true),
            Dentista(2, "Juan", "Pérez", "Afeitado profesional y delineado", true),
            Dentista(3, "Carlos", "Hernández", "Cortes juveniles y fades",  true)
        )
    )
    val barberos: StateFlow<List<Dentista>> = _barberos

    fun agregarBarbero(barbero: Dentista) {
        _barberos.value = _barberos.value + barbero
    }

    fun editarBarbero(barberoEditado: Dentista) {
        _barberos.value = _barberos.value.map {
            if (it.id == barberoEditado.id) barberoEditado else it
        }
    }

    fun eliminarBarbero(id: Int) {
        _barberos.value = _barberos.value.filterNot { it.id == id }
    }
}

