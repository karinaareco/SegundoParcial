package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.repository.modelos.Ciudad


sealed class CiudadesEstado {

    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()
    data class Resultado(val ciudades: Array<Ciudad>) : CiudadesEstado()
    data class Error(val mensaje: String): CiudadesEstado()
    data class Exitoso (
        val ciudad: String = "",
        val temperatura: Double = 0.0,
        val descripcion: Long = 0L,
        val st :Double = 0.0,
        val clouds: Long = 0L,// se lo agregue para ver si funcionan las nubes
        val wind :Double =0.0,// se lo agregue para ver si funcionan la velocidad del viento

    ) : CiudadesEstado()




}

