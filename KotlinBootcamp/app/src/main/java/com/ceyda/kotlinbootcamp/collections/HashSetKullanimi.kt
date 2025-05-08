package com.ceyda.kotlinbootcamp.collections

fun main() {
    //HashSet
    //Tanımlama
    val meyveler = HashSet<String>()
    //Any -> en üst sınıf, bütün türleri kapsar
    //Javada -> Object

    meyveler.add("Elma")
    meyveler.add("Muz")
    meyveler.add("Kiraz")
    println(meyveler)

    meyveler.add("Amasya Elma")
    println(meyveler)

    val y = meyveler.elementAt(2)
    println(y)

    println("Boyut : ${meyveler.size}")

    for(meyve in meyveler.withIndex()) {
        println("Sonuc : ${meyve}")
    }

    for((indeks,meyve) in meyveler.withIndex()) {
        println("$indeks. : ${meyve}")
    }

    meyveler.remove("Elma")
    println(meyveler)





}