package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class Dia(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fecha: LocalDate
    )