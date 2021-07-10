import models.*
import javax.swing.JOptionPane

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
            searchProduct()
            returnMenu()
        }
        "3" -> {
            //seachBookGenre()
            recommendByGenerd(userLogin)
            menuUser()
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

//Funciones de Usuarios

fun topFavoriteBook(){
    println("Los libros favoritos son:")
    for(i in 0..99){
        if(listBook[i]?.favorite == true){
            println("Libro: ${listBook[i]?.title}, Autor: ${listBook[i]?.author}")
        }
    }
}

/*fun searchBook(){
    println("Esta sección esta en proceso...")
}*/

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

fun recommendByGenerd(user: User){
    val itOrg =if(user.preferredGenre[0].isNotEmpty()) 0 else if(user.preferredGenre[1].isNotEmpty()) 1 else 2
    var it = itOrg
    var tipo = ""
    var lectura = mutableSetOf<ArrayList<String>>()
    if(user.preferredGenre[0].isNotEmpty() || user.preferredGenre[1].isNotEmpty() || user.preferredGenre[2].isNotEmpty() ){
        do {
            val salir : Boolean ;
            if (user.preferredGenre[it].isNotEmpty()){
                when( it ){
                    0-> { tipo = "Libros   "; lectura = recomiendaLiteratura(user,it) }
                    1-> { tipo = "Revistas "; lectura = recomiendaLiteratura(user,it) }
                    2-> { tipo = "Artículos"; lectura = recomiendaLiteratura(user,it) }

                }

                println("""
                            -------------------------------
                            |                             |
                            | ¿Estás buscando algo nuevo? |
                            |                             |
                            | Los títulos que te podrían  |
                            | interesar son:              |
                            |                             |
                            |      ${tipo}              |
                            |                             |
                            |  1. ${cuentaLetrasYRecorta(lectura.elementAt(0)[0])}|
                            |       ${cuentaLetrasYRecorta(lectura.elementAt(0)[1],22)}|
                            |  2. ${cuentaLetrasYRecorta(lectura.elementAt(1)[0])}|
                            |       ${cuentaLetrasYRecorta(lectura.elementAt(1)[1],22)}|
                            |  3. ${cuentaLetrasYRecorta(lectura.elementAt(2)[0])}|
                            |       ${cuentaLetrasYRecorta(lectura.elementAt(2)[1],22)}|
                            |  4. ${cuentaLetrasYRecorta(lectura.elementAt(3)[0])}|
                            |       ${cuentaLetrasYRecorta(lectura.elementAt(3)[1],22)}|
                            |  5. ${cuentaLetrasYRecorta(lectura.elementAt(4)[0])}|
                            |       ${cuentaLetrasYRecorta(lectura.elementAt(4)[1],22)}|
                            |                  Skip ->    |
                            -------------------------------
                        """.trimIndent())

                when(JOptionPane.showOptionDialog(
                    null ,
                    "Recomendaciones por género",
                    "Recomendaciones por género",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null , arrayOf<Any>("Deslizar pantalla", "Regresar al menú"),  // null para YES, NO y CANCEL
                    null)){
                    0 ->{
                        if(it==2) it=itOrg else it++
                        salir = false
                    }
                    else -> salir=true
                }
            }else{
                if(it==2) it=itOrg else it++
                salir=false
            }
        }while (!salir)
    }else{
        selectPreferredGenre(user)
    }
}

fun cuentaLetrasYRecorta(frase:String, lengStop:Int=24): String {
    if(frase.length>23){
        return frase.substring(0,lengStop-3)+"..."
    }
    else {
        var nuevaFrase:String = frase
        do {
            nuevaFrase += " "
        }while (nuevaFrase.length<lengStop)
        return nuevaFrase
    }
}

fun recomiendaLiteratura( user: User, tipoDeLectura:Int) : MutableSet<ArrayList<String>> {
    val genresPreferdBooks = user.preferredGenre.elementAt(tipoDeLectura)
    when(tipoDeLectura){
        0-> {
            val bookByGenresPreferd = mutableListOf<Book>()
            listBook.forEach { val book = it
                genresPreferdBooks.forEach { if (book?.genre == it) bookByGenresPreferd.add(book); return@forEach }
            }
            val booksRecommend = mutableSetOf<ArrayList<String>>()
            do{
                val libroAleatorio = bookByGenresPreferd[(0 until bookByGenresPreferd.size).random()]
                booksRecommend.add( arrayListOf(libroAleatorio.title,libroAleatorio.author))
            }while(booksRecommend.size < 5)
            return booksRecommend
        }
        1 -> {
            val magazineByGenresPreferd = mutableListOf<Magazine>()
            listMagazine.forEach { val magazine = it
                genresPreferdBooks.forEach { if (magazine?.genre == it) magazineByGenresPreferd.add(magazine); return@forEach }
            }
            val magazineRecommend = mutableSetOf<ArrayList<String>>()
            do{
                val revistaAleatoria = magazineByGenresPreferd[(0 until magazineByGenresPreferd.size).random()]
                magazineRecommend.add( arrayListOf(revistaAleatoria.title,revistaAleatoria.author))
            }while(magazineRecommend.size < 5)
            return magazineRecommend
        }
        2 ->{
            val bookByGenresPreferd = mutableListOf<Article>()
            listArticle.forEach { val article = it
                genresPreferdBooks.forEach { if (article?.genre == it) bookByGenresPreferd.add(article); return@forEach }
            }
            val articlesRecommend = mutableSetOf<ArrayList<String>>()
            do{
                val articuloAleatorio = bookByGenresPreferd[(0 until bookByGenresPreferd.size).random()]
                articlesRecommend.add( arrayListOf(articuloAleatorio.title, articuloAleatorio.author))
            }while(articlesRecommend.size < 5)
            return articlesRecommend
        }
        else -> return mutableSetOf<ArrayList<String>>()
    }
}