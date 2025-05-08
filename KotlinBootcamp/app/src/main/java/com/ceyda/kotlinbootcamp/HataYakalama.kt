package com.ceyda.kotlinbootcamp

fun main() {

    //1 - Compile Error : Kodlama yaparken oluşur
    //val sayi = 100
    //sayi = 500

    //2 - Runtime Error (Exceptions) : Çalışma sırasında oluşur
    val x = 10
    val y = 0

    try {
        println("Sonuc : ${x/y}")
        println("Islem Tamamlandi")
    }catch (e:Exception){
        println("Ikinci sayi 0 olamaz")
    }

}