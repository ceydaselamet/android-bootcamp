package com.ceyda.kotlinbootcamp.standart_programlama

fun main(){
    val yas = 17

    println(yas>=18)

    if(yas >= 18)
        println("Reşit")
    else
        println("Reşit değil")

    val ka= "admin"
    val s = 123456
    val sayi = 10

    if(ka == "admin" && s == 123456)
        print("Hoşgeldiniz")
    else
        println("Hatalı giriş")

    if(sayi == 9 || sayi == 10)
        println("Sayı 9 veya 10")
    else
        println("Sayı 9 veya 10 değil")

    val number = 5

    when(number){
        1 -> println("Sayı 1")
        2 -> println("Sayı 2")
        else -> println("Sayı tanımlanmadı")
    }


}