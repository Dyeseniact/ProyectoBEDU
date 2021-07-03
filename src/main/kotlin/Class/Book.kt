package Class

var NumberIdStock: Int = 0


class Libro(idStock:String, tipoProducto:String, precio: Float, var titulo: String, var autor: String, var genero:String, var year: Int, inStock: Int): Product(idStock,
    tipoProducto, precio, inStock){


    /* Métodos requeridos:
     1. Add books al Stock
     2. Eliminar Books del stocks
     3.
     */

 /*    fun getidStock(){

    }*/

    /*fun gettipoProducto(){
        return null
    }*/

   // fun getprecio(): Float = precio
   // fun getinStock(): Int = inStock

    fun addBook() {

    }

    fun deleteBook(){

    }

}