package no.hvl.dat159.clients

import com.google.gson.Gson
import com.google.gson.JsonObject

class HeatClient(thingName:String) :DweetClient(thingName){

    fun publish(heater: String): Boolean = JsonObject()
        .apply { addProperty("heat", heater) }.let { publisher(it) }

    fun getHeatState(): String {//should be heating
        val response: String = Gson().fromJson(get().get("heat"), String::class.java)
        return response }


}
