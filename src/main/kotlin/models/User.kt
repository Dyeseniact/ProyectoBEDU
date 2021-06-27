package models

var listBook = Array<Book?>(100){null}
var countBook:Int=0
class User(id:Int, nombre:String, userName:String, password:String, private  var email: String, private var tipoCuenta:String="user",
           var preferredGenre: MutableSet<String> = mutableSetOf<String>()) : Person(id, nombre, userName, password) {

    fun getEmail():String = email
    fun getTipoCuenta():String = tipoCuenta
    init {
        if(tipoCuenta.equals("user"))
            println("Bienvenido $nombre, ahora eres parte de nuestra comunidad")
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
        listBook[countBook]=Book(countBook+1,title,author,genre,price,stock)
        countBook++
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
            3->genre="SCI-FI"
            4->genre="Story"
        }
        for(i in 0..99){
            if(genre.equals(listBook[i]?.genre)){
                listBookGenre[countGenre]= listBook[i]!!.title
                countGenre++
            }
        }
        for(i in 0..99){
            if(listBookGenre[i].isNotEmpty()){
                println("Te recomendamos: ")
                println(listBookGenre[i])
            }
        }
    }

}