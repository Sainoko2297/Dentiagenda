package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Dentista")

data class Dentista(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val nombre: String,
    val apellido: String,
    val especialidad:String,
    val activo: Boolean = true
    )
