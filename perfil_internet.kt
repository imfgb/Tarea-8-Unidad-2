fun main() {    
    // Se crea una persona llamada Amanda, de 33 años, con pasatiempo "play tennis" y sin referidor
    val amanda = Person("Amanda", 33, "play tennis", null)

    // Se crea una persona llamada Atiqah, de 28 años, con pasatiempo "climb" y referida por Amanda
    val atiqah = Person("Atiqah", 28, "climb", amanda)

    // Se muestra el perfil de Amanda
    amanda.showProfile()

    // Se muestra el perfil de Atiqah
    atiqah.showProfile()
}

// Se define la clase Person para representar el perfil de una persona
class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    // Función que muestra los detalles del perfil
    fun showProfile() {
        // Se imprime el nombre
        println("Name: $name")

        // Se imprime la edad
        println("Age: $age")

        // Se revisa si hay un referidor
        if (referrer != null && referrer.hobby != null) {
            // Si hay referidor y este tiene un pasatiempo
            println("Likes to $hobby. Has a referrer named ${referrer.name}, who likes to ${referrer.hobby}.")
        } else if (referrer != null) {
            // Si hay referidor pero sin pasatiempo definido
            println("Likes to $hobby. Has a referrer named ${referrer.name}.")
        } else {
            // Si no hay referidor
            println("Likes to $hobby. Doesn't have a referrer.")
        }
    }
}