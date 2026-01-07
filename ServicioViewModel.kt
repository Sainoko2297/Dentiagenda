package com.example.app_citas_barberia.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_citas_barberia.dao.ServicioDao
import com.example.app_citas_barberia.entity.Servicio
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ServicioViewModel(
    private val servicioDao: ServicioDao
) : ViewModel() {

    val servicios: StateFlow<List<Servicio>> =
        servicioDao.getServicios()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                emptyList()
            )
}
