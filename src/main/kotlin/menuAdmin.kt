import models.Book
import models.countBook
import models.listBook

fun menuAdmin(){
    println("""Selecciona una opción 
        |Menu Administrador:
        |1. Agregar un libro
        |2. Ver libros
        |3. Cerrar Sesión
        
    """.trimMargin())
    var answerMenu: String = readLine()!!

    when(answerMenu){
        "1" -> {
            addBook()
            returnMenuAdmin()
        }
        "2" -> {
            viewBooks()
            returnMenuAdmin()
        }
        "3" -> {
            start()
        }
        else -> {
            println("Opcion incorrecta intente de nuevo")
            returnMenuAdmin()
        }
    }


}
fun viewBooks(){
    for(i in 0..39){
        println("${listBook[i]?.id} - ${listBook[i]?.title}" )
    }
}

fun addBook(){
    println("Ingresa el titulo del libro")
    var title: String = readLine()!!
    println("Ingresa el autor")
    var author: String = readLine()!!
    println("Ingresa el genero literario")
    var genre: String = readLine()!!
    println("Ingresa el precio del libro")
    var price: Double = readLine()!!.toDouble()
    println("Ingresa el número de libros disponibles")
    var stock: Int = readLine()!!.toInt()
    listBook[countBook]= Book(countBook +1,title,author,genre,price,stock)
    countBook++
    println("Libro registrado con exito")
}

fun returnMenuAdmin(){
    println("Deseas regresar al menu principal (Y)es:")
    var answer: String = readLine()!!

    if(answer == "Y" || answer == "y"){
        menuAdmin()
    }else{
        returnMenuAdmin()
    }
}