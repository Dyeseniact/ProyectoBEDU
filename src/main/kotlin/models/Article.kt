package models

class Article(
    override val id: Int,
    override var title: String,
    override var author: String,
    override var genre: String,
    override var price: Double,
    override var stock: Int,
    override var favorite: Boolean=false,
    override var discount: Int=0,
    private var namePublication: String="",
    private var yearPublication: Int=0,
    private var monthPublication:String=""
):Product(){
    fun namePublication():String=namePublication
    fun yearPublication():Int=yearPublication
    fun monthPublication():String=monthPublication
}