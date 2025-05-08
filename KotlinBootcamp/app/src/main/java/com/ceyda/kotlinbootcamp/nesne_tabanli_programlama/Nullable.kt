package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

fun main() {
    //nullable , null safety, optional
    //genelde mobil uygulamalarda kullanılır
    //null, nan, nil

    //1. Tanımlama
    var mesaj:String? = null

    //mesaj = "Merhaba"

    //Yöntem 1
    //Sorun yoksa çalışır, sorun varsa uygulama çökmez
    println("Yontem 1 : ${mesaj?.length}")

    val x = mesaj?.length


    //Yöntem 2
    //Sorun yoksa çalışır, sorun varsa uygulama çöker
    //Çok emin olduğun kodda kullan
    //println("Yontem 2 : ${mesaj!!.length}")


    //Yöntem 3
    //null kontrol
    if(mesaj != null)
        println("Yontem 3 : ${mesaj!!.length}")
    else
        println("Null deger")

    //null kontrolü
    mesaj?.let {
        println("Yontem 3 : ${mesaj!!.length}")
    }
}