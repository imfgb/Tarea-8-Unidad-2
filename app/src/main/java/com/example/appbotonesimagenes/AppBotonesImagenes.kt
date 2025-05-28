package com.example.appbotonesimagenes // Define el paquete de la aplicación

import android.os.Bundle // Importa la clase Bundle para guardar/restaurar estado de la actividad
import androidx.activity.ComponentActivity // Clase base para actividades en Jetpack Compose
import androidx.activity.compose.setContent // Establece el contenido de la UI usando Compose
import androidx.compose.foundation.Image // Componente para mostrar imágenes
import androidx.compose.foundation.layout.* // Contiene herramientas para crear layouts flexibles
import androidx.compose.material3.Button // Botón con estilo Material 3
import androidx.compose.material3.Text // Componente para mostrar texto
import androidx.compose.runtime.* // Soporte para estados y composables en Compose
import androidx.compose.ui.Alignment // Define alineaciones dentro de layouts
import androidx.compose.ui.Modifier // Permite aplicar comportamiento visual a componentes
import androidx.compose.ui.graphics.painter.Painter // Interfaz para recursos de imagen pintables
import androidx.compose.ui.res.painterResource // Carga imágenes desde recursos (drawable)
import androidx.compose.ui.unit.dp // Define unidades de medida (density-independent pixels)

// Actividad principal de la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establece el contenido de la actividad con un composable
        setContent {
            AppBotonesImagenes() // Llama a la función composable principal
        }
    }
}

// Composable que contiene la lógica y UI de la aplicación
@Composable
fun AppBotonesImagenes() {
    val maxEtapas = 7 // Número máximo de etapas del árbol
    var etapaActual by remember { mutableStateOf(0) } // Estado que guarda la etapa actual del árbol

    // Contenedor vertical que organiza imagen y botón
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Centra los elementos horizontalmente
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
        modifier = Modifier.fillMaxSize() // El contenedor ocupa toda la pantalla
    ) {
        // Selecciona la imagen adecuada según la etapa actual
        val imagenRes: Painter = when (etapaActual) {
            0 -> painterResource(id = R.drawable.etapa_0)
            1 -> painterResource(id = R.drawable.etapa_1)
            2 -> painterResource(id = R.drawable.etapa_2)
            3 -> painterResource(id = R.drawable.etapa_3)
            4 -> painterResource(id = R.drawable.etapa_4)
            5 -> painterResource(id = R.drawable.etapa_5)
            6 -> painterResource(id = R.drawable.etapa_6)
            else -> painterResource(id = R.drawable.etapa_7) // Caso por defecto si etapaActual > 6
        }

        // Muestra la imagen correspondiente a la etapa del árbol
        Image(
            painter = imagenRes, // Imagen cargada según etapa
            contentDescription = "Etapa de crecimiento del árbol $etapaActual", // Descripción accesible
            modifier = Modifier.size(200.dp) // Tamaño fijo de la imagen
        )

        Spacer(modifier = Modifier.height(16.dp)) // Espacio entre la imagen y el botón

        // Botón para avanzar la etapa del árbol
        Button(onClick = {
            if (etapaActual < maxEtapas) {
                etapaActual++ // Aumenta la etapa si no ha llegado al máximo
            } else {
                etapaActual = 0 // Reinicia a 0 si se pasó de la última etapa
            }
        }) {
            Text(text = "Crecer el Árbol") // Texto dentro del botón
        }
    }
}