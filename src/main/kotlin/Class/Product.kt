package Class

abstract class Product(private var idStock: String, private var tipoProducto: String, var precio: Float, private var inStock: Int){
    fun getidStock(): String = idStock
    fun gettipoProducto(): String = tipoProducto
    fun getprecio(): Float = precio
    fun getinStock(): Int = inStock




   // fun addStock(): Int =         Agregar funcion de agregar producto al stock y eliminar
}