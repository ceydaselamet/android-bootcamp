package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama.kalitim

class Kedi : Memeli() {
    override fun sesCikar() {
        //super.sesCikar() -> super: kalıtım ile üst sınıfı temsil eder
        println("Miyav")
    }
}