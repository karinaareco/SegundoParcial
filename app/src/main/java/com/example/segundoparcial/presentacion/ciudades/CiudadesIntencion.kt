package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.repository.modelos.Ciudad

sealed class CiudadesIntencion {

    data class Buscar( val nombre:String ) : CiudadesIntencion()
    data class Seleccionar(val indice: Double) : CiudadesIntencion()//Hay que modif

    data class MostraClima (val ciudad: Ciudad): CiudadesIntencion()

    //data class Seleccionar(val ciudad : Ciudad) : CiudadesIntencion()//aca le paso una ciudad
}