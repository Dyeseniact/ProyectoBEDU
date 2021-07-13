package models

import Interfaces.Information

class Book(
    public override val id: Int,
    public override var title: String,
    public override var author: String,
    public override var genre: String,
    public override var price: Double,
    public override var stock: Int,
    public override var favorite: Boolean=false,
    public override var discount: Int=0
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