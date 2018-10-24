package no.hvl.dat159

import java.io.IOException

class Miner(private val chain: Blockchain) {

    @Throws(IOException::class)
    fun createAndMineNewBlock(data: String): Block {
        // Create a new block and mine with the goal of appending to the chain.
        var b1 = Block(chain.hashLastBlock!!, data)
        b1.mine(chain.miningTarget!!)
        chain.validateAndAppendNewBlock(b1)
        // Return the mined block.
        return b1
    }


}
