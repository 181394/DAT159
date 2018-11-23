package no.hvl.dat159.controller

import no.hvl.dat159.clients.HeatClient
import no.hvl.dat159.clients.TempClient

fun main(args: Array<String>) {
    val tempClient = TempClient("temp")
    val heatClient = HeatClient("heat")

    while (true) {
        val temp = tempClient.getTemp()
        if (temp<15){
            heatClient.publish("ON")
            println("ON")
        }else if (temp>25){
            heatClient.publish("OFF")
            println("OFF")
        }
        Thread.sleep(1000)
    }



}