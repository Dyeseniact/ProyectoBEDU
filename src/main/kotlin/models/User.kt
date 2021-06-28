package models

var listBook = Array<Book?>(100){null}
var countBook:Int=0
class User(id:Int, nombre:String, userName:String, password:String, private  var email: String, private var tipoCuenta:String="user",
           var preferredGenre: MutableSet<String> = mutableSetOf<String>()) : Person(id, nombre, userName, password) {

    fun getEmail():String = email
    fun getTipoCuenta():String = tipoCuenta

}