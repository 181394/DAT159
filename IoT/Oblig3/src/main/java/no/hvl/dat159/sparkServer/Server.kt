package no.hvl.dat159.sparkServer

import spark.Spark.*
import com.google.gson.*
import no.hvl.dat159.models.Heating
import no.hvl.dat159.models.Temperature

fun main(args: Array<String>) {
    val tempName = "temp"
    val heatName = "heat"
    var heater = Heating()
    var temp = Temperature()

    port(8080)
    //port(getHerokuAssignedPort())

    after("*") { req, res -> res.type("application/json") }

    get("/get/latest/dweet/for/$tempName") { req, res -> Gson().toJsonTree(temp) }

    put("/dweet/for/$tempName") { req, res ->

        val gson = Gson()

        temp = gson.fromJson(req.body(), Temperature::class.java)

        Gson().toJsonTree(temp)

    }
    get("/get/latest/dweet/for/$heatName") { req, res -> Gson().toJsonTree(heater)}

    put("/dweet/for/$heatName") { req, res ->

        val gson = Gson()

        heater = gson.fromJson(req.body(), Heating::class.java)

        Gson().toJsonTree(heater)
    }


}


fun getHerokuAssignedPort():Int {
    val processBuilder = ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
}
