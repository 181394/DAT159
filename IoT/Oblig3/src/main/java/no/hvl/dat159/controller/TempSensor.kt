package no.hvl.dat159.controller

import no.hvl.dat159.clients.HeatClient
import no.hvl.dat159.clients.TempClient
import no.hvl.dat159.models.Room


fun main(args: Array<String>) {
    val tempClient = TempClient("temp")
    val heatClient = HeatClient("heat")
    val room = Room(20)
    val tempSensor = TempSensor(room)

    while (true){
        if (heatClient.getHeatState()=="ON")
            room.actuate(true)
        else if (heatClient.getHeatState()=="OFF")
            room.actuate(false)
        val temp = tempSensor.read()
        tempClient.publish(temp)
        println(temp)
        Thread.sleep(1000)
    }

}



class TempSensor( val room: Room){



    fun read():Double {
        return room.sense()
    }



}
