package models

abstract class Product {
    protected abstract val id:Int
    protected abstract var title:String
    protected abstract var author:String
    protected abstract var genre:String
    protected abstract var price:Double
    protected abstract var stock:Int
    protected abstract var favorite:Boolean
    protected abstract var discount:Int

    fun getPrice(id:Int):Double{
            return price-(price*discount)
        }
}