package models

import Interfaces.Information

class Book(
    override val id: Int,
    override var title: String,
    override var author: String,
    override var genre: String,
    override var price: Double,
    override var stock: Int,
    override var favorite: Boolean=false,
    override var discount: Int=0
):Product(), Information {

init {
    countBook++

}

    override fun infoTitleAutorPrice():String {
        println("El titulo $title fue escrito por $author y tiene un precio de $price ${
            if (discount > 0) {
                println("lo tenemos acualmente en descuento de $discount el precio final es ${price-(price*discount)}")
            }else{
                println("")
            }
        }")
        return ""
    }
}