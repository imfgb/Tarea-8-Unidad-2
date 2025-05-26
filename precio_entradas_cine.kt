fun main() {
    // Se declara una variable para representar a un niño de 5 años
    val child = 5

    // Se declara una variable para representar a un adulto de 28 años
    val adult = 28

    // Se declara una variable para representar a un adulto mayor de 87 años
    val senior = 87

    // Se indica que hoy es lunes (lo cual afecta el precio estándar)
    val isMonday = true

    // Se imprime el precio del boleto para el niño
    println("The movie ticket price for a person aged $child is \$${ticketPrice(child, isMonday)}.")

    // Se imprime el precio del boleto para el adulto
    println("The movie ticket price for a person aged $adult is \$${ticketPrice(adult, isMonday)}.")

    // Se imprime el precio del boleto para el adulto mayor
    println("The movie ticket price for a person aged $senior is \$${ticketPrice(senior, isMonday)}.")
}

// Función que retorna el precio del boleto según la edad y si es lunes
fun ticketPrice(age: Int, isMonday: Boolean): Int {
    // Si la edad es menor o igual a 12, se aplica el precio infantil
    if (age in 0..12) {
        return 15
    }

    // Si la edad está entre 13 y 60 (inclusive)
    if (age in 13..60) {
        // Si es lunes, se aplica el precio estándar con descuento
        return if (isMonday) 25 else 30
    }

    // Si la edad está entre 61 y 100, se aplica el precio para adultos mayores
    if (age in 61..100) {
        return 20
    }

    // Si la edad no está en los rangos válidos, se retorna -1
    return -1
}