package com.example.segundoparcial.presentacion.ciudades


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.segundoparcial.R

import com.example.segundoparcial.ui.theme.SegundoParcialTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuenosAiresPage(modifier: Modifier = Modifier) {

    Scaffold(containerColor = Color(0xFFCDEDA3)
        ) {

        Column ( modifier = Modifier.padding(it).fillMaxWidth(),
            verticalArrangement = Arrangement.Center){
            Row {
                Text(
                    text = "16º",
                    style = MaterialTheme.typography.titleLarge
                )

                Image(
                    painter = painterResource(
                        id = R.drawable.icono_clima_soleado
                    ),
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            }
            Row {
                Text(
                    text = "Belgrano",
                    style = MaterialTheme.typography.bodyMedium
                )
                Image(
                    painter = painterResource(id = R.drawable.logoubicacion),
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )

            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "16º/12º Feels like 16º",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "Thu,17:38",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Image(
                    painter = painterResource(id = R.drawable.e_clima_09_05),
                    contentDescription = "Pronostico semanal"
                )
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row (modifier=Modifier.align(Alignment.CenterHorizontally)){
                Card(colors = CardDefaults.cardColors(containerColor = Color.Black)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.viento),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier
                            .background(Color.White)
                            .align(Alignment.CenterVertically))
                        Text(text = "|", color = Color.LightGray)
                        Image(
                            painter = painterResource(id = R.drawable.humedad),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                        Spacer(modifier = Modifier
                            .background(Color.White)
                            .align(Alignment.CenterVertically))
                        Text(text = "|", color = Color.LightGray)
                        Image(
                            painter = painterResource(id = R.drawable.termometro),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 16.dp)
                        )

                    }


                }


            }


        }


    }
}


@Preview(showBackground = true)
@Composable
fun BuenosAiresPreview() {
    SegundoParcialTheme {
        BuenosAiresPage()
    }
}