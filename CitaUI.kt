package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Persona::class,
            parentColumns = ["id"],
            childColumns = ["personaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Dentista::class,
            parentColumns = ["id"],
            childColumns = ["dentistaId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Dia::class,
            parentColumns = ["id"],
            childColumns = ["diaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Horario::class,
            parentColumns = ["id"],
            childColumns = ["horarioId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Servicio::class,
            parentColumns = ["id"],
            childColumns = ["servicioId"],
            onDelete = ForeignKey.CASCADE
        ),

    ]
)
data class CitaUI(
    val servicio: String,
    val dentista: String,
    val fecha: String,
    val hora: String
)
