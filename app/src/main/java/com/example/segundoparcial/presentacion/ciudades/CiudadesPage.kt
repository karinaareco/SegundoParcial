package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.segundoparcial.repository.RepositorioApi
import com.example.segundoparcial.repository.modelos.Ciudad
import com.example.segundoparcial.router.Enrutador


@Composable
fun CiudadesPage(
    navHostController: NavHostController
){

   val viewModel: CiudadesViewModel = viewModel(
       factory = CiudadesViewModelFactory(
           repositorio = RepositorioApi(),
           router = Enrutador(navHostController)
       )
   )
    CiudadesView(
        state = viewModel.uiState,
        onAction = { intencion ->
            viewModel.ejecutarIntencion(intencion)
        }
    )
}

