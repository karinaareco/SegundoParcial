package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

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
    ciudades :List<Ciudad>,
    onAction: (CiudadesIntencion) -> Unit
) {
 Scaffold(containerColor = Color(0xFF004D34)) {
     Column(modifier = Modifier.padding(it)) {
         var ciudadIngresada by remember { mutableStateOf("") }

         OutlinedTextField(value = ciudadIngresada,
             onValueChange = { ciudadIngresada = it },
             modifier = modifier
                 .fillMaxWidth(),
             colors = TextFieldDefaults.textFieldColors(containerColor = Color.Unspecified),
             label = { Text(text = "Buscar ubicacion",
                 color = Color.Black)},

             )


         when (state) {
             CiudadesEstado.Cargando -> TODO()
             is CiudadesEstado.Error -> ErrorView(mensaje = state.mensaje)
             is CiudadesEstado.Exitoso -> {


                 LazyColumn {
                     items(items = ciudades){
                         Text(text = it.name)
                         Text(text = it.state)
                         Text(text = "${it.lat}")
                         Text(text = "${it.lon}")



                     }
                 }

             }

             CiudadesEstado.Vacio -> EmptyView()
         }

         Button(onClick = { onAction(CiudadesIntencion.borrarLista) },
             colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
             Text(text = "Borrar lista",
                 color= Color.White
             )

         }
         Button(onClick = { onAction(CiudadesIntencion.mostrarLista) },
             colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
             Text(text = "Buscar Ciudad",
                 color= Color.White)
         }


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
    val ciudades = remember { mutableStateListOf<Ciudad>() }
    SegundoParcialTheme {
        CiudadesView(state = CiudadesEstado.Vacio, onAction = {},ciudades = ciudades)
    }
}

@Preview(showBackground = true)
@Composable
fun CiudadesPreviewExitoso() {
    val ciudades = remember { mutableStateListOf<Ciudad>() }
    SegundoParcialTheme {

        CiudadesView(
            state = CiudadesEstado.Exitoso(
                "Bs As", -1254.0, -18756.0, "Ar"
            ),
            onAction = {},
            ciudades = ciudades)
    }
}
