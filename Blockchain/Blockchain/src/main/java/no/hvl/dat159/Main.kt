package no.hvl.dat159

import java.io.IOException

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections

object Main {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val difficulty = 5
        val dataset = ArrayList(Arrays.asList("lol!", "lolo", "lulz", "ROFL"))


        println("DIFFICULTY: " + difficulty + "\n\n" + "#".repeat(195))

        val blockchain = Blockchain(difficulty)
        val m1 = Miner(blockchain)
        for (data in dataset)
            println("Block " + blockchain.sizeOfChain() + ": \t" + m1.createAndMineNewBlock(data))
        println("#".repeat(196))
        println("Validating blockchain...\n\n")
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        println(if (blockchain.isValidChain) "Blockchain is valid!" else "Blockchain is NOT valid!")

    }


}



