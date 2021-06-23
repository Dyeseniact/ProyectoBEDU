var listUsr = Array<User?>(100){null}
var countUsers = 0
var cuentaEncontrada=""
abstract class Persona(private val id:Int, private var name: String, private var userName: String, private var password:String) {
    fun getId():Int = id
    fun getName():String = name
    fun getUserName():String = userName
    fun getPassword():String = password
}
class User(id:Int, nombre:String, userName:String, password:String,private  var email: String,private var tipoCuenta:String="user"): Persona(id, nombre, userName, password) {
    fun getEmail():String = email
    fun getTipoCuenta():String = tipoCuenta
}