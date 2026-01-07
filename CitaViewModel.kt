package com.example.app_citas_barberia.viewmodel

import androidx.lifecycle.ViewModel
import com.example.app_citas_barberia.entity.Dentista
import com.example.app_citas_barberia.entity.CitaUI
import com.example.app_citas_barberia.entity.Servicio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class CitaViewModel : ViewModel() {

    /* ---------- FUENTE ÚNICA DE DATOS ---------- */

    private val _servicios = MutableStateFlow<List<Servicio>>(emptyList())
    val servicios: StateFlow<List<Servicio>> = _servicios.asStateFlow()

    private val _dentistas = MutableStateFlow<List<Dentista>>(emptyList())
    val dentistas: StateFlow<List<Dentista>> = _dentistas.asStateFlow()

    /* ---------- SELECCIONES ---------- */



    private val _servicioSeleccionado = MutableStateFlow<Servicio?>(null)
    val servicioSeleccionado: StateFlow<Servicio?> = _servicioSeleccionado.asStateFlow()

    private val _dentistaSeleccionado = MutableStateFlow<Dentista?>(null)
    val dentistaSeleccionado: StateFlow<Dentista?> = _dentistaSeleccionado.asStateFlow()

    private val _fecha = MutableStateFlow<String?>(null)
    val fecha: StateFlow<String?> = _fecha.asStateFlow()

    private val _hora = MutableStateFlow<String?>(null)
    val hora: StateFlow<String?> = _hora.asStateFlow()

    /* ---------- HISTORIAL ---------- */

    private val _citasUI = MutableStateFlow<List<CitaUI>>(emptyList())
    val citasUI: StateFlow<List<CitaUI>> = _citasUI.asStateFlow()

    /* ---------- INIT ---------- */

    init {
        cargarDatosIniciales()
    }

    private fun cargarDatosIniciales() {
        _servicios.value = listOf(
            Servicio(1, "Limpieza dental", "Limpieza profunda", 30, 500.0),
            Servicio(2, "Extracción", "Extracción simple", 45, 800.0),
            Servicio(3, "Blanqueamiento", "Blanqueamiento estético", 60, 1500.0),
            Servicio(4, "Ortodoncia", "Revisión ortodoncia", 40, 600.0)
        )

        _dentistas.value = listOf(
            Dentista(1, "Ana", "Gómez", "Odontología general", true),
            Dentista(2, "Luis", "Ramírez", "Ortodoncia", true),
            Dentista(3, "María", "Hernández", "Cirugía dental", true)
        )
    }

    fun agregarDentista(dentista: Dentista) {
        _dentistas.value = _dentistas.value + dentista
    }

    fun editarDentista(dentistaEditado: Dentista) {
        _dentistas.value = _dentistas.value.map {
            if (it.id == dentistaEditado.id) dentistaEditado else it
        }
    }
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

    fun eliminarDentista(id: Int) {
        _dentistas.value = _dentistas.value.filterNot { it.id == id }
    }

    /* ---------- MÉTODOS DE SELECCIÓN ---------- */


    fun seleccionarServicio(servicio: Servicio) {
        _servicioSeleccionado.value = servicio
        _dentistaSeleccionado.value = null
    }

    fun seleccionarDentista(dentista: Dentista) {
        _dentistaSeleccionado.value = dentista
    }

    fun seleccionarHorario(fecha: String, hora: String) {
        _fecha.value = fecha
        _hora.value = hora
    }

    /* ---------- CONFIRMAR CITA ---------- */

    fun confirmarCita() {
        if (!citaCompleta()) return

        val cita = CitaUI(
            servicio = _servicioSeleccionado.value!!.nombre,
            dentista = "${_dentistaSeleccionado.value!!.nombre} ${_dentistaSeleccionado.value!!.apellido}",
            fecha = _fecha.value!!,
            hora = _hora.value!!
        )

        _citasUI.value = _citasUI.value + cita
        limpiarCita()
    }

    fun citaCompleta(): Boolean =
                _servicioSeleccionado.value != null &&
                _dentistaSeleccionado.value != null &&
                _fecha.value != null &&
                _hora.value != null

    /* ---------- LIMPIAR ---------- */

    fun limpiarCita() {
        _servicioSeleccionado.value = null
        _dentistaSeleccionado.value = null
        _fecha.value = null
        _hora.value = null
    }
}
