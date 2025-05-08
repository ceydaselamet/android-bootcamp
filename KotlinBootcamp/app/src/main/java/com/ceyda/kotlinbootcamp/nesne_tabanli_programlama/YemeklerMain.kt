package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

fun main() {
    val y1 = Yemekler(100, "Köfte", 249)
    y1.bilgiAl();

    y1.fiyat = 350
    y1.bilgiAl()
    println("--------------------")

    val y2 = Yemekler(200, "Baklava", 300)
    y2.bilgiAl()
}