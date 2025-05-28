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

package com.example.tiptime // Define el paquete del proyecto

import android.os.Bundle // Para manejar el ciclo de vida de la actividad
import androidx.activity.ComponentActivity // Clase base para actividades con Compose
import androidx.activity.compose.setContent // Permite establecer la UI usando Compose
import androidx.activity.enableEdgeToEdge // Habilita el diseño de pantalla completa (edge-to-edge)

import androidx.compose.foundation.layout.Arrangement // Para alinear elementos en un layout
import androidx.compose.foundation.layout.Column // Layout vertical para organizar elementos
import androidx.compose.foundation.layout.Spacer // Componente para crear espacios vacíos
import androidx.compose.foundation.layout.fillMaxSize // Hace que el componente ocupe toda la pantalla
import androidx.compose.foundation.layout.fillMaxWidth // Hace que el componente ocupe todo el ancho
import androidx.compose.foundation.layout.height // Define altura fija a un componente
import androidx.compose.foundation.layout.padding // Agrega relleno interno (padding)
import androidx.compose.foundation.layout.safeDrawingPadding // Padding seguro que evita solaparse con UI del sistema
import androidx.compose.foundation.layout.statusBarsPadding // Padding para evitar solaparse con la barra de estado
import androidx.compose.foundation.rememberScrollState // Crea estado de scroll vertical
import androidx.compose.foundation.text.KeyboardOptions // Permite configurar tipo de teclado
import androidx.compose.foundation.verticalScroll // Permite hacer scroll vertical
import androidx.compose.material3.MaterialTheme // Permite acceder al tema visual
import androidx.compose.material3.Surface // Contenedor de fondo con color del tema
import androidx.compose.material3.Text // Componente de texto
import androidx.compose.material3.TextField // Campo de texto con etiqueta
import androidx.compose.runtime.Composable // Marca funciones que representan UI en Compose
import androidx.compose.runtime.getValue // Para leer valores reactivamente
import androidx.compose.runtime.mutableStateOf // Crea una variable de estado
import androidx.compose.runtime.remember // Conserva el estado entre recomposiciones
import androidx.compose.runtime.setValue // Para asignar nuevos valores al estado
import androidx.compose.ui.Alignment // Para alinear elementos dentro de layouts
import androidx.compose.ui.Modifier // Modificador para aplicar comportamientos visuales
import androidx.compose.ui.res.stringResource // Permite obtener textos desde resources
import androidx.compose.ui.text.input.KeyboardType // Define el tipo de teclado (numérico, texto, etc.)
import androidx.compose.ui.tooling.preview.Preview // Permite ver una vista previa en Android Studio
import androidx.compose.ui.unit.dp // Unidad de medida para dimensiones
import com.example.tiptime.ui.theme.TipTimeTheme // Importa el tema personalizado de la app
import java.text.NumberFormat // Para dar formato a cantidades monetarias

// Actividad principal de la aplicación
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Habilita diseño edge-to-edge (pantalla completa)
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme { // Aplica el tema visual de la app
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
                ) {
                    TipTimeLayout() // Llama al composable principal
                }
            }
        }
    }
}

// Función composable principal que define la interfaz de la app
@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") } // Estado para guardar lo que el usuario escribe

    val amount = amountInput.toDoubleOrNull() ?: 0.0 // Convierte el input a Double, o 0.0 si es inválido
    val tip = calculateTip(amount) // Calcula la propina

    Column(
        modifier = Modifier
            .statusBarsPadding() // Evita que el contenido se superponga con la barra de estado
            .padding(horizontal = 40.dp) // Padding lateral
            .verticalScroll(rememberScrollState()) // Permite hacer scroll si hay mucho contenido
            .safeDrawingPadding(), // Padding seguro para evitar solaparse con bordes del sistema
        horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
        verticalArrangement = Arrangement.Center // Centra el contenido verticalmente
    ) {
        Text(
            text = stringResource(R.string.calculate_tip), // Título de la app desde recursos
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp) // Espaciado arriba y abajo
                .align(alignment = Alignment.Start) // Alineado a la izquierda dentro del Column
        )
        EditNumberField(
            value = amountInput, // Valor actual del campo de texto
            onValueChanged = { amountInput = it }, // Callback para actualizar el estado cuando se escribe
            modifier = Modifier
                .padding(bottom = 32.dp) // Espacio inferior
                .fillMaxWidth() // El campo ocupa todo el ancho disponible
        )
        Text(
            text = stringResource(R.string.tip_amount, tip), // Texto que muestra el monto calculado de la propina
            style = MaterialTheme.typography.displaySmall // Estilo de texto grande del tema
        )
        Spacer(modifier = Modifier.height(150.dp)) // Espacio adicional al final para estética
    }
}

// Composable reutilizable para el campo de entrada de número
@Composable
fun EditNumberField(
    value: String, // Valor actual del campo de texto
    onValueChanged: (String) -> Unit, // Función que se llama al cambiar el valor
    modifier: Modifier // Modificador para estilo y comportamiento
) {
    TextField(
        value = value, // Valor del campo
        singleLine = true, // Solo permite una línea de texto
        modifier = modifier, // Aplica modificadores dados
        onValueChange = onValueChanged, // Se ejecuta al escribir en el campo
        label = { Text(stringResource(R.string.bill_amount)) }, // Etiqueta del campo desde recursos
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Usa teclado numérico
    )
}

/**
 * Calcula la propina basada en la cantidad y el porcentaje (15% por defecto)
 * Devuelve una cadena con formato de moneda local.
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = tipPercent / 100 * amount // Calcula el 15% de la cantidad
    return NumberFormat.getCurrencyInstance().format(tip) // Formatea como moneda
}

// Vista previa en Android Studio del layout
@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout()
    }
}