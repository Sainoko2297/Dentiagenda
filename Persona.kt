package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName="Persona",
    foreignKeys =[
    ForeignKey(
        entity = Genero::class,
        parentColumns = ["id"],
        childColumns = ["generoId"],
        onDelete = ForeignKey.SET_NULL
    )
    ]
,
indices=[
    androidx.room.Index(value=["generoId"])
]
)
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,

    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,

    val generoid: Int? = null,

    val fechaNacimiento: String,
    val telefono: String,
    val email: String

    )