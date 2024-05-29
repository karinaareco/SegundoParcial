package com.example.segundoparcial.repository

import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.repository.modelos.Clima2

interface Repositorio {
    suspend fun buscarCiudad(ciudad: String): List<Ciudad>
    suspend fun traerClima(ciudad: Ciudad) : Clima2
    suspend fun traerPronostico(ciudad: Ciudad) : List<Clima2>
}