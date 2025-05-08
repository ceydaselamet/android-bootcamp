package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

fun main() {
    val a = ClassA()

    //standart nesne tabanlı erişim
    //println(a.x)
    //a.metod()

    //sanal nesne - virtual object - nameless object
    //parantez varsa nesne oluşur
    //println(ClassA().x) //1. nesne
    //ClassA().metod() // 2. nesne


    //static kullanımı
    println(ClassA.x)
    ClassA.metod()
}