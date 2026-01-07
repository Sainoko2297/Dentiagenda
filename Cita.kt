package mx.ipn.upiicsa.dentiagenda.model

data class Cita(
    val id: Int,
    val paciente: String,
    val fecha: String,
    val hora: String,
    val motivo: String
)
