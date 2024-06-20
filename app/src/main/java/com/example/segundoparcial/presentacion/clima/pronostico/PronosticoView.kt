package com.example.segundoparcial.presentacion.clima.pronostico

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.segundoparcial.repository.modelos.ListForecast


@Composable
fun PronosticoView(
    modifier: Modifier = Modifier,
    state: PronosticoEstado,
    onAction: (PronosticoIntencion) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(PronosticoIntencion.actualizarClima)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (state) {
            is PronosticoEstado.Error -> ErrorView(mensaje = state.mensaje)
            is PronosticoEstado.Exitoso -> PronosticoView(state.climas)
            PronosticoEstado.Vacio -> LoadingView()
            PronosticoEstado.Cargando -> EmptyView()
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun EmptyView() {
    Text(text = "No hay nada que mostrar")
}

@Composable
fun LoadingView() {
    Text(text = "Cargando")
}

@Composable
fun ErrorView(mensaje: String) {
    Text(text = mensaje)
}

@Composable
fun PronosticoView(climas: List<ListForecast>) {
    LazyColumn {
        items(items = climas) {
            Card(
                modifier = Modifier
                    .size(width = 340.dp, height = 200.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Black)
            ) {
                Column (modifier = Modifier.padding(10.dp)){
                    Text(
                        text = "Temperatura: ${it.main.temp}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Humedad: ${it.main.humidity} %",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Temperatura max: ${it.main.temp_max}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "Temperatura min: ${it.main.temp_min}",
                        color = Color.White
                    )

                }



            }
        }
    }
}