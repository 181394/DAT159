package no.hvl.dat159.models

class Room(starttemp: Int) {

    private var tempstate: Int = 0

    private var temperature: Double = 0.toDouble()
    private val lastsense: Long

    init {
        tempstate = -1
        temperature = starttemp.toDouble()
        lastsense = System.currentTimeMillis()
    }

    @Synchronized
    fun sense(): Double {

        val timenow = System.currentTimeMillis()
        val timeinterval = timenow - lastsense

        temperature = temperature + RATE * tempstate.toDouble() * timeinterval.toDouble()

        return temperature
    }

    @Synchronized
    fun actuate(newstate: Boolean) {

        sense()

        if (newstate)
            tempstate = 1
        else
            tempstate = -1
    }

    companion object {
        private val RATE = .0001 // change in temperature per time unit
    }
}
