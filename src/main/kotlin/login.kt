import db.*
import models.*
import javax.swing.JOptionPane

lateinit var userLogin: User

fun createDB(){
    //Creamos la base de datos de todos los administradores
    createDBAdmins()
    createDBBooks()
    createDBArticle()
    createDBMagazine()

}

fun start(){
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
            userLogin = listUsr[i]!!
            return "admin"
        }else{
            if(listUsr[i]?.getTipoCuenta() == "user" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
                userLogin = listUsr[i]!!
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

    listUsr[countUsers]?.let { selectPreferredGenre(it) }

    userLogin = listUsr[countUsers]!!

    countUsers++
}

fun selectPreferredGenre(user: User?=null){
    val genres = fun(books : Array<Book?>?, magazines: Array<Magazine?>?, articles :Array<Article?>? ) : MutableSet<String> {
        val genre = mutableSetOf<String>()
        books?.map { it?.let { it1 -> genre.add(it1.genre) } }
        magazines?.map { it?.let { it1 -> genre.add(it1.genre) } }
        articles?.map { it?.let { it1 -> genre.add(it1.genre) } }
        return genre
    }

    val allGenres = arrayListOf( genres(listBook,null,null),genres(null, listMagazine,null),genres(null,null, listArticle) )

    var genreSelected = arrayListOf<MutableSet<String>>()
    genreSelected.add(mutableSetOf())
    genreSelected.add(mutableSetOf())
    genreSelected.add(mutableSetOf())

    do{
        println("""
            ------------------------------
            |                             |
            |  Hola, en esta plataforma   |
            |  tenemos una gran variedad  |
            |  de obras que te podrían    |
            |  interesar, para mostrarte  |
            |  recomendaricones por favor |
            |  selecciona que tipo de     |
            |  lecturas prefieres.        |
            |                             |
            |  ----------   -----------   |
            |  | Libros |   | Revistas |  |
            |  ----------   -----------   |
            |        ------------         |
            |        | Artículos |        |
            |        ------------         |
            |                             |
            |                             |
            |                    SKIP->   |
            |                             |
            -------------------------------
        """.trimIndent()
        )

        var respuesta: String?
        do{
            respuesta = when(retornaRespuesta("¿Qué te gusta leer?\n 1.Libros\n 2.Revistas\n 3.Artículos \n \n 0.Skip \n" +
                    "\n Nota: En caso de que sean más de uno ingresar los números juntos")){
                "1" -> "1"
                "2" -> "2"
                "3" -> "3"
                "0" -> "0"
                "12" -> "12"
                "123" -> "123"
                "23" ->"23"
                "13" ->"13"
                else -> null
            }
        }while( respuesta==null )

        respuesta.toCharArray().map {
            when( it ){
                '1' ->{
                    println("""
                            -------------------------------
                            |  Constantemente estaremos   |
                            |  agregando más libros.      |
                            |                             |
                            |  Los géneros disponible son |
                            |                             |
                            |   * Libros *                |
                            |                             |
                            |  1.Terror                   |
                            |                             |
                            |  2.Ciencia Ficción          |
                            |                             |
                            |  3.Romance                  |
                            |                             |
                            |  4.Historia                 |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                  Skip ->    |
                            -------------------------------
                        """.trimIndent())
                    genreSelected = seleccionaLosGéneros(genreSelected,allGenres,0, arrayListOf(1,2,3,4,0))
                }
                '2' ->{
                    println("""
                            -------------------------------
                            |  Constantemente estaremos   |
                            |  agregando más revistas.    |
                            |                             |
                            |  Los géneros disponible son |
                            |                             |
                            |    * Revistas *             |
                            |                             |
                            | 1.Multidisciplina           |
                            | 2.Artes y Humanidades       |
                            | 3.Ciencias Sociales y       |
                            |     Económicas              |
                            | 4.Físico Matemáticas y      |
                            |     Ciencias de la Tierra   |
                            | 5.Medicina y Ciencias de la |
                            |     Salud                   |
                            | 6.Biología                  |
                            | 7.Química                   |
                            |                             |
                            |                  Skip ->    |
                            -------------------------------
                        """.trimIndent())
                    genreSelected = seleccionaLosGéneros(genreSelected,allGenres,1, arrayListOf(1,2,3,4,5,6,7,0))
                }
                '3' ->{
                    println("""
                            -------------------------------
                            |  Constantemente estaremos   |
                            |  agregando más artículos.   |
                            |                             |
                            |  Los géneros disponible son |
                            |                             |
                            |   * Artículos *             |
                            |                             |
                            |  1.Biologic                 |
                            |                             |
                            |  2.Medical                  |
                            |                             |
                            |  3.Science                  |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                  Skip ->    |
                            -------------------------------
                        """.trimIndent())
                    genreSelected = seleccionaLosGéneros(genreSelected,allGenres,2, arrayListOf(1,2,3,0))
                }
            }
        }

        val salir = when(retornaRespuesta("Muchas gracias por seleccionar los géneros preferidos \n 0. Continuar \n 1. Regresar")){
            "1" -> true
            else -> false
        }
    }while(salir)
    user?.preferredGenre = genreSelected
}

fun retornaRespuesta(mensaje:String):String{
    //Esta función permite obtener una respuesta que no sea nula
    var respuesta:String?
    do { respuesta = JOptionPane.showInputDialog(mensaje)
    }while (respuesta==null)
    return respuesta
}

fun seleccionaLosGéneros(genreSelected: ArrayList<MutableSet<String>>,
                         allGenres: ArrayList<MutableSet<String>>, tipo:Int,
                         listaOpciones:ArrayList<Int>): ArrayList<MutableSet<String>> {
    var respuesta: String
    do{
        respuesta = retornaRespuesta("Ingresa tus géneros preferidos \n" +
                " 0.Skip \n" +
                "\n Nota: En caso de que sean más de uno ingresar los números juntos en orden ")
        var viable = false
        respuesta?.forEach { char ->
            listaOpciones.map { int ->
                viable = char.toString().toInt() == int; if(viable) return@forEach
            }
        }
    }while( viable==false )

    if(respuesta!!.toInt() != 0){
        respuesta!!.forEach {valor ->
            genreSelected[tipo].add(allGenres[tipo].elementAt(valor.toString().toInt()-1))
        }
        return genreSelected
    }else{
        return genreSelected
    }
}






