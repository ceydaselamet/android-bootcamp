package com.ceyda.kotlinbootcamp.nesne_tabanli_programlama.kalitim

fun main() {
    val hayvan = Hayvan()
    val memeli = Memeli()
    val kedi = Kedi()
    val kopek = Kopek()

    hayvan.sesCikar()  //Ses Çıkar  - kalıtım yok, kendi metoduna erişti
    memeli.sesCikar()  //Ses Çıkar  - kalıtım var, üst sınıfın metoduna erişti
    kedi.sesCikar()    //Miyav      - kalıtım var, kendi metoduna erişti, ezdi
    kopek.sesCikar()   //Hav hav    - kalıtım var, kendi metoduna erişti, ezdi
}