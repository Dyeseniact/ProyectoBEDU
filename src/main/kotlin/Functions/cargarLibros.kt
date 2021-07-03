package Functions

import Class.Libro


//Esta funcion es solo para cargar libros genericos
fun cargaLibros(numBook: Int){
    var Lib=Array<Libro?>(numBook){null}
    for (i in 0 until numBook){
        var idStock = "Book + ${numBook}"
        var tipoProducto = "Libro"
        var precio = (0..20).random().toFloat()
        val titulo = "Titulo+${numBook}"
        val autor = "Autor+${numBook}"
        val genero = "Genero + ${numBook}"
        val year = (1800..2021).random()
        var inStock = (0..20).random()
        Lib[i] = Libro(idStock,tipoProducto,precio,titulo,autor,genero,year,inStock)


    }

    /*var NumberIdStock = Libro("Book + $NumberIdStock","Libro",10.4f,"La barca","Janner",
        "Terror",2021,10)*/

}