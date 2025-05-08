package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

fun main() {
    ucretHesapla(KonserveBoyut.ORTA, 50)
}

fun ucretHesapla(boyut: KonserveBoyut, adet:Int){
    when(boyut){
        KonserveBoyut.KUCUK -> println("Ücret : ${adet*104} TL")
        KonserveBoyut.ORTA -> println("Ücret : ${adet*207} TL")
        KonserveBoyut.BUYUK -> println("Ücret : ${adet*309} TL")
    }
}