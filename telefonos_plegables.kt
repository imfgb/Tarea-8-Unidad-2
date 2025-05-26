// Se declara una clase abierta llamada Phone que puede ser heredada por otras clases
open class Phone(var isScreenLightOn: Boolean = false) {

    // Método abierto que permite ser sobrescrito; enciende la luz de la pantalla
    open fun switchOn() {
        isScreenLightOn = true
    }

    // Método que apaga la luz de la pantalla
    fun switchOff() {
        isScreenLightOn = false
    }

    // Método que imprime el estado actual de la luz de la pantalla (encendida o apagada)
    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight.")
    }
}

// Se declara la clase FoldablePhone que hereda de Phone
class FoldablePhone(var isFolded: Boolean = true) : Phone() {

    // Sobrescribe el método switchOn de Phone
    // Solo enciende la pantalla si el teléfono no está plegado
    override fun switchOn() {
        if (!isFolded) {
            isScreenLightOn = true
        }
    }

    // Método que pliega el teléfono (cambia su estado a plegado)
    fun fold() {
        isFolded = true
    }

    // Método que despliega el teléfono (cambia su estado a no plegado)
    fun unfold() {
        isFolded = false
    }
}

// Función principal que ejecuta el comportamiento del teléfono plegable
fun main() {
    // Se crea una instancia de FoldablePhone
    val newFoldablePhone = FoldablePhone()

    // Intenta encender la pantalla mientras el teléfono está plegado
    newFoldablePhone.switchOn()

    // Muestra si la pantalla está encendida o apagada
    newFoldablePhone.checkPhoneScreenLight()

    // Despliega el teléfono
    newFoldablePhone.unfold()

    // Vuelve a intentar encender la pantalla ahora que está desplegado
    newFoldablePhone.switchOn()

    // Muestra nuevamente el estado de la pantalla
    newFoldablePhone.checkPhoneScreenLight()
}