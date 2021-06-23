//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////// Aquí van las funciones ////////////////////////////////////////////////

fun inicio(){
    //Creamos la base de datos de todos los administradores
    createDBAdmins()

    // Verifico si el usuario está registrado o no
    println("Eres usuario registatrado? (Y)es or (N)o")
    var isUser: String = readLine()!!
    if(isUser == "Y"){

        // Voy a verificar si las credenciasles están correctas

        println("Por favor, Introduzca su nombre de usuario")
        var email = readLine()!!
        email = verifyCorrectEmail(email)

        println("Por favor, Introduzca su contraseña")
        var password = readLine()!!
        password = verifyCorrectPassword(password)

        if ( isAdmind(email,password) == true) {
            println("Estás loggeado, eres Administrador")

            // función menú




        }else if(isUser(email,password) == true){
            print("Estás loggeado, eres Usuario")

            // función menú



        }else {
            println("No estas registrado")
            println("Cree su usuario siguiendo las instrucciones:)")

            createUsr()

            // función menú Nuevo usuario


        }
    }else if (isUser == "N"){
        println("¿Desea crear un usuario? (Y)es or (N)o)")
        var addUser: String = readLine()!!
        if(addUser == "Y"){

            createUsr()

            // función menú Nuevo usuario

        }else{
            println("¿Lamentamos su negativa")
        }
    }else {
        println("Error, comience y lea correctamente")
    }

}

fun isAdmind(email: String, password: String): Boolean {
    for(i in 0..99){
        if(listUsr[i]?.getTipoCuenta() == "admin" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
            return true
        }
    }
    return false
}
fun isUser(email: String, password: String): Boolean {
    for(i in 0..99){
        if(listUsr[i]?.getTipoCuenta() == "user" && listUsr[i]?.getEmail() == email && listUsr[i]?.getPassword() == password){
            return true
        }
    }
    return false
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


fun createDBAdmins(){

    //Crea una lista con 4 administradores que están predeterminados

    listUsr[countUsers]=User(countUsers+1,"Erick","ErickBedu",
        "1234erick","erick@gmail.com","admin")
    countUsers++
    listUsr[countUsers]=User(countUsers+1,"Yess","YessBedu",
        "1234yess","yess@gmail.com","admin")
    countUsers++
    listUsr[countUsers]=User(countUsers+1,"Janner","JannerBedu",
        "1234janner","janner@gmail.com","user")
    countUsers++
    listUsr[countUsers]=User(countUsers+1,"Genaro","GenaroBedu",
        "1234genaro","genaro@gmail.com","user")
    countUsers++
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

