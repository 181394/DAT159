package no.hvl.dat159.clients

import com.google.gson.Gson
import com.google.gson.JsonObject

class TempClient(thingName:String) :DweetClient(thingName){
    fun publish(temperature: Double): Boolean = JsonObject()
        .apply { addProperty("temperature", temperature) }
        .let { publisher(it) }
    fun getTemp(): Double {//skal være Temperature
        val response:Double = Gson().fromJson(get().get("temperature"), Double::class.java)
        return response
    }

}
