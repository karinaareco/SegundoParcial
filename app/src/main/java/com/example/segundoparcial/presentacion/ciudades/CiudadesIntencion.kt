package com.example.segundoparcial.presentacion.ciudades

sealed class CiudadesIntencion {

    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val indice: Double) : CiudadesIntencion()//Hay que modif
}