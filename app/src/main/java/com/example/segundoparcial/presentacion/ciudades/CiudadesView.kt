package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import com.example.segundoparcial.repository.modelos.Ciudad

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CiudadesView(
    modifier: Modifier = Modifier,
    state: CiudadesEstado,
    onAction: (CiudadesIntencion) -> Unit
) {
    Scaffold(containerColor = Color(0xFF004D34)) {
        Column(modifier = Modifier.padding(it)) {
            var ciudadIngresada by remember { mutableStateOf("") }
            OutlinedTextField(
                value = ciudadIngresada, //searchState.name,
                onValueChange = {
                    ciudadIngresada = it
                    onAction(CiudadesIntencion.Buscar(ciudadIngresada))
                },

                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Unspecified,
                    unfocusedContainerColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified,
                ),
                label = {
                    Text(
                        text = "Buscar ubicacion",
                        color = Color.Black
                    )
                },
            )

            when (state) {

                is CiudadesEstado.Cargando -> Text(text = "cargando")
                is CiudadesEstado.Error -> Text(text = state.mensaje)
                is CiudadesEstado.Resultado ->  ListaDeCiudades(state.ciudades) {
                    onAction(
                        CiudadesIntencion.Seleccionar(it)
                    )
                }

                is CiudadesEstado.Vacio -> Text(text = "No hay resultados")
            }

            Button(
                onClick = { onAction(CiudadesIntencion.Seleccionar(0)) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Borrar lista",
                    color = Color.White
                )

            }
            Button(
                onClick = { onAction(CiudadesIntencion.Buscar("")) },

                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Buscar Ciudad",
                    color = Color.White
                )
            }
        }
    }
}






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeCiudades(ciudades: List<Ciudad>, onSelect: (Int)->Unit){

    LazyColumn {
        items(items = ciudades){
            Card (onClick = { onSelect(0) }){
                Text(text = it.name)
            }
        }
    }

}

