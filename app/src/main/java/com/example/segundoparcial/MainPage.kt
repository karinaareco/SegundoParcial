package com.example.segundoparcial

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.segundoparcial.presentacion.ciudades.CiudadesPage
import com.example.segundoparcial.presentacion.clima.ClimaPage
import com.example.segundoparcial.router.Ruta

@Composable
fun MainPage(){
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = Ruta.Ciudades.id){
        composable(
            route = Ruta.Ciudades.id
        ){
            CiudadesPage(navHostController)
        }

        composable(
            route= Ruta.Clima().id
        ){
            ClimaPage(navHostController)
        }

    }


}