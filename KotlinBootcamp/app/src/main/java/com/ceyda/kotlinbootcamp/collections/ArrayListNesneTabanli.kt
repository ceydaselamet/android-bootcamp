package com.ceyda.kotlinbootcamp.collections

fun main() {
    val f1 = Filmler(1, "Babam ve Oğlum", 50.0)
    val f2 = Filmler(2, "Neşeli Hayatlar", 30.0)
    val f3 = Filmler(3, "Kış Uykusu", 35.0)

    val filmlerListesi = ArrayList<Filmler>()
    filmlerListesi.add(f1)
    filmlerListesi.add(f2)
    filmlerListesi.add(f3)

    for(film in filmlerListesi)
        println("${film.id} : ${film.ad} : ${film.fiyat}")


    //Sıralama - Sorting
    println("------Fiyata Gore Artan-------")
    val siralama1 = filmlerListesi.sortedWith(compareBy { it.fiyat })

    for(film in siralama1)
        println("${film.id} : ${film.ad} : ${film.fiyat}")

    println("------Fiyata Gore Azalan-------")
    val siralama2 = filmlerListesi.sortedWith(compareByDescending { it.fiyat })

    for(film in siralama2)
        println("${film.id} : ${film.ad} : ${film.fiyat}")


    //Fitreleme
    println("---Filtreleme 1 ---")
    val filtreleme1 = filmlerListesi.filter { it.fiyat > 40 }
    for(film in filtreleme1)
        println("${film.id} : ${film.ad} : ${film.fiyat}")

    println("---Filtreleme 2 ---")
    val filtreleme2 = filmlerListesi.filter { it.ad.contains("a") }
    for(film in filtreleme2)
        println("${film.id} : ${film.ad} : ${film.fiyat}")

}
