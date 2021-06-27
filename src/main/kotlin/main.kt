import models.User

fun main(args: Array<String>) {
    //Este es mi rama, yo soy Erick
    //comentario en rama erick - genaro
    createDBBooks()
    listUsr[countUsers]= User(countUsers+1,"Genaro2","GenaroBedu2",
        "1234genaro2","genaro@gmail.com2","user2")
    listUsr[countUsers]?.let {
        selectPreferredGenre(it)
        recommendByGenerd(it)
    }
}