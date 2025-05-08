package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

fun main() {

    val f = Fonksiyonlar()

    f.selamla1()

    val gelenSonuc = f.selamla2()
    println("Gelen Sonuc : $gelenSonuc")

    f.selamla3("Ceyda")

    f.topla(1,2)
    f.topla(1.0,2.5)
    f.topla(1,2, 3)
}