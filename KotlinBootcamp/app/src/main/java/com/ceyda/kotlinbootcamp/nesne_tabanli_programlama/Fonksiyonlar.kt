package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

class Fonksiyonlar {

    // void = Unit
    fun selamla1(){
        val sonuc = "Merhaba Ceyda!"
        println(sonuc)
    }

    //return
    fun selamla2() : String{
        val sonuc = "Merhaba Ceyda!"
        return sonuc
    }

    //parametre
    fun selamla3(isim:String) {
        val sonuc = "Merhaba $isim!"
        println(sonuc)
    }

    //overloading
    fun topla(sayi1:Int, sayi2:Int){
        println("Toplama : ${sayi1 + sayi2}")
    }

    fun topla(sayi1:Double, sayi2:Double){
        println("Toplama : ${sayi1 + sayi2}")
    }

    fun topla(sayi1:Int, sayi2:Int, sayi3:Int){
        println("Toplama : ${sayi1 + sayi2 + sayi3}")
    }
}