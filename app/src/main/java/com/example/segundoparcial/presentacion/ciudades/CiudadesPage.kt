package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun MainCiudadesPage(){
    val viewModel : CiudadesViewModel = viewModel(factory = CiudadesViewModel.factory)
    CiudadesView(
        state = viewModel.uiState,
        onAction = { intencion ->
            viewModel.ejecutarIntencion(intencion)
        }
    )
}