package no.hvl.dat159.clients

import java.io.IOException
import java.io.InputStream
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.util.Scanner
import com.google.gson.JsonObject
import com.google.gson.JsonParser


open class DweetClient(var thingName:String) {

    private val API_DWEET_END_POINT = "localhost:8080"//"http://iot-spark-server.herokuapp.com/"

    private val jsonParser = JsonParser()

    @Throws(IOException::class)
    fun publisher(jThing:JsonObject): Boolean {
        thingName = URLEncoder.encode(thingName, "UTF-8")

        val connection = URL("http://$API_DWEET_END_POINT/dweet/for/$thingName")
            .let { it.openConnection() as HttpURLConnection }
            .apply { setRequestProperty("Content-Type", "application/json; charset=utf-8")
                requestMethod = "PUT"
                doInput = true
                doOutput = true
            }

        val out = PrintWriter(connection.outputStream)

        out.println(jThing.toString())
        out.flush()
        out.close()

        val response = readResponse(connection.inputStream)

        connection.disconnect()

        return response.has("temperature")
    }

    @Throws(IOException::class)
    fun get(): JsonObject {

        // http://dweet.io/get/latest/dweet/for/dat159-sensor

        thingName = URLEncoder.encode(thingName, "UTF-8")

        val connection = URL("http://$API_DWEET_END_POINT/get/latest/dweet/for/$thingName")
            .let { it.openConnection() as HttpURLConnection }
            .apply {
                setRequestProperty("Content-Type", "application/json; charset=utf-8")
                setRequestProperty("Accept", "application/json")
                requestMethod = "GET"
                doInput = true
                doOutput = true
            }

        val response = readResponse(connection.inputStream)

        connection.disconnect()

        return response
    }

    fun readResponse(`in`: InputStream): JsonObject {

        val scan = Scanner(`in`)
        val sn = StringBuilder()

        while (scan.hasNext())
            sn.append(scan.nextLine()).append('\n')

        scan.close()

        return jsonParser.parse(sn.toString()).asJsonObject
    }
}
