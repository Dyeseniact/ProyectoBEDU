import models.Book
import models.User
import models.countBook
import models.listBook
import javax.swing.text.StyledEditorKit

//////////////////////////////////////////////////////////////////////////////////////////////
////////////////////// Aquí van las funciones ////////////////////////////////////////////////
var listUsr = Array<User?>(100){null}
var countUsers = 0
var cuentaEncontrada=""
fun inicio(){
    //Creamos la base de datos de todos los administradores
    createDBAdmins()
    createDBBooks()

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
var cambio:Boolean=false
    listUsr[countUsers]= User(countUsers+1,"Erick","ErickBedu",
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

fun createDBBooks(){
    listBook[countBook]= Book(countBook +1,"Drácula","Bram Stoker","Terror",450.24,10,false,0)
    listBook[countBook]= Book(countBook +1,"Frankesnstein","Mary Shelley","Terror",350.15,10,true,0)
    listBook[countBook]= Book(countBook +1,"Misery","Stephen King","Terror",420.3,10,true,0)
    listBook[countBook]= Book(countBook +1,"Carrie","Stephen King","Terror",350.5,10,false,0)
    listBook[countBook]= Book(countBook +1,"El gato Negro","Edgar Allan Poe","Terror",420.2,10,true,0)
    listBook[countBook]= Book(countBook +1,"Vuelta de tuerca","Henry James","Terror",380.359,10,false,0)
    listBook[countBook]= Book(countBook +1,"El fantasma de la ópera","Gastón Leroux","Terror",374.386,10,false,0)
    listBook[countBook]= Book(countBook +1,"El exorcista","William Peter Blatty","Terror",368.413,10,false,0)
    listBook[countBook]= Book(countBook +1,"Entrevista con el vampiro","Anne Rice","Terror",362.44,10,true,0)
    listBook[countBook]= Book(countBook +1,"Cementerio de animales","Stephen King","Terror",356.467,10,false,0)
    listBook[countBook]= Book(countBook +1,"El marciano","Andy Weir","Ciencia Ficción",350.494,10,false,0)
    listBook[countBook]= Book(countBook +1,"Ready Player One ","Ernest Clin","Ciencia Ficción",344.521,10,true,0)
    listBook[countBook]= Book(countBook +1,"World War Z","Max Brooks","Ciencia Ficción",338.548,10,false,0)
    listBook[countBook]= Book(countBook +1,"American Gods","Neil Gaiman","Ciencia Ficción",332.575,10,false,0)
    listBook[countBook]= Book(countBook +1,"Desfiladero de la absolución","Alastair Reynolds","Ciencia Ficción",326.602,10,false,0)
    listBook[countBook]= Book(countBook +1,"El jugador","Iain M. Banks","Ciencia Ficción",320.629,10,false,0)
    listBook[countBook]= Book(countBook +1,"The Expanse","James S.A. Corey","Ciencia Ficción",314.656,10,true,0)
    listBook[countBook]= Book(countBook +1,"Qualityland","Marc-Uwe Kling","Ciencia Ficción",308.683,10,false,0)
    listBook[countBook]= Book(countBook +1,"Las estrellas son legión","Kameron Hurley","Ciencia Ficción",302.71,10,false,0)
    listBook[countBook]= Book(countBook +1,"Embassytown: La Ciudad Embajada de ","China Mieville:","Ciencia Ficción",296.737,10,true,0)
    listBook[countBook]= Book(countBook +1,"Orgullo y Prejuicio","Jane Austen","romance",290.764,10,false,0)
    listBook[countBook]= Book(countBook +1,"Emma","Jane Austen","romance",284.791,10,false,0)
    listBook[countBook]= Book(countBook +1,"Sentido y Sensibilidad","Jane Austen","romance",278.818,10,false,0)
    listBook[countBook]= Book(countBook +1,"La antigua magia","Lisa Kleypas","romance",272.845,10,true,0)
    listBook[countBook]= Book(countBook +1,"Tuya a Medianoche","Lisa Kleypas","romance",266.872,10,false,0)
    listBook[countBook]= Book(countBook +1,"Tentación al Anochecer","Lisa Kleypas","romance",260.899,10,false,0)
    listBook[countBook]= Book(countBook +1,"Secretos de una noche de verano","Lisa Kleypas","romance",254.926,10,false,0)
    listBook[countBook]= Book(countBook +1,"Casarse con él","Lisa Kleypas","romance",248.953,10,true,0)
    listBook[countBook]= Book(countBook +1,"Una tentación para el Duque","Lorraine Heath","romance",242.98,10,false,0)
    listBook[countBook]= Book(countBook +1,"Jane Eyre","Lorraine Heath","romance",237.007,10,false,0)
    listBook[countBook]= Book(countBook +1,"Autor anonimo","POEMA DE GILGAMESH","historia",231.034,10,false,0)
    listBook[countBook]= Book(countBook +1,"Autor anonimo","LIBRO DE JOB","historia",225.061,10,true,0)
    listBook[countBook]= Book(countBook +1,"Autor anonimo","las mil y una noches","historia",219.088,10,false,0)
    listBook[countBook]= Book(countBook +1,"Autor anonimo","Saga de njal","historia",213.115,10,false,0)
    listBook[countBook]= Book(countBook +1," CHINUA ACHEBE","TODO SE DESMORONA","historia",207.142,10,true,0)
    listBook[countBook]= Book(countBook +1,"HANS CHRISTIAN ANDERSEN","CUENTOS INFANTILES","historia",201.169,10,false,0)
    listBook[countBook]= Book(countBook +1,"DANTE ALIGHIERI","DIVINA COMEDIA","historia",195.196,10,false,0)
    listBook[countBook]= Book(countBook +1,"HONORÉ DE BALZAC","PAPÁ GORIOT","historia",189.223,10,false,0)
    listBook[countBook]= Book(countBook +1,"SAMUEL BECKETT","MOLLOY","historia",183.25,10,true,0)
    listBook[countBook]= Book(countBook +1,"GIOVANNI BOCCACCIO","Decameron","historia",177.277,10,false,0)
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

