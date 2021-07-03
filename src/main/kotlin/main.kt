import models.User
import db.createDBBooks
import db.listUsr
import models.listArticle
import models.listBook
import models.listMagazine

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

    start()
    println(listUsr[1]!!.getName())
            println(listBook[1]!!.title)
            println(listMagazine[1]!!.title)
            println(listArticle[1]!!.title)

}