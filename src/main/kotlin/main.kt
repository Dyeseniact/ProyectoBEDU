import kotlinx.coroutines.delay
import models.*
import kotlin.concurrent.thread

/*
    NOTA:
    Si se quiere hacer una prueba con un usuario registrado usar las siguientes credenciales:
    EMAIL: genaro@gmail.com
    PASSWORD: 1234genaro
    Si se quiere hacer una pruba con un Administrador registrado usar las siguientes credenciales:
    EMAIL: yess@gmail.com
    PASSWORD: 1234yess
*/

fun main(args: Array<String>) {
    createDB()
    start()
    Thread.sleep(6100)
    println("${listBook[1]?.infoTitleAutorPrice()}")


  }