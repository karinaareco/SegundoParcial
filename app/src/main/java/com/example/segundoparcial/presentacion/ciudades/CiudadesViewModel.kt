package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.segundoparcial.presentacion.clima.ClimaViewModel
import com.example.segundoparcial.repository.Repositorio
import com.example.segundoparcial.repository.RepositorioApi
import com.example.segundoparcial.repository.modelos.Ciudad

class CiudadesViewModel ( val respositorio: Repositorio): ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorio = RepositorioApi()
                CiudadesViewModel(repositorio)
            }
        }
    }

    var uiState by mutableStateOf<CiudadesEstado>(CiudadesEstado.Vacio)

    var ciudades: List<Ciudad> = listOf(

        Ciudad("Cordoba", -31.4135000, -64.1810500, "Cordoba Provincia"),
        Ciudad("Cordoba", -3.4135000, -104.1810500, "España"),
        Ciudad("Cordoba", -3.4135000, -104.1810500, "Mexico"),
        Ciudad("Catamarca", -31.4135000, -64.1810500, "Catamarca"),
        Ciudad("Misiones", -31.4135000, -64.1810500, "Misiones Provincia"),
        Ciudad("Misiones", -31.4135000, -64.1810500, "Paraguay"),
        Ciudad("Buenos Aires", -31.4135000, -64.1810500, "Buenos Aires Provincia"),
    )


    fun ejecutarIntencion(intencion: CiudadesIntencion,ciudadIngresada: String = "") {
        when (intencion) {
            CiudadesIntencion.borrarLista ->borrarLista()
            CiudadesIntencion.mostrarLista -> mostrarLista(ciudadIngresada)
        }
    }

    private fun borrarLista(){
        uiState = CiudadesEstado.Vacio
    }


    private fun mostrarLista(ciudadIngresada:String) {
        val ciudadesFiltradas = ciudades.filter { ciudad ->
            ciudad.name.contains(ciudadIngresada, ignoreCase = true)
        }
        uiState = if(ciudadesFiltradas.isNotEmpty()){


             CiudadesEstado.Exitoso(ciudadesFiltradas)
        }
        else{
             CiudadesEstado.Vacio
        }


    }
}






