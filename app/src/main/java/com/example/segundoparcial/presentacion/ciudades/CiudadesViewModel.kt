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
import com.example.segundoparcial.router.Ruta
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

     private fun buscar(ciudadIngresada:String){

        uiState = CiudadesEstado.Cargando

         viewModelScope.launch{

             try {
                 val listaDeCiudades = respositorio.buscarCiudad(ciudadIngresada)
                 uiState = CiudadesEstado.Resultado(listaDeCiudades)

             }catch (exeption: Exception){
                 uiState = CiudadesEstado.Error("Error al buscar ciudad")
             }

         }

    }

    fun ejecutarIntencion(intencion: CiudadesIntencion){
       when(intencion){
           is CiudadesIntencion.Buscar -> buscar(ciudadIngresada = intencion.nombre)
           is CiudadesIntencion.Seleccionar -> seleccionar(indice = intencion.indice)
       }

   }

    private fun seleccionar(indice: Double) {
        uiState = CiudadesEstado.Vacio
        router.navegar(Ruta.Clima())
    }}

class CiudadesViewModelFactory(
    private val repositorio: Repositorio,
    private val router: Router
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CiudadesViewModel::class.java)) {
            return CiudadesViewModel(repositorio,router) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


