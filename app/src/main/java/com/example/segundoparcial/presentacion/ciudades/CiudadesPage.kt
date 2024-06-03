package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.segundoparcial.repository.modelos.Ciudad


@Composable
fun MainCiudadesPage(){
    val ciudades = remember { mutableStateListOf<Ciudad>() }
    val viewModel : CiudadesViewModel = viewModel(factory = CiudadesViewModel.factory)
    CiudadesView(

        state = viewModel.uiState,
        ciudades = ciudades,
        onAction = { intencion ->
            viewModel.ejecutarIntencion(intencion)
        }
    )
}