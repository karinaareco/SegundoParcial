package com.example.segundoparcial.presentacion.clima


import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segundoparcial.R
import com.example.segundoparcial.ui.theme.SegundoParcialTheme


@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    state : ClimaEstado,
    onAction: (ClimaIntencion)->Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var ciudadABuscar by remember { mutableStateOf("")}
Row {
    OutlinedTextField(value = ciudadABuscar, onValueChange = {ciudadABuscar=it} )
}


        when(state){
            is ClimaEstado.Ubicacion -> ErrorView(mensaje = state.ciudad)
            is ClimaEstado.Exitoso -> ClimaView(
                ciudad = state.ciudad,
                temperatura = state.temperatura,
                descripcion = state.descripcion,
                st = state.st
            )
            ClimaEstado.Vacio -> EmptyView()
            ClimaEstado.Cargando -> EmptyView()
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(onClick = { onAction(ClimaIntencion.BorrarTodo) }) {
            Text(text = "Borrar todo")
        }
        Button(onClick = { onAction(ClimaIntencion.MostrarCaba) }) {
            Text(text = "Mostrar Caba")
        }
        Button(onClick = { onAction(ClimaIntencion.MostrarCordoba) }) {
            Text(text = "Mostrar Cordoba")
        }
        Button(onClick = { onAction(ClimaIntencion.MostrarUbicacion) }) {
            Text(text = "Conocer ubicacion")
           Image(painter = painterResource(id = R.drawable.logoubicacion), contentDescription = "Ubicacion")
        }
    }
}

@Composable
fun EmptyView(){
    Text(text = "No hay nada que mostrar")
}

@Composable
fun ErrorView(mensaje: String){
    Text(text = mensaje)
}

@Composable
fun ClimaView(ciudad: String, temperatura: Double, descripcion: String, st:Double){
    Column {
        Text(text = ciudad, style = MaterialTheme.typography.titleMedium)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = "sensacionTermica: ${st}°", style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewVacio() {
    SegundoParcialTheme {
        ClimaView(state = ClimaEstado.Vacio, onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewUbicacion() {
    SegundoParcialTheme {
        ClimaView(state = ClimaEstado.Ubicacion("Esta es la ubicacion"), onAction = {})
    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewExitoso() {
    SegundoParcialTheme {
        ClimaView(state = ClimaEstado.Exitoso(ciudad = "Mendoza", temperatura = 0.0), onAction = {})
    }
}