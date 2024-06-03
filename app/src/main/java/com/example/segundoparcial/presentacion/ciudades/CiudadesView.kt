package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segundoparcial.presentacion.clima.ClimaEstado
import com.example.segundoparcial.presentacion.clima.ClimaView
import com.example.segundoparcial.repository.modelos.Ciudad

import com.example.segundoparcial.ui.theme.SegundoParcialTheme
import com.google.firebase.crashlytics.buildtools.reloc.javax.annotation.meta.When

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CiudadesView(
    modifier: Modifier = Modifier,
    state: CiudadesEstado,
    ciudades: List<Ciudad>,
    onAction: (CiudadesIntencion) -> Unit
) {


    Column(modifier = Modifier.padding(30.dp)) {
        var ciudadIngresada by remember { mutableStateOf("") }

        TextField(value = ciudadIngresada, onValueChange = { ciudadIngresada = it })


        when (state) {
            CiudadesEstado.Cargando -> TODO()
            is CiudadesEstado.Error -> ErrorView(mensaje = state.mensaje)
            is CiudadesEstado.Exitoso -> {
            LazyColumn {
                items(items = ciudades){
                    Text(text = it.name)
                }
            }

            }

            CiudadesEstado.Vacio -> EmptyView()
        }

        Button(onClick = { onAction(CiudadesIntencion.borrarLista) }) {
            Text(text = "Borrar lista")

        }
        Button(onClick = { onAction(CiudadesIntencion.mostrarLista) }) {
            Text(text = "Buscar Ciudad")
        }


    }


}



@Composable
fun EmptyView() {
    Text(text = "")
}

@Composable
fun ErrorView(mensaje: String) {
    Text(text = mensaje)
}

@Composable
fun ciudadesView(nombre: String, lat: Double, descripcion: String, lon: Double) {
    Column {
        Text(text = "${nombre}", style = MaterialTheme.typography.titleMedium)
        Text(text = "${lat}°", style = MaterialTheme.typography.titleLarge)
        Text(text = "${descripcion}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "long: ${lon}°", style = MaterialTheme.typography.bodyMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun CiudadesPreviewVacio() {
    val ciudades = remember { mutableStateListOf<Ciudad>()}
    SegundoParcialTheme {
        CiudadesView(state = CiudadesEstado.Vacio, onAction = {},ciudades=ciudades)
    }
}

@Preview(showBackground = true)
@Composable
fun CiudadesPreviewExitoso() {
    val ciudades = remember { mutableStateListOf<Ciudad>()}
    SegundoParcialTheme {

        CiudadesView(
            state = CiudadesEstado.Exitoso(
                Ciudad("Bs As", -1254.0, -18756.0, "Ar")
            ),
            onAction = {},ciudades=ciudades)
    }
}
