fun main() {
    // Se declara una variable que representa la cantidad de notificaciones por la mañana
    val morningNotification = 51

    // Se declara una variable que representa la cantidad de notificaciones por la noche
    val eveningNotification = 135

    // Se llama a la función para imprimir el resumen de notificaciones por la mañana
    printNotificationSummary(morningNotification)

    // Se llama a la función para imprimir el resumen de notificaciones por la noche
    printNotificationSummary(eveningNotification)
}

// Función que recibe un número entero y muestra un resumen dependiendo de la cantidad
fun printNotificationSummary(numberOfMessages: Int) {

    // Si el número de notificaciones es menor que 100, se muestra la cantidad exacta
    if (numberOfMessages < 100) {
        println("You have $numberOfMessages notifications.")
    
    // Si el número de notificaciones es 100 o más, se muestra un mensaje general
    } else {
        println("Your phone is blowing up! You have 99+ notifications.")
    }
}