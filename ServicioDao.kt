package com.example.app_citas_barberia.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.app_citas_barberia.entity.Servicio
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicioDao {

    @Query("SELECT * FROM Servicio WHERE activo = 1")
    fun getServicios(): Flow<List<Servicio>>

    @Query("SELECT * FROM Servicio WHERE id = :id")
    suspend fun getServicioById(id: Int): Servicio?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServicio(servicio: Servicio)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertServicios(servicios: List<Servicio>)
}
