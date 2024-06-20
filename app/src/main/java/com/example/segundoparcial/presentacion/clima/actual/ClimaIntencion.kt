package com.example.segundoparcial.presentacion.clima.actual

sealed  class ClimaIntencion {
    object BorrarTodo: ClimaIntencion()
    object MostrarCordoba: ClimaIntencion()
    object MostrarCaba: ClimaIntencion()
    object MostrarUbicacion: ClimaIntencion()


}
