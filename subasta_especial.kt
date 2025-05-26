// Función principal donde inicia la ejecución del programa
fun main() {
    // Se crea una instancia de la clase Bid con una oferta de 5000 hecha por "Private Collector"
    val winningBid = Bid(5000, "Private Collector")

    // Se imprime el precio de venta del ítem A usando la oferta existente
    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")

    // Se imprime el precio de venta del ítem B sin oferta, por lo que se usa el precio mínimo
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

// Clase Bid que representa una oferta en una subasta, contiene el monto ofrecido y el nombre del postor
class Bid(val amount: Int, val bidder: String)

// Función que determina el precio final de un ítem en una subasta
// Si hay una oferta válida, se usa el monto de la oferta; de lo contrario, se usa el precio mínimo
fun auctionPrice(bid: Bid?, minimumPrice: Int): Int {
    return bid?.amount ?: minimumPrice
    // El operador Elvis (?:) retorna bid.amount si bid no es null, si es null retorna minimumPrice
}