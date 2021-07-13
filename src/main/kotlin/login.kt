import db.*
import models.*
import java.lang.Exception
import javax.swing.JOptionPane

lateinit var userLogin: User

fun createDB(){
    //Creamos la base de datos de todos los administradores
    createDBAdmins()
    createDBBooks()
    createDBArticle()
    createDBMagazine()

}

var runProgram = true
fun start(){
    do{
        println("""
                            -------------------------------
                            |                             |
                            |       -----------|          |
                            |      |             |        |
                            |      |               |      |
                            |      |               |      |
                            |      |             |        |
                            |      |           |          |
                            |      |             |        |
                            |      |               |      |
                            |      |                 |    |
                            |      |                  |   |
                            |      |                   |  |
                            |      |                    | |
                            |      |                   |  |
                            |      |                  |   |
                            |      |----------------|     |
                            |                             |
                            |       Iniciar sesión        |
                            |        Crear usuario        |
                            -------------------------------
                        """.trimIndent())

        val selection = JOptionPane.showOptionDialog(
            null ,
            "¿Es un usuario registrado?",
            "Login",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null , arrayOf<Any>("Si", "No", "Salir"),  // null para YES, NO y CANCEL
            null)

        // Verifico si el usuario está registrado o no
        if(selection == 0){

            // Voy a verificar si las credenciasles están correctas

            var email = returnResponseFromCheckboxNotNull("Por favor, Introduzca su correo")
            email = verifyCorrectEmail(email)

            var password = returnResponseFromCheckboxNotNull("Por favor, Introduzca su contraseña")
            password = verifyCorrectPassword(password)

            //Verifica que sea un usuario registrado
            var loginAnswer = login(email,password)

            //Verifica si es un usuario o un administrador
            when(loginAnswer){
                "admin" -> { menuAdmin(); runProgram = false }
                "user" -> { menuUser(); runProgram = false }
                "userWrongPassword" -> do {
                    var out : Boolean = false
                    val selection = JOptionPane.showOptionDialog(
                        null ,
                        "Hola, parece que has olvidado tu contraseña",
                        "Contraseña incorrecta",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null , arrayOf<Any>("Reintentar", "Iniciar sesión con otra cuenta", "Recuperar contraseña"),  // null para YES, NO y CANCEL
                        null)
                    when(selection){
                        0 -> {
                            password = verifyCorrectPassword(
                                returnResponseFromCheckboxNotNull("Por favor, Introduzca su contraseña nuevamente")
                            )
                            if ( login(email,password) == "user" ) out = true; menuUser();
                        }
                        2 -> {
                            JOptionPane.showMessageDialog(null,
                                "Hemos enviado un correo con los pasos necesario para recuperar tu contraseña." )
                        }
                    }
                }while (!out)
                else -> {
                    JOptionPane.showMessageDialog(null, "No estas registrado" )
                    createUserQuestion()
                }
            }

        }else if (selection == 1){
            createUserQuestion()
        }else {
            JOptionPane.showMessageDialog(null, "Adios :(" )
            runProgram = false
        }
    }while (runProgram)

}
fun login(email: String, password: String): String{
    for(i in 0..99){
        if(listUsr[i]?.getAccount() == "admin" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
            userLogin = listUsr[i]!!
            return "admin"
        }else{
            if(listUsr[i]?.getAccount() == "user" && listUsr[i]?.getEmail() == email){
                if( listUsr[i]?.getPassword() == password){
                    userLogin = listUsr[i]!!
                    return "user"
                }else{
                    return "userWrongPassword"
                }
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
            JOptionPane.showMessageDialog(null,
                "El correo no es correcto hace falta el @, ingresa nuevamente el correo." )
            correo = returnResponseFromCheckboxNotNull("Ingresa email nuevamente")
        }
    } while (!correctEmail)
    return correo
}


fun verifyCorrectPassword(password:String):String{
    var pass = password
    do{
        val correctPassword= pass?.length!! >= 8
        if(!correctPassword){
            JOptionPane.showMessageDialog(null,
                "La contraseña al menos debe tener 8 caracteres, ingresa nuevamente la contraseña." )
            pass = returnResponseFromCheckboxNotNull("Ingrese tu contraseña")
        }
    }while(!correctPassword)
    return pass
}

fun createUserQuestion(){
    val selection = JOptionPane.showOptionDialog(
        null ,
        "¿Desea crear un usuario?",
        "Creando ususario",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null , arrayOf<Any>("Si", "No"),  // null para YES, NO y CANCEL
        null)
    if(selection == 0){
        createUsr()
        menuUser()
    }else{
        JOptionPane.showMessageDialog(null,
            "Lamentamos su negativa")
    }
}

fun createUsr(){

    //Crea un usuario nuevo

    println("""
                            -------------------------------
                            |                             |
                            |    Este es el primer paso   |
                            |    para aprender más        |
                            |                             |
                            |    Favor de ingresar los    |
                            |    siguientes datos.        |
                            |                             |
                            |    Nombre:______________    |
                            |                             |
                            |    Usuario:_____________    |
                            |                             |
                            |    Contraseña:__________    |
                            |                             |
                            |    Email:_______________    |
                            |                             |
                            |    Número:______________    |
                            |                             |
                            |                             |
                            |                             |
                            -------------------------------
                        """.trimIndent())

    val name= returnResponseFromCheckboxNotNull("Favor de ingresar su nombre")
    val userName = returnResponseFromCheckboxNotNull("Favor de ingresar su user name")
    val password = verifyCorrectPassword(returnResponseFromCheckboxNotNull("Favor de ingresar su contraseña"))
    val email = verifyCorrectEmail(returnResponseFromCheckboxNotNull("Favor de ingresar su correo"))

    do{
        var out: Boolean=false
        val num = returnResponseFromCheckboxNotNull("Favor de ingresar número")
        try {
            val numberPhone = num.toLong()
            if(numberPhone<1000000000){
                JOptionPane.showMessageDialog(null,
                    "El número es menor a 10 dígitos" )
            }else{
                out=true
            }
        }catch (e: Exception){
            JOptionPane.showMessageDialog(null,
                "El número no es correcto, verifique" )
        }
    }while(!out)

    println("""
                            -------------------------------
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |     B i e n v e n i d o     |
                            |                             |
                            |                             |
                            |     Ahora eres parte de     |
                            |                             |
                            |     nuestra comunidad       |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            |                             |
                            -------------------------------
                        """.trimIndent())

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

        var response: String?
        do{
            response = when(returnResponseFromCheckboxNotNull("¿Qué te gusta leer?\n 1.Libros\n 2.Revistas\n 3.Artículos \n \n 0.Skip \n" +
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
        }while( response==null )

        response.toCharArray().map {
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
                    genreSelected = genredSelect(genreSelected,allGenres,0, arrayListOf(1,2,3,4,0))
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
                    genreSelected = genredSelect(genreSelected,allGenres,1, arrayListOf(1,2,3,4,5,6,7,0))
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
                    genreSelected = genredSelect(genreSelected,allGenres,2, arrayListOf(1,2,3,0))
                }
            }
        }

        val getOut = when(JOptionPane.showOptionDialog(
            null ,
            "Muchas gracias por seleccionar los géneros preferidos",
            "Mi lectura favorita",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null , arrayOf<Any>("Continuar", "Volver a seleccionar mis géneros"),  // null para YES, NO y CANCEL
            null)){
            1 -> true
            else -> false
        }
    }while(getOut)
    user?.preferredGenre = genreSelected
}

fun returnResponseFromCheckboxNotNull(mensaje:String):String{
    //Esta función permite obtener una respuesta que no sea nula
    var response:String?
    do { response = JOptionPane.showInputDialog(mensaje)
    }while (response==null)
    return response
}

fun genredSelect(genreSelected: ArrayList<MutableSet<String>>,
                 allGenres: ArrayList<MutableSet<String>>, tipo:Int,
                 optionslist:ArrayList<Int>): ArrayList<MutableSet<String>> {
    var response: String
    do{
        response = returnResponseFromCheckboxNotNull("Ingresa tus géneros preferidos \n" +
                " 0.Skip \n" +
                "\n Nota: En caso de que sean más de uno ingresar los números juntos en orden ")
        var viable = false
        response?.forEach { char ->
            optionslist.map { int ->
                viable = char.toString().toInt() == int; if(viable) return@forEach
            }
        }
    }while( viable==false )

    if(response!!.toInt() != 0){
        response!!.forEach { valor ->
            genreSelected[tipo].add(allGenres[tipo].elementAt(valor.toString().toInt()-1))
        }
        return genreSelected
    }else{
        return genreSelected
    }
}






