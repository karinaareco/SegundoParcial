package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.repository.modelos.ListForecast


sealed class CiudadesEstado {

    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado(val ciudades: List<Ciudad>) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion: Long = 0L,
        val st :Double = 0.0,
        val clouds: Long = 0L,
        val wind :Double =0.0,
        val icon: String= "", // un icono por defecto
    ) : CiudadesEstado()
    data class okClima (
        val climas: List<ListForecast>,
    ) : CiudadesEstado()




}

