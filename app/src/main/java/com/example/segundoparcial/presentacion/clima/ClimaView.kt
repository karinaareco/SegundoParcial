package com.example.segundoparcial.presentacion.clima


import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segundoparcial.R
import com.example.segundoparcial.ui.theme.SegundoParcialTheme


@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    state: ClimaEstado,
    onAction: (ClimaIntencion) -> Unit
) {
    Scaffold(
        containerColor = Color(0xFFFFFFFF)
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .fillMaxWidth()
                .padding(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var ciudadIngresada by remember { mutableStateOf("") }
            Row {
                OutlinedTextField(
                    value = ciudadIngresada,
                    label = { Text("Buscar ubicacion") },
                    onValueChange = { ciudadIngresada = it },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    }
                )

                }



            when (state) {
                is ClimaEstado.Error -> ErrorView(mensaje = state.mensaje)
                is ClimaEstado.Exitoso -> climaView(
                    ciudad = state.ciudad,
                    temperatura = state.temperatura,
                    descripcion = state.descripcion,
                    st = state.st

                )

                ClimaEstado.Vacio -> EmptyView()
                ClimaEstado.Cargando -> EmptyView()
            }

            Spacer(modifier = Modifier.height(100.dp))

            FilledTonalButton(onClick = { onAction(ClimaIntencion.BorrarTodo) },colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Borrar todo")
            }
            Button(onClick = { onAction(ClimaIntencion.MostrarCaba) },colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Mostrar Caba")
            }
            Button(onClick = { onAction(ClimaIntencion.MostrarCordoba) },colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Mostrar Cordoba")
            }
            Button(onClick = { onAction(ClimaIntencion.MostrarUbicacion) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Conocer ubicacion")
                Image(
                    painter = painterResource(id = R.drawable.logoubicacion),
                    contentDescription = "Ubicacion"
                )
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
fun climaView(ciudad: String, temperatura: Double, descripcion: String, st: Double) {
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

    }
}

@Preview(showBackground = true)
@Composable
fun ClimaPreviewExitoso() {
    SegundoParcialTheme {
        ClimaView(state = ClimaEstado.Exitoso(ciudad = "Mendoza", temperatura = 0.0), onAction = {})
    }
}