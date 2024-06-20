package com.example.segundoparcial.presentacion.clima.actual

sealed class ClimaEstado {
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion:Long = 0L,
        val st :Double = 0.0,
        val clouds: Long = 0L,
        val wind :Double =0.0,
        val icon: String= "", // un icono por defecto
        ) : ClimaEstado()
    data class Error(
        val mensaje :String = "",
    ) : ClimaEstado()
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
}