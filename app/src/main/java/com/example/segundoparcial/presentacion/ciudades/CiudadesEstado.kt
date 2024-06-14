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
        val descripcion: String= "",
        val st :Double = 0.0,
    ) : CiudadesEstado()




}

