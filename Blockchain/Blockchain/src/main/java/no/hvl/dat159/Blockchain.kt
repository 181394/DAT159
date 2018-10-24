package no.hvl.dat159

import java.util.ArrayList
import java.util.Collections
import java.util.regex.Pattern

import no.hvl.dat159.Block

class Blockchain(
        // getters and setters


        var miningDifficulty: Int // The number of leading zeros in block hashes
) {
    var miningTarget: String? = null  // The reg-exp representing the mining target

    private var listOfBlocks: MutableList<Block>? = null // The list of blocks

    // Returning the hash of the last block appended to the chain.
    // If the chain is empty, "0" is returned.
    val hashLastBlock: String?
        get() = if (listOfBlocks!!.isEmpty()) "0".repeat(64) else listOfBlocks!![listOfBlocks!!.size - 1].hash

    // Validate the entire chain.
    val isValidChain: Boolean
        get() = hashLastBlock!!.matches(miningTarget!!.toRegex())

    init {
        miningTarget = "^0{$miningDifficulty}.*"
        listOfBlocks = ArrayList()
    }// Initializing *ALL* the instance variables to a valid state.

    fun validateAndAppendNewBlock(b: Block): Boolean {
        // Validate and append to chain if valid.
        // Return whether everything went OK and the block was appended.
        return b.isValidAsNextBlock(b.prev, miningTarget!!) && listOfBlocks!!.add(b)
    }

    fun getListOfBlocks(): List<Block>? {
        return listOfBlocks
    }

    fun sizeOfChain(): Int {
        return listOfBlocks!!.size
    }

    fun setListOfBlocks(listOfBlocks: MutableList<Block>) {
        this.listOfBlocks = listOfBlocks
    }
}
