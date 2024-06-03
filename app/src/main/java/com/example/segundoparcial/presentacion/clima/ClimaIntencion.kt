package com.example.segundoparcial.presentacion.clima

import com.example.segundoparcial.presentacion.ciudades.CiudadesIntencion

sealed  class ClimaIntencion {
    object BorrarTodo: ClimaIntencion()
    object MostrarCordoba: ClimaIntencion()
    object MostrarCaba: ClimaIntencion()
    object MostrarUbicacion: ClimaIntencion()


}
