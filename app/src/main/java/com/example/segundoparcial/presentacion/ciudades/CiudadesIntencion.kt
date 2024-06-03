package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.presentacion.clima.ClimaIntencion

sealed class CiudadesIntencion {

    object mostrarLista: CiudadesIntencion()
    object borrarLista: CiudadesIntencion()
}