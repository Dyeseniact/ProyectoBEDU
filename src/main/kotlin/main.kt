import Class.Libro
import Class.NumberIdStock
import Functions.buscarLibro
import Functions.cargaLibros
import javax.swing.JOptionPane

fun main() {

   var newBooks = JOptionPane.showInputDialog("Introduzca el número de libros figticios que quieres cargar").toInt()
   var Lib=Array<Libro?>(newBooks){null}
   cargaLibros(newBooks)
   println("El numero de libros cargados es ${Lib.size}")
    println("El numero de libros cargados es ${Lib[2]?.autor}")



println("El numero de libros cargados es ${Lib.size}")
println("El numero de libros cargados es ${Lib[2]?.autor}")






    //inicio()
    buscarLibro()
}