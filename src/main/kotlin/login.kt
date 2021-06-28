import models.Book
import models.User
import models.listBook
import db.createDBBooks
import db.createDBAdmins
import db.listUsr
import db.countUsers

fun start(){
    //Creamos la base de datos de todos los administradores
    createDBAdmins()
    createDBBooks()

    println("-------Bienvenido a nuestro sistema-------")
    println("Eres usuario registatrado? (Y)es or (N)o")
    var isUser: String = readLine()!!

    // Verifico si el usuario está registrado o no
    if(isUser == "Y" || isUser == "y"){

        // Voy a verificar si las credenciasles están correctas

        println("Por favor, Introduzca su correo")
        var email = readLine()!!
        email = verifyCorrectEmail(email)

        println("Por favor, Introduzca su contraseña")
        var password = readLine()!!
        password = verifyCorrectPassword(password)

        //Verifica que sea un usuario registrado
        var loginAnswer = login(email,password)

        //Verifica si es un usuario o un administrador
        when(loginAnswer){
            "admin" -> menuAdmin()
            "user" -> menuUser()
            else -> {
                println("No estas registrado")
                println("Cree su usuario siguiendo las instrucciones:)")
                createUsr()
                menuUser()
            }
        }

    }else if (isUser == "N" || isUser == "n"){
        println("¿Desea crear un usuario? (Y)es or (N)o)")
        var addUser: String = readLine()!!
        if(addUser == "Y" || addUser == "y"){
            createUsr()
            menuUser()

        }else{
            println("Lamentamos su negativa")
        }
    }else {
        println("Error, comience y lea correctamente")
    }

}
fun login(email: String, password: String): String{
    for(i in 0..99){
        if(listUsr[i]?.getTipoCuenta() == "admin" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
            return "admin"
        }else{
            if(listUsr[i]?.getTipoCuenta() == "user" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
                return "user"
            }
        }
    }
    return "isnRegister"
}

fun verifyCorrectEmail(email:String): String {
    var correo = email
    do {
        var correctEmail = false
        correo?.forEach {
            if (it == '@') { correctEmail = true }
        }
        if (!correctEmail) {
            println("El correo no es correcto hace falta el @, ingresa nuevamente el correo.")
            println("\nIngresa email nuevamente"); correo = readLine().toString()
        }
    } while (!correctEmail)
    return correo
}


fun verifyCorrectPassword(password:String):String{
    var pass = password
    do{
        val correctPassword= pass?.length!! >= 8
        if(!correctPassword){
            println("La contraseña al menos debe tener 8 caracteres, ingresa nuevamente la contraseña.")
            println("\nIngrese tu contraseña"); pass = readLine().toString()
        }
    }while(!correctPassword)
    return pass
}

fun createUsr(){

    //Crea un usuario nuevo

    print("Escriba su nombre: ")
    val name= readLine().toString()
    print("Escriba su nombre de usuario: ")
    val userName = readLine().toString()
    print("Escriba su contraseña: ")
    val password = verifyCorrectPassword(readLine()!!)
    print("Escriba su email: ")
    val email = verifyCorrectEmail(readLine()!!)

    println("Bienvenido, ahora eres parte de nuestra comunidad")

    listUsr[countUsers]=User(countUsers+1,name,userName,password,email)

    countUsers++
}

fun selectPreferredGenre(user: User){
    val genre = mutableSetOf<String>()
    val genreSelected = mutableSetOf<String>()
    listBook.forEach {  it?.let { it1 -> genre.add(it1.genre) } }

    do{
        println("Catálogo de Géneros Literarios")
        var i = 1; genre.forEach {  println("$i. $it"); i++ }
        do {
            print("Ingrese el número del género literio preferido o ingrese C para continuar: ")
            val generInput = readLine()
            var out= false
            generInput?.forEach { if(!it.isLetter()) out = true; return@forEach }
            if (out){
                genreSelected.add(genre.elementAt(generInput?.toInt()!!-1))
                println("   Has agregado ${genre.elementAt(generInput?.toInt()!!-1)}")
            }
        }while (out)

        print("\nTus género seleccionados son ")
        genreSelected.forEach { print(" $it, ")}
        print("\nIngrese Y para continuar o cualquier tecla para agregar más géneros: "); val salir = readLine()!="Y"
    }while(salir)
    user.preferredGenre = genreSelected
}

fun recommendByGenerd(user: User){
    val genresPreferd = user.preferredGenre
    val bookByGenresPreferd = mutableListOf<Book>()
    listBook.forEach { val book = it
        genresPreferd.forEach { if (book?.genre == it) bookByGenresPreferd.add(book); return@forEach }
    }

    println()
    println("Títulos que te podrían interesar.")
    for( i in 0 until 5){
        val libroAleatorio = bookByGenresPreferd[(0 until bookByGenresPreferd.size).random()]
        println(" ${i+1}. ${libroAleatorio.title}, ${libroAleatorio.author} ")
    }
}





