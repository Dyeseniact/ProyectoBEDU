import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import models.*

fun menuAdmin(){
    println("""Selecciona una opción 
        |Menu Administrador:
        |1. Agregar un libro
        |2. Ver libros
        |3. Ver Revistas
        |4. Ver Articulos
        |5. Cerrar Sesión
        
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
            viewMagazine()
            returnMenuAdmin()
        }
        "4" -> {
            viewArticle()
            returnMenuAdmin()
        }
        "5" -> {
            start()
        }
        else -> {
            println("Opcion incorrecta intente de nuevo")
            returnMenuAdmin()
        }
    }


}
//Funciones de Administrador

fun viewBooks(){
    println("Cargando Libros")

    Thread.sleep(2000)

    for (i in 0..10){
        print(".")
        Thread.sleep(1000)
    }

    println("... listo")

    for(i in 0..39){
        println("${listBook[i]?.id} - ${listBook[i]?.title} - ${listBook[i]?.stock}" )
        Thread.sleep(500)
    }
}

fun viewArticle() = runBlocking{
    launch {
        delay(2000L)
        for (i in 0..10){
            print(".")
            Thread.sleep(500)
        }
        println("... listo")
    }

    launch {
        delay(3000L)
        for(i in 0..17){
            println("${listArticle[i]?.id} - ${listArticle[i]?.title} - ${listArticle[i]?.stock}" )
            Thread.sleep(500)
        }
    }

    println("Cargando Articulos")
}

fun viewMagazine(){
    println("Cargando Revistas")

    Thread.sleep(2000)

    for (i in 0..10){
        print(".")
        Thread.sleep(1000)
    }

    println("... listo")

    for(i in 0..29){
        println("${listMagazine[i]?.id} - ${listMagazine[i]?.title} - ${listMagazine[i]?.stock}" )
        Thread.sleep(500)
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