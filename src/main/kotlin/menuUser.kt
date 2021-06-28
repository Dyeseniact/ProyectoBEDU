import models.Book
import models.User
import models.listBook
import kotlin.system.exitProcess

fun menuUser(){
    println("""Selecciona una opción 
        |Menu
        |1. Ver favoritos
        |2. Buscar libro
        |3. Mis recomendaciones
        |4. Mis libros comprados
        |5. Cerrar Sesión
        
    """.trimMargin())
    var answerMenu: String = readLine()!!
    when(answerMenu){
        "1" -> {
            topFavoriteBook()
            returnMenu()
        }
        "2" -> {
            searchBook()
            returnMenu()
        }
        "3" -> {
            seachBookGenre()
            returnMenu()
        }
        "4" -> {
            myBooksBought()
            returnMenu()
        }
        "5" -> {
            start()
        }
        else -> {
            println("Opcion incorrecta intente de nuevo.")
            menuUser()
        }
    }
}

fun topFavoriteBook(){
    println("Los libros favoritos son:")
    for(i in 0..99){
        if(listBook[i]?.favorite == true){
            println("Libro: ${listBook[i]?.title}, Autor: ${listBook[i]?.author}")
        }
    }
}

fun searchBook(){
    println("Esta sección esta en proceso...")
}

fun seachBookGenre(){
    var listBookGenre = Array<String>(100){""}
    var countGenre: Int = 0
    print("""
            Escribe el número del genero que te interese:
            1. Terror
            2. Romance
            3. SCI-FI
            4. Historia
        
        """.trimIndent())
    var opcion: Int = readLine()!!.toInt()
    var genre:String=""
    when (opcion){
        1->genre="Terror"
        2->genre="Romance"
        3->genre="Ciencia Ficción"
        4->genre="Historia"
    }
    for(i in 0..99){
        if(genre.equals(listBook[i]?.genre)){
            listBookGenre[countGenre]= listBook[i]!!.title
            countGenre++
        }
    }
    println("Te recomendamos estos libro del genero $genre: ")
    for(i in 0..99){
        if(listBookGenre[i].isNotEmpty()){
            println("Título: ${listBookGenre[i]}")
        }
    }
}

fun myBooksBought(){
    println("Esta sección esta en proceso")
}

fun returnMenu(){
    println("Deseas regresar al menu principal (Y)es:")
    var answer: String = readLine()!!

    if(answer == "Y" || answer == "y"){
        menuUser()
    }else{
        returnMenu()
    }
}