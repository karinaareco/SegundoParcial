package com.example.segundoparcial.presentacion.clima

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



    fun ejecutar(intencion: ClimaIntencion){
        when(intencion){
            ClimaIntencion.BorrarTodo -> borrarTodo()
            ClimaIntencion.MostrarCaba -> mostrarCaba()
            ClimaIntencion.MostrarCordoba -> mostrarCordoba()
            ClimaIntencion.MostrarUbicacion -> {

                val ciudad = "Buenos Aires"
                val imagenClima = R.drawable.icono_clima_soleado
                val descripcionClima = "Soleado"
                val detalleAdicional = "Temperatura: 25°C"

                mostrarUbicacion(ciudad, imagenClima, descripcionClima, detalleAdicional,mutableStateOf(uiState))
            }
        }
    }



    private fun mostrarUbicacion(
        ciudad: String,
        imagenClima: Int,
        descripcionClima: String,
        detalleAdicional: String,
        uiStateParam: MutableState<ClimaEstado>
    ){
        uiStateParam.value = ClimaEstado.Ubicacion("Ciudad: $ciudad, Clima: $descripcionClima, Detalle: $detalleAdicional")
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
                ClimaEstado.Exitoso(
                    ciudad = clima.name ,
                    temperatura = 10.0,//clima.main.temp,
                    descripcion = "asd",//clima.weather.first().description,
                    st = 10.2//clima.main.feelsLike,
                )
            } catch (exeption: Exception){
                ClimaEstado.Ubicacion("xxxx")
            }


        }
    }

}