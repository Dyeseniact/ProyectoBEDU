import models.*
import javax.swing.JOptionPane

fun menuUser(){
    println("""Selecciona una opción 
        |Menu
        |1. Ver favoritos
        |2. Buscar libro
        |3. Mis recomendaciones
        |4. Mis compras
        |5. Cerrar Sesión
        
    """.trimMargin())

    println("""
                            -------------------------------
                            |               Cerrar sesión |
                            |                             |
                            |   |\  /| |-- |\  | |  |     |
                            |   | \/ | |__ | \ | |  |     |
                            |   |    | |__ |  \| |__|     |
                            |                             |
                            |                             |
                            |  F A V O R I T O S          |
                            |                             |
                            |  B U S C A R                |
                            |                             |
                            |  R E C O M E N D A C I Ó N  |
                            |                             |
                            |  M I S   C O M P R A S      |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            -------------------------------
                        """.trimIndent())
    var answerMenu = JOptionPane.showOptionDialog(
        null ,
        "Selecciona una opción",
        "Ventana principal",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null , arrayOf<Any>("Favoritos", "Buscar", "Recomendaciones", "Mis compras", "Cerrar sesión"),  // null para YES, NO y CANCEL
        null)

    when(answerMenu){
        0 -> {
            topFavorite()
            returnMenu()
        }
        1 -> {
            searchProduct()
            returnMenu()
        }
        2 -> {
            recommendByGenerd(userLogin)
            menuUser()
        }
        3 -> {
            myProductsBought()
            returnMenu()
        }
        4 -> {
            JOptionPane.showMessageDialog(null, "Te esperamos pronto de regreos :D" )
            runProgram = false
        }
        else -> {
            println("Opcion incorrecta intente de nuevo.")
            menuUser()
        }
    }
}

//Funciones de Usuarios

fun topFavorite(){
    println("Los libros favoritos son:")
    for(i in 0..99){
        if(listBook[i]?.favorite == true){
            println("Libro: ${listBook[i]?.title}, Autor: ${listBook[i]?.author}")
        }
    }
    println("Las revistas favoritas son:")
    for(i in 0..99){
        if(listMagazine[i]?.favorite == true){
            println("Revistas: ${listMagazine[i]?.title}, Autor: ${listMagazine[i]?.author}")
        }
    }
    println("Los artículos favoritos son:")
    for(i in 0..99){
        if(listArticle[i]?.favorite == true){
            println("Artículos: ${listArticle[i]?.title}, Autor: ${listArticle[i]?.author}")
        }
    }
}


fun myProductsBought(){

    var book = 0
    var magazine = 0
    var article = 0
    println("Mis compras:")

    for(i in 0..2){
        book = (0..39).random()
        magazine = (0..29).random()
        article = (0..17).random()

        println("* Libro:  ${listBook[book]?.title} ")
        println("* Revista: ${listMagazine[magazine]?.title}")
        println("* Articulo: ${listArticle[article]?.title}")
    }


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
    var type = ""
    var literature = mutableSetOf<ArrayList<String>>()
    if(user.preferredGenre[0].isNotEmpty() || user.preferredGenre[1].isNotEmpty() || user.preferredGenre[2].isNotEmpty() ){
        do {
            val getOutLoop : Boolean ;
            if (user.preferredGenre[it].isNotEmpty()){
                when( it ){
                    0-> { type = "Libros   "; literature = literatureRecommend(user,it) }
                    1-> { type = "Revistas "; literature = literatureRecommend(user,it) }
                    2-> { type = "Artículos"; literature = literatureRecommend(user,it) }

                }

                println("""
                            -------------------------------
                            |                             |
                            | ¿Estás buscando algo nuevo? |
                            |                             |
                            | Los títulos que te podrían  |
                            | interesar son:              |
                            |                             |
                            |      ${type}              |
                            |                             |
                            |  1. ${countLetterAndRemakeString(literature.elementAt(0)[0])}|
                            |       ${countLetterAndRemakeString(literature.elementAt(0)[1],22)}|
                            |  2. ${countLetterAndRemakeString(literature.elementAt(1)[0])}|
                            |       ${countLetterAndRemakeString(literature.elementAt(1)[1],22)}|
                            |  3. ${countLetterAndRemakeString(literature.elementAt(2)[0])}|
                            |       ${countLetterAndRemakeString(literature.elementAt(2)[1],22)}|
                            |  4. ${countLetterAndRemakeString(literature.elementAt(3)[0])}|
                            |       ${countLetterAndRemakeString(literature.elementAt(3)[1],22)}|
                            |  5. ${countLetterAndRemakeString(literature.elementAt(4)[0])}|
                            |       ${countLetterAndRemakeString(literature.elementAt(4)[1],22)}|
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
                        getOutLoop = false
                    }
                    else -> getOutLoop=true
                }
            }else{
                if(it==2) it=itOrg else it++
                getOutLoop=false
            }
        }while (!getOutLoop)
    }else{
        selectPreferredGenre(user)
    }
}

fun countLetterAndRemakeString(sentence:String, lengStop:Int=24): String {
    if(sentence.length>23){
        return sentence.substring(0,lengStop-3)+"..."
    }
    else {
        var newSentence:String = sentence
        do {
            newSentence += " "
        }while (newSentence.length<lengStop)
        return newSentence
    }
}

fun literatureRecommend(user: User, literatureType:Int) : MutableSet<ArrayList<String>> {
    val genresPreferdBooks = user.preferredGenre.elementAt(literatureType)
    when(literatureType){
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

