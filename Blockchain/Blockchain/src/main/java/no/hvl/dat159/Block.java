package no.hvl.dat159;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {

    private long nonce;     // The nonce for this block.

    private String data; // The data for this block.
    private String prev; // The hash of the previous block in the blockchain.
    private String hash; // The calculated hash for this block.

    public Block(String hashLastBlock, String data) throws IOException {
        // Initializing *ALL* the instance variables to a valid state.
        // The nonce can be 0. No mining required at this stage.
        // Use the helper method calculateHash() to calculate the hash.
        prev = hashLastBlock;
        nonce = 0;
        this.data = data;
        hash = calculateHash(prev, nonce, data);
    }

    public void mine(String miningTarget) throws IOException {
        // Given the miningTarget, mine until the calculated hash matches the target.
        // The target is a regular expression, for example "^0{5}.*" which implies
        // that the hash must start with 5 zeros.
        nonce = 0;
        while (!hash.matches(miningTarget)) {
            nonce++;
            hash = calculateHash(prev, nonce, data);
        }
    }

    public boolean isValidAsNextBlock(String hashLastBlock, String miningTarget) {
        // A complete validation of the block, including prev matching
        // the hash of the last block in the blockchain, and that the block is
        // mined according to the mining target.
        return (prev.equals(hashLastBlock) && hash.matches(miningTarget) && !data.equals(""));
    }


    // getters, setters and a nice toString()-method
    private String calculateHash(String prev, long nonce, String data) {
        return DatatypeConverter.printHexBinary(createSHA256(prev+nonce+data));

    }

    public static byte[] createSHA256(String file) {
        MessageDigest digester = null;
        try {
            digester = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digester.digest(file.getBytes());

    }

    public String getHash() {
        return hash;
    }

    public String getPrev() {
        return prev;
    }

    @Override
    public String toString() {
        return "[nonce=" + nonce +
                ", "+"\t" + "data='" + data + '\'' +
                ", "+ '\t' + "prev='" + prev + '\'' +
                ", "+'\t' + "hash='" + hash + '\'' + "]";
    }

}
