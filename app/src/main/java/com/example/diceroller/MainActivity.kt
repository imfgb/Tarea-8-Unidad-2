/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.diceroller // Paquete de la aplicación

import android.os.Bundle // Para manejar el ciclo de vida de la actividad
import androidx.activity.ComponentActivity // Actividad base para Jetpack Compose
import androidx.activity.compose.setContent // Permite definir el contenido composable
import androidx.compose.foundation.Image // Componente para mostrar imágenes
import androidx.compose.foundation.layout.Column // Layout para organizar elementos verticalmente
import androidx.compose.foundation.layout.Spacer // Componente espaciador (no usado en este archivo)
import androidx.compose.foundation.layout.fillMaxSize // Modificador para ocupar todo el espacio disponible
import androidx.compose.foundation.layout.height // Modificador de altura (no usado)
import androidx.compose.foundation.layout.wrapContentSize // Ajusta el tamaño al contenido
import androidx.compose.material3.Button // Botón Material 3
import androidx.compose.material3.MaterialTheme // Tema visual Material 3
import androidx.compose.material3.Surface // Contenedor de fondo estilizado
import androidx.compose.material3.Text // Componente de texto
import androidx.compose.runtime.Composable // Marca funciones que definen UI en Compose
import androidx.compose.runtime.getValue // Soporte para obtener estados mutables
import androidx.compose.runtime.mutableStateOf // Crea estado observable que dispara recomposición
import androidx.compose.runtime.remember // Permite mantener estado entre recomposiciones
import androidx.compose.runtime.setValue // Permite modificar estados observables
import androidx.compose.ui.Alignment // Alineación de elementos en layouts
import androidx.compose.ui.Modifier // Permite aplicar comportamiento visual a componentes
import androidx.compose.ui.res.painterResource // Carga recursos gráficos desde drawable
import androidx.compose.ui.res.stringResource // Carga cadenas desde strings.xml
import androidx.compose.ui.tooling.preview.Preview // Permite previsualizar funciones composables
import androidx.compose.ui.unit.dp // Unidad para medidas de espacio
import androidx.compose.ui.unit.sp // Unidad para tamaño de texto
import com.example.diceroller.ui.theme.DiceRollerTheme // Importa el tema de la aplicación

// Actividad principal de la aplicación
class MainActivity : ComponentActivity() {
    // Función que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define el contenido de la actividad usando Compose
        setContent {
            // Aplica el tema visual de la app
            DiceRollerTheme {
                // Superficie que cubre toda la pantalla con color de fondo del tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Llama a la función composable principal
                    DiceRollerApp()
                }
            }
        }
    }
}

// Función composable para previsualizar o renderizar el contenido principal
@Preview
@Composable
fun DiceRollerApp() {
    // Llama a la función con el layout de dado y botón, centrado en pantalla
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

// Función composable que muestra una imagen de dado y un botón para lanzarlo
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    // Variable de estado que guarda el resultado actual del dado (1 por defecto)
    var result by remember { mutableStateOf(1) }

    // Determina qué imagen usar con base en el valor del dado
    val imageResource = when(result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    // Layout vertical que contiene la imagen del dado y el botón
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Muestra la imagen del dado correspondiente al valor actual
        Image(
            painter = painterResource(imageResource),
            contentDescription = result.toString() // Descripción para accesibilidad
        )

        // Botón que genera un nuevo número aleatorio del 1 al 6 al presionarlo
        Button(
            onClick = { result = (1..6).random() },
        ) {
            // Texto del botón obtenido desde recursos
            Text(
                text = stringResource(R.string.roll),
                fontSize = 24.sp // Tamaño de letra grande para mayor visibilidad
            )
        }
    }
}