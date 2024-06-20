package com.example.segundoparcial.presentacion.clima

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.segundoparcial.presentacion.clima.actual.ClimaView
import com.example.segundoparcial.presentacion.clima.actual.ClimaViewModel
import com.example.segundoparcial.presentacion.clima.pronostico.PronosticoView
import com.example.segundoparcial.presentacion.clima.pronostico.PronosticoViewModel
import com.example.segundoparcial.presentacion.clima.pronostico.PronosticoViewModelFactory
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
    val pronosticoViewModel : PronosticoViewModel = viewModel(
        factory = PronosticoViewModelFactory(
            repositorio = RepositorioApi(),
            router = Enrutador(navHostController),
            nombre = "nombre"//lo dejo asi para ver si me corre despues corregir
        )
    )

    Column {
        ClimaView(
            state = viewModel.uiState,
            onAction = { intencion ->
                viewModel.ejecutar(intencion)
            }
        )
        PronosticoView(
            state = pronosticoViewModel.uiState,
            onAction = { intencion ->
                pronosticoViewModel.ejecutar(intencion)
            }
        )

    }

}







