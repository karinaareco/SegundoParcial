package com.example.segundoparcial.repository.modelos
import kotlinx.serialization.Serializable
data class Clima (
    val temperatura: Int,
    val humedad: Float,
    val ciudad: String,
    val st: Int,
    val viento: Int,
    val latitud: Long,
    val longitud: Long,
    val estado: String
)

@Serializable
data class Clima2(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val name: String,
)
@Serializable
data class Coord(
    val lon: Double,
    val lat: Double,
)
@Serializable
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)
@Serializable
data class Main(
    val temp: Double,
    val feelsLike: Double = 0.0,
    val tempMin: Double = 0.0,
    val tempMax: Double = 0.0,
    val pressure: Long,
    val humidity: Long,
)
@Serializable
data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double,
)

@Serializable
data class Clouds(
    val all: Long,
)
@Serializable
data class Sys(
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)