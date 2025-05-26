fun main() {
    // Se llama a la función para convertir de Celsius a Fahrenheit
    printFinalTemperature(
        initialMeasurement = 27.0,
        initialUnit = "Celsius",
        finalUnit = "Fahrenheit",
        conversionFormula = { celsius -> (9.0 / 5) * celsius + 32 }
    )

    // Se llama a la función para convertir de Kelvin a Celsius
    printFinalTemperature(
        initialMeasurement = 350.0,
        initialUnit = "Kelvin",
        finalUnit = "Celsius",
        conversionFormula = { kelvin -> kelvin - 273.15 }
    )

    // Se llama a la función para convertir de Fahrenheit a Kelvin
    printFinalTemperature(
        initialMeasurement = 10.0,
        initialUnit = "Fahrenheit",
        finalUnit = "Kelvin",
        conversionFormula = { fahrenheit -> (5.0 / 9) * (fahrenheit - 32) + 273.15 }
    )
}

// Función que imprime el resultado de la conversión de temperatura
fun printFinalTemperature(
    initialMeasurement: Double, 
    initialUnit: String, 
    finalUnit: String, 
    conversionFormula: (Double) -> Double
) {
    // Se aplica la fórmula de conversión y se formatea el resultado a dos decimales
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement))

    // Se imprime el resultado con las unidades originales y finales
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}