package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Genero",
indices = [androidx.room.Index(value = ["descripcion"], unique = true)]
)
data class Genero (

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    var descripcion: String
    )

