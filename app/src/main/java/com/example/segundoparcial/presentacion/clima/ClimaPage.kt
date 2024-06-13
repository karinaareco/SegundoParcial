package com.example.segundoparcial.presentacion.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.segundoparcial.repository.RepositorioApi
import com.example.segundoparcial.router.Enrutador

@Composable
fun ClimaPage(
    navHostController: NavHostController
){
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModel.ClimaViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController)
        )
    )

    ClimaView(
        state = viewModel.uiState,
        onAction = { intencion ->
            viewModel.ejecutar(intencion)
        }
    )
}







