package com.example.segundoparcial.presentacion.clima

sealed class ClimaEstado {
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion: String= "",
        val st :Double = 0.0,
    ) : ClimaEstado()
    data class Ubicacion(
        val ciudad: String = "",
        val imagenClima: Int = 0,
        val descripcionClima: String = "",
        val detalleAdicional: String = ""
    ) : ClimaEstado()
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
}