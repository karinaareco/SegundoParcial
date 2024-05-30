package com.example.segundoparcial.presentacion.clima

sealed  class ClimaIntencion {
    object BorrarTodo: ClimaIntencion()
    object MostrarCordoba: ClimaIntencion()
    object MostrarCaba: ClimaIntencion()
    object MostrarUbicacion: ClimaIntencion()
}
