package com.example.app_citas_barberia.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalTime

@Entity
data class Horario (
    @PrimaryKey(autoGenerate = true)
   val Id: Int,
   val hora: LocalTime
    )