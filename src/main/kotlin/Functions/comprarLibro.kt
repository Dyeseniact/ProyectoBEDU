package Functions

fun comprarLibro(opt: String){
    when (opt) {
        "1" -> {
            comprar()
        }
        "2" -> {
            alquilar()
        }
        "3" -> {
            leerOnline()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }




}




fun comprar(){
println("Proceda a pagar su compra")


    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}

fun alquilar(){
    println("Proceda a pagar el alquiler de su libro")

    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}

fun leerOnline(){
    println("Proceda a pagar su libro de lectura")

    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}
