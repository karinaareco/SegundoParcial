package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.presentacion.clima.ClimaEstado
import com.example.segundoparcial.repository.modelos.Ciudad

sealed class CiudadesEstado {

    /*data class Exitoso (
        val name: String = "",
        val lat: Double = 0.0,
        val lon: Double= 0.0,
        val state :String = ""
    ) : CiudadesEstado()*/
    data class Error(
        val mensaje :String = "",
    ) : CiudadesEstado()
    data object Vacio: CiudadesEstado()
    data object Cargando: CiudadesEstado()

    data class Exitoso(val ciudad: Ciudad?) : CiudadesEstado() {
        constructor(ciudades: List<Ciudad>): this(null)
    }
}

