package no.hvl.dat159

import java.io.IOException

import java.util.ArrayList
import java.util.Arrays
import java.util.Collections

object Main {

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        /*
		 * Create a blockchain and a miner, add some blocks and validate the chain.
		 * You should also System.print out the blocks as soon as the are appended,
		 * and print out the final validation result. See output.txt for example
		 * output for a solution to this assignment.
		 */
        val difficulty = 5
        val dataset = ArrayList(Arrays.asList("lol!", "lolo", "lulz", "ROFL"))
        println("DIFFICULTY: " + difficulty + "\n\n" + Collections.nCopies(195, "#").joinToString(""))

        val blockchain = Blockchain(difficulty)
        val m1 = Miner(blockchain)
        for (data in dataset)
            println("Block " + blockchain.sizeOfChain() + ": \t" + m1.createAndMineNewBlock(data))
        println(Collections.nCopies(195, "#").joinToString(""))
        println("Validating blockchain...\n\n")
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        println(if (blockchain.isValidChain) "Blockchain is valid!" else "Blockchain is NOT valid!")

    }


}



