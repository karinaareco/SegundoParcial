package com.example.segundoparcial.presentacion.ciudades

import com.example.segundoparcial.repository.modelos.Ciudad

sealed class CiudadesIntencion {

    data class Buscar( val nombre:String ) : CiudadesIntencion()


    data class MostraClima (val ciudad: Ciudad): CiudadesIntencion()


}