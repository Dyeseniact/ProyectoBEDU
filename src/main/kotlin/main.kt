import models.User
import db.createDBBooks
import db.listUsr
import models.listArticle
import models.listBook
import models.listMagazine
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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
    println("Inicio del programa")
    createDB()
    start()
    GlobalScope.launch {
        Thread.sleep(1000)
        println("Delay de un segundo")
    }

    println("fin del programa")



}