package com.example.app_citas_barberia.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.app_citas_barberia.entity.Rol


@Entity(
    tableName = "Usuario",
    foreignKeys = [
        ForeignKey(
            entity = Persona::class,
            parentColumns = ["id"],
            childColumns = ["idPersonaFK"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUsuario")
    val idUsuario: Int = 0,
    @ColumnInfo(name = "username")
    val username :String = "",
    @ColumnInfo(name = "password")
    val password :String = "",
    @ColumnInfo(name ="rol")
    val rol: Rol,
    @ColumnInfo(name = "idPersonaFK", index = true)
    val idPersonaFK: Int
)
