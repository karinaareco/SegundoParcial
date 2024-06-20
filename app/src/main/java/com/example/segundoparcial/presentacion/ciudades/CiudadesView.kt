package com.example.segundoparcial.presentacion.ciudades

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.segundoparcial.presentacion.clima.actual.climaView
import com.example.segundoparcial.presentacion.clima.pronostico.PronosticoView
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
                value = ciudadIngresada,

                onValueChange = {
                    ciudadIngresada = it
                    //onAction(CiudadesIntencion.Buscar(ciudadIngresada))
                },
                textStyle = TextStyle(color = Color.White),

                modifier = modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Unspecified,
                    unfocusedContainerColor = Color.Unspecified,
                    disabledContainerColor = Color.Unspecified,
                ),
                label = {
                    Text(
                        text = "Ubicacion",
                        color = Color.White
                    )
                },
            )

            when (state) {
                is CiudadesEstado.Cargando -> Text(text = "cargando")
                is CiudadesEstado.Error -> Text(text = state.mensaje)
                is CiudadesEstado.Resultado -> ListaDeCiudades(
                    ciudades = state.ciudades,
                    onAction = onAction
                )

                is CiudadesEstado.Vacio -> Text(text = "")
                is CiudadesEstado.Exitoso -> climaView(
                    ciudad = state.ciudad,
                    temperatura = state.temperatura,
                    descripcion = state.descripcion,
                    st = state.st,
                    nubes = state.clouds,
                    viento = state.wind,
                    icon = state.icon

                )
                is CiudadesEstado.okClima-> PronosticoView(state.climas)

            }

                  Button(
                      onClick = { onAction(CiudadesIntencion.Buscar(ciudadIngresada)) },

                      colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                  ) {
                      Text(
                          text = "Buscar",
                          color = Color.White
                      )
                  }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeCiudades(ciudades: List<Ciudad>, onAction: (CiudadesIntencion) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(items = ciudades) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .clickable {},
                onClick = { },
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black
                )
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = it.name,
                        color = Color.White
                    )
                    Text(
                        text = it.state,
                        color = Color.White
                    )
                    Text(
                        text = "Lat:${it.lat}",
                        color = Color.White
                    )
                    Text(
                        text = "Long:${it.lon}",
                        color = Color.White
                    )
                    Button(onClick = { onAction(CiudadesIntencion.MostraClima(it)) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
                        Text(text = "Ver Clima",
                            color = Color.Black)

                    }
                    Button(onClick = { onAction(CiudadesIntencion.MostraPronostico(it.name)) }) {
                        Text(text = "Pronostico de la semana")
                        
                    }

                }
            }
        }
    }
}

