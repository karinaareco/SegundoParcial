package com.example.segundoparcial.repository

import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.repository.modelos.Clima2
import com.example.segundoparcial.repository.modelos.ListForecast

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun traerClima(ciudad: Ciudad) : Clima2
    suspend fun traerPronostico(nombre: String) : List<ListForecast>
}