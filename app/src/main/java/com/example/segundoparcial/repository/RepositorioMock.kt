package com.example.segundoparcial.repository

import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.repository.modelos.Clima
import com.example.segundoparcial.repository.modelos.Clima2
import com.example.segundoparcial.repository.modelos.ListForecast

class RepositorioMock  : Repositorio {

        val cordoba = Ciudad(name = "Cordoba",
            lat = -23.0,
            lon = -24.3,
            state = "Argentina")
        val bsAs =Ciudad(name = "Buenos Aires",
            lat = -23.0,
            lon = -24.3,
            state = "Argentina")
        val laPlata =Ciudad(name = "La Plata",
            lat = -23.0,
            lon = -24.3,
            state = "Argentina")
        val ciudades = listOf(cordoba,bsAs,laPlata)

    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {
        if (ciudad == "error"){
            throw Exception()
        }
        return ciudades.filter {it.name.contains(ciudad,ignoreCase = true) }
    }

    override suspend fun traerClima(ciudad: Ciudad): Clima2 {
        TODO("Not yet implemented")
    }

    suspend fun traerClima(lat: Float, lon: Float): Clima {
        TODO("Not yet implemented")
    }
    override suspend fun traerPronostico(nombre: String): List<ListForecast> {
        TODO("Not yet implemented")
    }
}
class RepositorioMockError  : Repositorio {

    override suspend fun buscarCiudad(ciudad: String): List<Ciudad> {
        throw Exception()
    }

    override suspend fun traerClima(ciudad: Ciudad): Clima2 {
        TODO("Not yet implemented")
    }

    suspend fun traerClima(lat: Float, lon: Float): Clima {
        throw Exception()
    }

    override suspend fun traerPronostico(nombre: String): List<ListForecast> {
        throw Exception()
    }
}


