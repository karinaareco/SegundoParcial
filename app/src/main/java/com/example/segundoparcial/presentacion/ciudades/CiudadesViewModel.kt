package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.segundoparcial.repository.Repositorio
import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.router.Router
import kotlinx.coroutines.launch

class CiudadesViewModel(
    val respositorio: Repositorio,
    val router: Router
) : ViewModel() {
    var uiState by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacio)

    val todasLasCiudades = listOf(
        Ciudad("Cordoba", -31.4135000, -64.1810500, "Cordoba Provincia"),
        Ciudad("Cordoba", -3.4135000, -104.1810500, "España"),
        Ciudad("Cordoba", -3.4135000, -104.1810500, "Mexico"),
        Ciudad("Catamarca", -31.4135000, -64.1810500, "Catamarca"),
        Ciudad("Misiones", -31.4135000, -64.1810500, "Misiones Provincia"),
        Ciudad("Misiones", -31.4135000, -64.1810500, "Paraguay"),
        Ciudad("Buenos Aires", -31.4135000, -64.1810500, "Buenos Aires Provincia"),
    )


    /*fun buscar( ciudadIngresada: String) {
        val  ciudadesFiltradas : List<Ciudad> = todasLasCiudades.filter { ciudad ->
            ciudad.name.contains(ciudadIngresada, ignoreCase = true)
        }

        uiState = if (ciudadesFiltradas.isNotEmpty()) {
            CiudadesEstado.Resultado(ciudadesFiltradas)
        } else {
            CiudadesEstado.Vacio
        }
    }*/

    private fun buscar(ciudadIngresada: String) {

        uiState = CiudadesEstado.Cargando

        viewModelScope.launch {

            try {
                val listaDeCiudades = respositorio.buscarCiudad(ciudadIngresada)
                uiState = CiudadesEstado.Resultado(listaDeCiudades)

            } catch (exeption: Exception) {
                uiState = CiudadesEstado.Error("Error al buscar ciudad")
            }

        }

    }

    private fun mostrarPronostico(nombre: String) {
        uiState = CiudadesEstado.Cargando
        viewModelScope.launch {
            try {
                val forecast = respositorio.traerPronostico(nombre)
                uiState = CiudadesEstado.okClima(forecast)
            } catch (exception: Exception) {
                uiState = CiudadesEstado.Error(exception.localizedMessage ?: "error desconocido")
            }
        }

    }

    private fun mostraClimaDeCiudad(ciudad: Ciudad) {
        uiState = CiudadesEstado.Cargando

        viewModelScope.launch {
            //try {
                val clima2 = respositorio.traerClima(ciudad)
                uiState = CiudadesEstado.Exitoso(
                    ciudad = clima2.name,
                    temperatura = clima2.main.temp,
                    descripcion = clima2.main.humidity,
                    st = clima2.main.feelsLike,
                    wind = clima2.wind.speed,
                    clouds = clima2.clouds.all,
                    icon = clima2.weather.first().icon,
                    )
            /*} catch (exeption: Exception) {
                println(exeption)
                uiState = CiudadesEstado.Error("error 1")
            }*/
        }
    }

    fun ejecutarIntencion(intencion: CiudadesIntencion) {
        when (intencion) {
            is CiudadesIntencion.Buscar -> buscar(ciudadIngresada = intencion.nombre)

            is CiudadesIntencion.MostraClima -> mostraClimaDeCiudad(ciudad = intencion.ciudad)

            is CiudadesIntencion.MostraPronostico -> mostrarPronostico(nombre = intencion.nombre)
        }

    }


}


class CiudadesViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModel::class.java)) {
            return CiudadesViewModel(repositorio, router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


