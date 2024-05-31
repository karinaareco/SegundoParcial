package com.example.segundoparcial.presentacion.clima

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.segundoparcial.R
import com.example.segundoparcial.repository.Repositorio
import com.example.segundoparcial.repository.RepositorioApi
import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.repository.modelos.Clima
import kotlinx.coroutines.launch

class ClimaViewModel(
    val respositorio: Repositorio
) : ViewModel() {




    companion object {
        val factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repositorio = RepositorioApi()
                ClimaViewModel(repositorio)
            }
        }
    }

    var uiState by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    private val climaBuenosAires = Clima(
        temperatura = 20,
        humedad = 8.0F,
        ciudad = "Buenos Aires",
        st = 12,
        viento = 90,
        latitud = 151515151,
        longitud = 252525252,
        estado = "Nublado",


    )

    fun ejecutar(intencion: ClimaIntencion){
        when(intencion){
            ClimaIntencion.BorrarTodo -> borrarTodo()
            ClimaIntencion.MostrarCaba -> mostrarCaba()
            ClimaIntencion.MostrarCordoba -> mostrarCordoba()
            ClimaIntencion.MostrarUbicacion -> mostrarUbicacion()
        }
    }



    private fun mostrarUbicacion(){
        uiState = ClimaEstado.Exitoso(
        ciudad = climaBuenosAires.ciudad,
            temperatura = climaBuenosAires.temperatura.toDouble(),
            st = climaBuenosAires.st.toDouble()

        )

    }

    private fun borrarTodo(){
        uiState = ClimaEstado.Vacio
    }

    private fun mostrarCaba(){

    }

    private fun mostrarCordoba(){
        uiState =ClimaEstado.Cargando
        viewModelScope.launch {
            val cordoba = Ciudad(name = "Cordoba", lat = -31.4135, lon = -64.18105, state = "Ar")
            try{
                val clima = respositorio.traerClima(cordoba)
                uiState = ClimaEstado.Exitoso(
                    ciudad = clima.name ,
                    temperatura = 10.0,//clima.main.temp,
                    descripcion = "asd",//clima.weather.first().description,
                    st = 10.2//clima.main.feelsLike,
                )
            } catch (exeption: Exception){
                uiState = ClimaEstado.Error("error")
            }


        }
    }



}