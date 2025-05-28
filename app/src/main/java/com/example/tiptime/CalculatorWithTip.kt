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

package com.example.tiptime // Paquete principal de la aplicación

import android.os.Bundle // Para manejar el ciclo de vida de la actividad
import androidx.activity.ComponentActivity // Actividad base para Jetpack Compose
import androidx.activity.compose.setContent // Permite establecer la UI en Compose
import androidx.activity.enableEdgeToEdge // Habilita diseño edge-to-edge (pantalla completa)
import androidx.annotation.DrawableRes // Anotación para IDs de drawables
import androidx.annotation.StringRes // Anotación para IDs de strings

// Layouts y espaciado
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState // Estado de scroll
import androidx.compose.foundation.text.KeyboardOptions // Opciones para el teclado
import androidx.compose.foundation.verticalScroll // Permite scroll vertical

// Componentes visuales de Material3
import androidx.compose.material3.*

// Estado Compose
import androidx.compose.runtime.*

// Modificadores y recursos
import androidx.compose.ui.Alignment // Alineación de elementos
import androidx.compose.ui.Modifier // Modificadores visuales y de comportamiento
import androidx.compose.ui.res.painterResource // Carga de imágenes desde recursos
import androidx.compose.ui.res.stringResource // Carga de texto desde strings.xml

// Entrada de texto
import androidx.compose.ui.text.input.ImeAction // Acciones del botón "Enter"
import androidx.compose.ui.text.input.KeyboardType // Tipo de teclado (numérico, texto, etc.)
import androidx.compose.ui.tooling.preview.Preview // Previsualización de la UI
import androidx.compose.ui.unit.dp // Unidad para medidas

import com.example.tiptime.ui.theme.TipTimeTheme // Tema visual de la aplicación

import java.text.NumberFormat // Formateo de números como monedas

// Actividad principal de la app
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Habilita diseño sin márgenes en bordes
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme { // Aplica el tema personalizado
                Surface(
                    modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
                ) {
                    TipTimeLayout() // Llama al componente composable principal
                }
            }
        }
    }
}

// Función composable principal de la UI
@Composable
fun TipTimeLayout() {
    var amountInput by remember { mutableStateOf("") } // Estado para el monto ingresado
    var tipInput by remember { mutableStateOf("") } // Estado para el porcentaje de propina
    var roundUp by remember { mutableStateOf(false) } // Estado del switch para redondear

    val amount = amountInput.toDoubleOrNull() ?: 0.0 // Convierte input a Double o usa 0.0
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0 // Igual para porcentaje
    val tip = calculateTip(amount, tipPercent, roundUp) // Calcula la propina

    Column(
        modifier = Modifier
            .statusBarsPadding() // Evita que se superponga con la barra de estado
            .padding(horizontal = 40.dp) // Margen lateral
            .verticalScroll(rememberScrollState()) // Habilita scroll si el contenido es largo
            .safeDrawingPadding(), // Padding para evitar áreas peligrosas (notch, etc.)
        horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente
        verticalArrangement = Arrangement.Center // Centra verticalmente
    ) {
        Text(
            text = stringResource(R.string.calculate_tip), // Título desde recursos
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp) // Espaciado arriba y abajo
                .align(alignment = Alignment.Start) // Alineación a la izquierda
        )

        // Campo para ingresar el total de la cuenta
        EditNumberField(
            label = R.string.bill_amount, // Texto del campo
            leadingIcon = R.drawable.money, // Ícono de dinero
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Teclado numérico
                imeAction = ImeAction.Next // "Enter" pasa al siguiente campo
            ),
            value = amountInput, // Valor actual del input
            onValueChanged = { amountInput = it }, // Actualiza el estado
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        // Campo para ingresar el porcentaje de propina
        EditNumberField(
            label = R.string.how_was_the_service, // Texto del campo
            leadingIcon = R.drawable.percent, // Ícono de porcentaje
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number, // Teclado numérico
                imeAction = ImeAction.Done // "Enter" cierra teclado
            ),
            value = tipInput, // Valor actual del input
            onValueChanged = { tipInput = it }, // Actualiza el estado
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth()
        )

        // Switch para decidir si redondear la propina
        RoundTheTipRow(
            roundUp = roundUp, // Valor actual del switch
            onRoundUpChanged = { roundUp = it }, // Actualiza estado
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Texto con el resultado del cálculo
        Text(
            text = stringResource(R.string.tip_amount, tip), // Propina formateada
            style = MaterialTheme.typography.displaySmall // Estilo grande del tema
        )

        Spacer(modifier = Modifier.height(150.dp)) // Espacio visual inferior
    }
}

// Componente reutilizable para campos de texto con ícono e input numérico
@Composable
fun EditNumberField(
    @StringRes label: Int, // ID del string del label
    @DrawableRes leadingIcon: Int, // ID del drawable del ícono
    keyboardOptions: KeyboardOptions, // Configuración del teclado
    value: String, // Valor del campo
    onValueChanged: (String) -> Unit, // Función que actualiza el valor
    modifier: Modifier = Modifier // Modificadores para aplicar estilo y tamaño
) {
    TextField(
        value = value, // Valor actual del campo
        singleLine = true, // Limita a una sola línea
        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) }, // Ícono al inicio
        modifier = modifier, // Aplica modificadores
        onValueChange = onValueChanged, // Callback para cambios en el texto
        label = { Text(stringResource(label)) }, // Texto del label
        keyboardOptions = keyboardOptions // Configuración del teclado
    )
}

// Componente que muestra un switch para redondear la propina
@Composable
fun RoundTheTipRow(
    roundUp: Boolean, // Estado actual del switch
    onRoundUpChanged: (Boolean) -> Unit, // Callback cuando cambia el switch
    modifier: Modifier = Modifier // Modificadores opcionales
) {
    Row(
        modifier = modifier.fillMaxWidth(), // Ocupa todo el ancho disponible
        verticalAlignment = Alignment.CenterVertically // Centrado vertical
    ) {
        Text(text = stringResource(R.string.round_up_tip)) // Texto a la izquierda
        Switch(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End), // Switch alineado a la derecha
            checked = roundUp, // Estado actual
            onCheckedChange = onRoundUpChanged // Cambia el estado al presionar
        )
    }
}

/**
 * Calcula la propina en base al monto y porcentaje dados.
 * Si `roundUp` es verdadero, redondea el resultado hacia arriba.
 * Devuelve el valor como una cadena con formato de moneda local.
 */
private fun calculateTip(amount: Double, tipPercent: Double = 15.0, roundUp: Boolean): String {
    var tip = tipPercent / 100 * amount // Calcula el porcentaje de propina
    if (roundUp) {
        tip = kotlin.math.ceil(tip) // Redondea hacia arriba si es necesario
    }
    return NumberFormat.getCurrencyInstance().format(tip) // Retorna el valor formateado como moneda
}

// Función para previsualizar la UI desde el editor
@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    TipTimeTheme {
        TipTimeLayout() // Muestra la interfaz principal
    }
}