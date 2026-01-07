package com.example.app_citas_barberia.entity



import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "Servicio")

data class Servicio(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val descripcion: String? = null,
    val duracion: Int? = null,
    val precio: Double? = null,
)



