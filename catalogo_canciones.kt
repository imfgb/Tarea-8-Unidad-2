// Se define una clase llamada Song para representar la estructura de una canción
class Song(
    val title: String,          // Título de la canción
    val artist: String,         // Artista que interpreta la canción
    val releaseYear: Int,       // Año de publicación
    val playCount: Int          // Recuento de reproducciones
) {
    // Propiedad que indica si la canción es popular o no
    val isPopular: Boolean
        get() = playCount >= 1000

    // Método que imprime la descripción de la canción
    fun printDescription() {
        println("\"$title\", interpretada por $artist, se lanzó en $releaseYear.")
    }
}

fun main() {
    // Se crea una instancia de la clase Song con una canción poco popular
    val song1 = Song("Viento Azul", "Sol Sonoro", 2019, 350)

    // Se crea una instancia de la clase Song con una canción popular
    val song2 = Song("Luz de la Noche", "Estrella Beat", 2023, 5400)

    // Se imprime la descripción de la primera canción
    song1.printDescription()
    // Se imprime si la canción es popular
    println("¿Es popular? ${song1.isPopular}")

    // Se imprime la descripción de la segunda canción
    song2.printDescription()
    // Se imprime si la canción es popular
    println("¿Es popular? ${song2.isPopular}")
}