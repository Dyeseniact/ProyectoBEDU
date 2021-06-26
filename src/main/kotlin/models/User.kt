package models

var listUsr = Array<User?>(100){null}
var countUsers = 0
var cuentaEncontrada=""

class User(id:Int, nombre:String, userName:String, password:String,private  var email: String,private var tipoCuenta:String="user"): Person(id, nombre, userName, password) {
    fun getEmail():String = email
    fun getTipoCuenta():String = tipoCuenta
}