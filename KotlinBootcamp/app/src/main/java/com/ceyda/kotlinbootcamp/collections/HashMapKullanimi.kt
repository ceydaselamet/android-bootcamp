package com.ceyda.kotlinbootcamp.collections

fun main() {
    //HashMap JSON veri modeline benzer
    //Map, Dictionary
    //Shared Preferences, DataStore, UserDefault
    val iller = HashMap<Int, String>()

    iller.put(16, "Bursa")
    iller[34] = "İstanbul"
    iller[6] = "Ankara"

    println(iller)

    iller[16] = "Yeni Bursa"
    println(iller)

    val il = iller[6]
    println(il)

    println("Boyut : ${iller.size}")

    for((key, value) in iller){
        println("$key -> ${value}")
    }

    iller.remove(6)
    println(iller)

    iller.clear()
    println(iller)

}