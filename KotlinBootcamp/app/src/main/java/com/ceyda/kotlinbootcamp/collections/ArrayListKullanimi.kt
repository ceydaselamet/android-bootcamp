package com.ceyda.kotlinbootcamp.collections

fun main() {
    //Tanımlama
    val meyveler = ArrayList<String>()

    //Veri ekleme : en sonuna ekler
    meyveler.add("Elma") //0. index
    meyveler.add("Muz") // 1
    meyveler.add("Kiraz") // 2

    println(meyveler)

    //Güncelleme
    meyveler[1] = "Yeni muz"

    println(meyveler[1])

    //Insert : istediğimiz indexe ekler
    meyveler.add(1, "Portakal")
    println(meyveler)

    //Okuma
    val m = meyveler.get(3)
    println(m)

    println(meyveler.size)

    meyveler.reverse()
    println(meyveler)

    for(meyve in meyveler)
        println("Sonuc : ${meyve}")

    println("----------")

    for((indeks,meyve) in meyveler.withIndex()) {
        println("$indeks. : ${meyve}")
    }

    meyveler.removeAt(1)
    println(meyveler)

    meyveler.clear()
    println(meyveler)

}