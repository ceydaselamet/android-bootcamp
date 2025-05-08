package com.ceyda.kotlinbootcamp.standart_programlama

fun main(){
    for(i in 1..3){ //swiftte 1...3
        println("Dongu 1 : $i")
    }

    for(x in 10..20 step 5)
        println("Döngü 2 : $x")

    for(x in 20 downTo 10 step 5)
        println("Döngü 3 : $x")

    var sayac = 1

    while(sayac < 4) {
        println("Döngü 4 : $sayac")
        sayac ++
    }

    for(i in 1..5){
        if(i == 3)
            break
        println("Dongu 5 : $i")
    }

    for(i in 1..5){
        if(i == 3)
            continue
        println("Dongu 6 : $i")
    }

}