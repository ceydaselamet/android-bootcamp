package com.ceyda.kotlinbootcamp.degiskenler

fun main() {
    var id = 1
    var name = "Mont"
    var resim = "mont.png"
    var puan = 4.7
    var fiyat = 4500
    var stokDurum = true

    println("id : $id ")
    println("name: $name")
    println("resim : $resim")
    println("puan : $puan")
    println("fiyat : $fiyat")
    println("stokDurum : $stokDurum")

    //Sabitler - Constants
    var sayi = 30
    sayi = 100
    println(sayi)

    val numara = 50
    println(numara)
    //val kullanmak perfonmansa faydalı
    //var esnek yapı sağlar, büyüyebilir

    //Tür Dönüşümleri
    //1- Sayısaldan sayısala dönüşüm

    val d = 89.56
    val i = 34
    val sonuc1 = d.toInt()
    val sonuc2 = i.toDouble()
    println(sonuc1)
    println(sonuc2)

    //2- Sayısaldan metne dönüşüm
    var x = 85
    val sonuc3 = x.toString()
    println(sonuc3)

    //3- Metinden sayısala dönüşüm
    val yazi = "48T"
    val sonuc4 = yazi.toIntOrNull()

    if(sonuc4 != null){
        println(sonuc4)
    }else
        println("Sonuc null")

}