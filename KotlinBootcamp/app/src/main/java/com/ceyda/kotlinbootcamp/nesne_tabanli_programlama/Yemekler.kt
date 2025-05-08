package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama

data class Yemekler(var id:Int, var ad:String, var fiyat:Int) {
    init {
        println("*** Nesne oluştu ***")
    }

    fun bilgiAl(){
        println("Id    : ${id}")
        println("Ad    : ${ad}")
        println("Fiyat : ${fiyat}")
    }
}