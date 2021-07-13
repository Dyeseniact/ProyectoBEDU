import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import models.listArticle
import models.listBook
import models.listMagazine
import javax.swing.JOptionPane


fun searchProduct(){

    val findProduct = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar artículos\n" +
            "2. Buscar libros\n" +
            "3. Buscar Revistas\n" +
            "4. Volver al Menú\n")
    when (findProduct) {
        "1" -> {
            searchArticle()
        }
        "2" -> {
            searchBook()
        }
        "3" -> {
            searchMagazine()
        }
        "4" -> {
            returnMenu()
        }
        else -> {
            //print("Por favor, seleccione un método de búsqueda correcto")
            JOptionPane.showConfirmDialog(null,"Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }
}


fun searchArticle(){
    var findBook = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar por Título\n" +
            "2. Buscar por autores\n" +
            "3. Buscar por género\n" +
            "4. Buscar por año\n" +
            "5. Volver al menú")
    when (findBook) {
        "1" -> {
            searchPaperTitle()
        }
        "2" -> {
            searchPaperAutor()
        }
        "3" -> {
            searchPaperGenre()
        }
        "4" -> {
            searchPaperYear()
        }
        "5" -> {
            returnMenu()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }

}

fun searchMagazine(){
    var findMagazine = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar por Título\n" +
            "2. Buscar por revista\n" +
            "3. Buscar por género\n" +
            "4. Volver al menú")
    when (findMagazine) {
        "1" -> {
            searchMagazineTitle()
        }
        "2" -> {
            searchMagazineAutor()
        }
        "3" -> {
            searchMagazineGenre()
        }
        "4" -> {
            returnMenu()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchMagazine()
        }
    }
}



fun searchBook(){

    var findBook = JOptionPane.showInputDialog("¿Por qué método desea realizar la búsqueda? " +
            "Diga una opción.\n " +
            "1. Buscar por Título\n" +
            "2. Buscar por autores\n" +
            "3. Buscar por género\n" +
            "4. Volver al menú")
    when (findBook) {
        "1" -> {
            searchBookTitle()
        }
        "2" -> {
            searchBookAutor()
        }
        "3" -> {
            searchBookGenreJ()
        }
        "4" -> {
            returnMenu()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }
}

/////////////////////////////////////////////////
// Busqueda por Libros

fun searchBookTitle() {
    // A redactar en función de las BD de Libros
    var listBookTitle = Array<String>(listBook.size) { "" }
    var countTitle: Int = 0
    var busquedaTitulo: String = JOptionPane.showInputDialog("Introduce el Título deseado").toString()
    //Mensaje de prueba
    println("Búscando el Título $busquedaTitulo")
    // Aún por desarrollar

    for (i in 0..99) {
        if (busquedaTitulo.equals(listBook[i]?.title, ignoreCase = true)) {
            listBookTitle[countTitle]= listBook[i]!!.title
            countTitle++
        }

    }


    if (countTitle>0){
        var buy = JOptionPane.showInputDialog(
            "El libro está disponible ¿Que desea realizar? " +
                    "Seleccione una opción.\n " +
                    "1. Comprar en físico\n" +
                    "2. Rentar \n" +
                    "3. Lectura Online\n"
        ).toInt()
        comprarLibro(buy, busquedaTitulo)
    } else{
        var salir = JOptionPane.showInputDialog("El libro no se encuentró."+
                "Seleccione una opción.\n " +
                "1. Volver al Menú\n" +
                "2. Realizar otra búsqueda\n")
        when (salir) {
            "1" -> {
                menuUser()
            }
            "2" -> {
                searchBook()
            }
            else -> {
                println("Introdujo opción erronéa, esté más atento")
                returnMenu()
            }
        }
    }

}

fun searchBookAutor(){
    // A redactar en función de las BD de Libros
    //println("Introduce el Nombre del autor:")
    var listBookAuthor = Array<String>(listBook.size) { "" }
    var countAuthor: Int = 0
    var busquedaAuthor: String = JOptionPane.showInputDialog("Introduce el Nombre del autor:")

    //Mensaje de prueba
    println("Búscando Títulos del author $busquedaAuthor")



    for (i in 0..99) {
        if (busquedaAuthor.equals(listBook[i]?.author, ignoreCase = true)) {
            listBookAuthor[countAuthor]= listBook[i]!!.title
            countAuthor++
        }

    }

    if (countAuthor>0){
        println("Los libros disponibles del autor $busquedaAuthor son:\n:")
        for(i in 0..99){
            if(listBookAuthor[i].isNotEmpty()){
                println("Título: ${listBookAuthor[i]}")
            }
        }

        searchBookTitle()
    }else{
        println("El autor $busquedaAuthor no tiene libros disponibles")}


// Aún por desarrollar
}

fun searchBookGenreJ(){
    var listBookGenre = Array<String>(100){""}
    var countGenre: Int = 0
    var genre: String? = ""
    print("""
            Escribe el número del genero que te interese:
            1. Terror
            2. Romance
            3. SCI-FI
            4. Historia
        
        """.trimIndent())
    var opcion = JOptionPane.showInputDialog("Introduzca el género deseado:\n" +
            "1. Terror\n" +
            "2. Romance\n" +
            "3. Ciencia Ficción\n" +
            "4. Historia").toInt()


    when (opcion){
        1->{
            genre="Terror"
        }
        2->{
            genre="Romance"
        }
        3->{
            genre="Ciencia Ficción"
        }
        4->{
            genre="Historia"
        }
        else->{
            searchBookGenreJ()
        }
    }
    for(i in 0..99){
        if(genre.equals(listBook[i]?.genre)){
            listBookGenre[countGenre]= listBook[i]!!.title
            countGenre++
        }
    }
    if (countGenre>0){
        println("Te recomendamos estos libro del genero $genre: ")
        for(i in 0..99){
            if(listBookGenre[i].isNotEmpty()){
                println("Título: ${listBookGenre[i]} ")
            }
        }
        searchBookTitle()
    }else{
        println("Lamentablemente no contamos con  libros \n del genero $genre: ")
    }





}

///////////////////////////////////////////////////////////////////
// Buscar Articulos
// Many paths, one destination: mapping the movements of a kleptoparasitic spider on the host’s web

fun searchPaperTitle(){
    // A redactar en función de las BD de Libros
    val listPaperTitle = Array<String>(listArticle.size) { "" }
    var countTitle: Int = 0
    val paperTitulo: String = JOptionPane.showInputDialog("Introduce el nombre del artículo:").toString()
    //Mensaje de prueba
    println("Búscando el Título $paperTitulo")
    // Aún por desarrollar

    for (i in 0..99) {
        if (paperTitulo.equals(listArticle[i]?.title, ignoreCase = true)) {
            listPaperTitle[countTitle]= listArticle[i]!!.title
            countTitle++
        }

    }


    if (countTitle>0){
        var buy = JOptionPane.showInputDialog(
            "El libro está disponible ¿Que desea realizar? " +
                    "Seleccione una opción.\n " +
                    "1. Comprar en físico\n" +
                    "2. Lectura Online \n"
        ).toInt()

        if (buy.equals(2)){
            buy=3
        }
        comprarLibro(buy, paperTitulo)
    } else{
        var salir = JOptionPane.showInputDialog("El artículo no se encuentró."+
                "Seleccione una opción.\n " +
                "1. Volver al Menú\n" +
                "2. Realizar otra búsqueda\n")
        when (salir) {
            "1" -> {
                menuUser()
            }
            "2" -> {
                searchBook()
            }
            else -> {
                println("Introdujo opción erronéa, esté más atento")
                returnMenu()
            }
        }
    }

}

fun searchPaperAutor(){
    // A redactar en función de las BD de Libros
    //println("Introduce el Nombre del autor:")
    var listPaperAuthor = Array<String>(listArticle.size) { "" }
    var countAuthor: Int = 0
    var busquedaAuthor: String = JOptionPane.showInputDialog("Introduce el Nombre del autor:")

    //Mensaje de prueba
    println("Búscando Títulos del author $busquedaAuthor")



    for (i in 0..99) {
        if (busquedaAuthor.equals(listArticle[i]?.author, ignoreCase = true)) {
            listPaperAuthor[countAuthor]= listArticle[i]!!.title
            countAuthor++
        }

    }

    if (countAuthor>0){
        println("Los libros disponibles del autor $busquedaAuthor son:\n:")
        for(i in 0..99){
            if(listPaperAuthor[i].isNotEmpty()){
                println("Título: ${listPaperAuthor[i]}")
            }
        }

        searchBookTitle()
    }else{
        println("El autor $busquedaAuthor no tiene libros disponibles")}


// Aún por desarrollar
}

fun searchPaperGenre(){
    var listPaperGenre = Array<String>(listArticle.size){""}
    var countGenre: Int = 0
    var genre: String? = ""

    var opcion = JOptionPane.showInputDialog("Introduzca el género deseado:\n" +
            "1. Artes y Humanidades\n" +
            "2. Ciencias Sociales y Económicas\n" +
            "3. Físico Matemáticas y Ciencias de la Tierra\n" +
            "4. Medicina y Ciencias de la Salud\n" +
            "5. Multidisciplina\n" +
            "6. Otro").toInt()


    when (opcion){
        1->{
            genre="Artes y Humanidades"
        }
        2->{
            genre="Ciencias Sociales y Económicas"
        }
        3->{
            genre="Físico Matemáticas y Ciencias de la Tierra"
        }
        4->{
            genre="Medicina y Ciencias de la Salud"
        }
        5->{
            genre="Multidisciplina"
        }
        6->{
            genre=JOptionPane.showInputDialog("Introduce otro genero").toString()
        }


    }

    for(i in 0..99){
        if(genre.equals(listArticle[i]?.genre, ignoreCase = true)){
            listPaperGenre[countGenre]= listArticle[i]!!.title
            countGenre++
        }
    }

    if (countGenre>0){


        println("Te recomendamos estos libro del genero $genre: ")
        for(i in 0..listPaperGenre.size){
            if(listPaperGenre[i].isNotEmpty()){
                println("Título: ${listPaperGenre[i]} ")
            }
        }
        searchPaperTitle()

    }else{
        println("Lamentablemente no contamos con artículos del género\n" +
                "$genre")
    }




}


fun searchPaperYear(){
    // A redactar en función de las BD de Libros
    //println("Introduce el Nombre del autor:")
    val listPaperYear = Array<String>(listArticle.size) {""}
    var countAuthor: Int = 0
    //val busquedaYear: Int = 0
    var meanYear: Int = 0
    var menorYear: Int = 0
    var mayorYear: Int = 0
    val opcionYear: String = JOptionPane.showInputDialog("Seleccione el periodo de estudio:\n" +
            "1. Año específico\n" +
            "2. Varios años")

    when (opcionYear){
        "1"->{

            meanYear = JOptionPane.showInputDialog("Introduce el año requerido:").toInt()
            for (i in 0..99) {
                if (meanYear == listArticle[i]?.getYearPublication()) {
                    listPaperYear[countAuthor]= listArticle[i]!!.title
                    countAuthor++
                }

            }



        }
        "2"->{
            menorYear = JOptionPane.showInputDialog("Introduce el menor año:").toInt()
            mayorYear = JOptionPane.showInputDialog("Introduce el mayor año:").toInt()

            for (i in 0..99) {
                if (menorYear <= listArticle[i]?.getYearPublication()!!) {
                    if (mayorYear>=listArticle[i]?.getYearPublication()!!){
                        listPaperYear[countAuthor]= listArticle[i]!!.title
                        countAuthor++
                    }

                }

            }
            println("Búscando Títulos del author entre los años $menorYear - $mayorYear")
        }



    }

    //Mensaje de prueba
    if (countAuthor>0){
        println("Los artículos disponibles son:\n:")
        for(i in 0..99){
            if(listPaperYear[i].isNotEmpty()){
                println("Título: ${listPaperYear[i]}")
            }
        }

        searchPaperTitle()
    }else{
        println("No hay artículos disponibles")}


// Aún por desarrollar
}

////////////////////////////////////////////////////////////
fun searchMagazineTitle(){
    // A redactar en función de las BD de Libros
    val listMagazineTitle = Array<String>(listMagazine.size) { "" }
    var countTitle: Int = 0
    val magazineTitulo: String = JOptionPane.showInputDialog("Introduce el nombre de la revista:").toString()
    //Mensaje de prueba
    println("Búscando el Título $magazineTitulo")
    // Aún por desarrollar

    for (i in 0..99) {
        if (magazineTitulo.equals(listMagazine[i]?.title, ignoreCase = true)) {
            listMagazineTitle[countTitle]= listMagazine[i]!!.title
            countTitle++
        }

    }


    if (countTitle>0){
        var buy = JOptionPane.showInputDialog(
            "La revista está disponible ¿Que desea realizar? " +
                    "Seleccione una opción.\n " +
                    "1. Comprar en físico\n" +
                    "2. Lectura Online \n"
        ).toInt()

        if (buy.equals(2)){
            buy=3
        }
        comprarLibro(buy, magazineTitulo)
    } else{
        var salir = JOptionPane.showInputDialog("El artículo no se encuentró."+
                "Seleccione una opción.\n " +
                "1. Volver al Menú\n" +
                "2. Realizar otra búsqueda\n")
        when (salir) {
            "1" -> {
                menuUser()
            }
            "2" -> {
                searchBook()
            }
            else -> {
                println("Introdujo opción erronéa, esté más atento")
                returnMenu()
            }
        }
    }
}

fun searchMagazineAutor(){
    var listMagazineAuthor = Array<String>(listMagazine.size) { "" }
    var countAuthor: Int = 0
    var busquedaAuthor: String = JOptionPane.showInputDialog("Introduce el Nombre del autor:")

    //Mensaje de prueba
    println("Búscando revista del author $busquedaAuthor")



    for (i in 0..99) {
        if (busquedaAuthor.equals(listMagazine[i]?.author, ignoreCase = true)) {
            listMagazineAuthor[countAuthor]= listMagazine[i]!!.title
            countAuthor++
        }

    }

    if (countAuthor>0){
        println("Las revistas disponibles del autor $busquedaAuthor son:\n:")
        for(i in 0..99){
            if(listMagazineAuthor[i].isNotEmpty()){
                println("Título: ${listMagazineAuthor[i]}")
            }
        }

        searchBookTitle()
    }else{
        println("El autor $busquedaAuthor no tiene revistas disponibles")}


}

fun searchMagazineGenre(){
    var listMagazineGenre = Array<String>(listMagazine.size){""}
    var countGenre: Int = 0
    var genre: String? = ""

    var opcion = JOptionPane.showInputDialog("Introduzca el género deseado:\n" +
            "1. Artes y Humanidades\n" +
            "2. Ciencias Sociales y Económicas\n" +
            "3. Físico Matemáticas y Ciencias de la Tierra\n" +
            "4. Medicina y Ciencias de la Salud\n" +
            "5. Multidisciplina\n" +
            "6. Otro").toInt()


    when (opcion){
        1->{
            genre="Artes y Humanidades"
        }
        2->{
            genre="Ciencias Sociales y Económicas"
        }
        3->{
            genre="Físico Matemáticas y Ciencias de la Tierra"
        }
        4->{
            genre="Medicina y Ciencias de la Salud"
        }
        5->{
            genre="Multidisciplina"
        }
        6->{
            genre=JOptionPane.showInputDialog("Introduce otro genero").toString()
        }


    }

    for(i in 0..99){
        if(genre.equals(listMagazine[i]?.genre, ignoreCase = true)){
            listMagazineGenre[countGenre]= listMagazine[i]!!.title
            countGenre++
        }
    }

    if (countGenre>0){


        println("Te recomendamos las siguientes revistas del genero $genre: ")
        for(i in 0..99){
            if(listMagazineGenre[i].isNotEmpty()){
                println("Título: ${listMagazineGenre[i]} ")
            }
        }
        searchMagazineTitle()

    }else{
        println("Lamentablemente no contamos con revistas del género\n" +
                "$genre")
    }




}


///////////////////////////////////////////////////////////////////////
fun comprarLibro(opt: Int, title: String){
    when (opt) {
        1 -> {
            comprar()
        }
        2-> {
            alquilar()
        }
        3 -> {
            leerOnline()
        }
        else -> {
            print("Por favor, seleccione un método de búsqueda correcto")
            searchBook()
        }
    }




}




fun comprar(){
 /*   println("Proceda a pagar su compra")
    val buyBook = launch{
        var percentaje = 0

        while (percentaje<100){
            delay( 100)
            percentaje+=10
        }

    }
*/

    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}



fun alquilar(){
    println("Proceda a pagar el alquiler de su libro")

    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}

fun leerOnline(){
    println("Proceda a pagar su libro de lectura")

    // Llamar la función carrito: Agregar al carrito, proceder al pago.
}

