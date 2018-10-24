package no.hvl.dat159

import javax.xml.bind.DatatypeConverter
import java.io.IOException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class Block @Throws(IOException::class)
constructor(val prev: String // The hash of the previous block in the blockchain.
            , private val data: String // The data for this block.
) {

    private var nonce: Long = 0     // The nonce for this block.
    var hash: String? = null
        private set // The calculated hash for this block.

    init {
        nonce = 0
        hash = calculateHash(prev, nonce, data)
    }// Initializing *ALL* the instance variables to a valid state.
    // The nonce can be 0. No mining required at this stage.
    // Use the helper method calculateHash() to calculate the hash.

    @Throws(IOException::class)
    fun mine(miningTarget: String) {
        // Given the miningTarget, mine until the calculated hash matches the target.
        // The target is a regular expression, for example "^0{5}.*" which implies
        // that the hash must start with 5 zeros.
        nonce = 0
        while (!hash!!.matches(miningTarget.toRegex())) {
            nonce++
            hash = calculateHash(prev, nonce, data)
        }
    }

    fun isValidAsNextBlock(hashLastBlock: String, miningTarget: String): Boolean {
        // A complete validation of the block, including prev matching
        // the hash of the last block in the blockchain, and that the block is
        // mined according to the mining target.
        return prev == hashLastBlock && hash!!.matches(miningTarget.toRegex()) && data != ""
    }


    // getters, setters and a nice toString()-method
    private fun calculateHash(prev: String, nonce: Long, data: String): String {
        return DatatypeConverter.printHexBinary(createSHA256(prev + nonce + data))

    }

    override fun toString(): String {
        return "[nonce=" + nonce +
                ", " + "\t" + "data='" + data + '\''.toString() +
                ", " + '\t'.toString() + "prev='" + prev + '\''.toString() +
                ", " + '\t'.toString() + "hash='" + hash + '\''.toString() + "]"
    }

    companion object {

        fun createSHA256(file: String): ByteArray {
            var digester: MessageDigest? = null
            try {
                digester = MessageDigest.getInstance("SHA-256")
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }

            return digester!!.digest(file.toByteArray())

        }
    }

}
