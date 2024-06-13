package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.presentacion.clima.ClimaIntencion

sealed class CiudadesIntencion {

    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val indice: Int) : CiudadesIntencion()//Hay que modif
}