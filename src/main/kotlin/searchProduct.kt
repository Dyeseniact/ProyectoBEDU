import models.listBook
import javax.swing.JOptionPane


fun searchProduct(){

    var findProduct = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar artículos\n" +
            "2. Buscar libros\n" +
            "3. Buscar Revistas\n")
    when (findProduct) {
        "1" -> {
            searchArticle()
        }
        "2" -> {
            searchBook()
        }
        "3" -> {
            searchMagazine()
        }
        else -> {
            //print("Por favor, seleccione un método de búsqueda correcto")
            JOptionPane.showConfirmDialog(null,"Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }
}


fun searchArticle(){


}

fun searchMagazine(){

}



fun searchBook(){

    var findBook = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar por Título\n" +
            "2. Buscar por autores\n" +
            "3. Buscar por género\n" +
            "4. Volver al menú")
    when (findBook) {
        "1" -> {
            buscarLibroTitulo()
        }
        "2" -> {
            buscarLibroAutor()
        }
        "3" -> {
            buscarLibroGenero()
        }
        "4" -> {
            returnMenu()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }
}

fun buscarLibroTitulo(){
    // A redactar en función de las BD de Libros
    println("Introduce el Título deseado")
    var busquedaTitulo: String = JOptionPane.showInputDialog("Introduce el Título deseado")
    //Mensaje de prueba
    println("Búscando el Título $busquedaTitulo")
    // Aún por desarrollar

    for (i in (0..(listBook.size-1))){
        if(busquedaTitulo == listBook[i]?.title){
            // if(busquedaTitulo.equals(listBook[i]?.title)){

            val buy = JOptionPane.showInputDialog("El libro está disponible ¿Que desea realizar? " +
                    "Seleccione una opción.\n " +
                    "1. Comprar en físico\n" +
                    "2. Rentar \n" +
                    "3. Lectura Online\n")
            comprarLibro(buy)
        }else {
            val salir = JOptionPane.showInputDialog("El libro no se encuentró."+
                    "Seleccione una opción.\n " +
                    "1. Volver al Menú\n" +
                    "2. Realizar otra búsqueda\n")
            if(salir == "1"){
                menuUser()
            }else if(salir == "2") {
                searchBook()
            }else{
                println("Introdujo opción erronéa, esté más atento")
                returnMenu()
            }
        }
    }




}

fun buscarLibroAutor(){
    // A redactar en función de las BD de Libros
    //println("Introduce el Nombre del autor:")
    var busquedaAutor: String = JOptionPane.showInputDialog("Introduce el Nombre del autor:")

    //Mensaje de prueba
    println("Búscando Títulos del author $busquedaAutor")


    for (i in 0 until listBook.size){

        if(busquedaAutor.equals(listBook[i]?.author)){
            println("${listBook[i]?.title}")


        }

    }
    buscarLibroTitulo()




// Aún por desarrollar
}

fun buscarLibroGenero(){
    //println("Introduce el género deseado:")
    var busquedaGenero: String = JOptionPane.showInputDialog("Introduce el género deseado:")
    //Mensaje de prueba
    println("Búscando el Título $busquedaGenero")

    for (i in 0 until listBook.size){
        if(busquedaGenero == listBook[i]!!.genre){

            JOptionPane.showInputDialog(null, "El libro está disponible")
            var buy = JOptionPane.showInputDialog("¿Que desea realizar con el libro? " +
                    "Seleccione una opción.\n " +
                    "1. Comprar en físico\n" +
                    "2. Rentar \n" +
                    "3. Lectura Online\n")
            comprarLibro(buy)
        }else {
            var salir = JOptionPane.showInputDialog("El libro no se encuentró."+
                    "Seleccione una opción.\n " +
                    "1. Volver al Menú\n" +
                    "2. Realizar otra búsqueda\n")
            if(salir == "1"){
                menuUser()
            }else if(salir == "2") {
                searchBook()
            }else{
                println("Introdujo opción erronéa, esté más atento")
                returnMenu()
            }
        }
    }


}






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

