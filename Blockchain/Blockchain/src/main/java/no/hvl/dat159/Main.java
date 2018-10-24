package no.hvl.dat159;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws IOException {
		/*
		 * Create a blockchain and a miner, add some blocks and validate the chain.
		 * You should also System.print out the blocks as soon as the are appended,
		 * and print out the final validation result. See output.txt for example
		 * output for a solution to this assignment.
		 */
		int difficulty = 5;
		List<String> dataset = new ArrayList<String>(Arrays.asList("lol!", "lolo", "lulz", "ROFL"));
		System.out.println("DIFFICULTY: "+difficulty + "\n\n" + String.join("", Collections.nCopies(195, "#")));

		Blockchain blockchain = new Blockchain(difficulty);
		Miner m1 = new Miner(blockchain);
		for(String data : dataset)
			System.out.println("Block " + blockchain.sizeOfChain() + ": \t" + m1.createAndMineNewBlock(data));
		System.out.println(String.join("",Collections.nCopies(195, "#")));
		System.out.println("Validating blockchain...\n\n");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(blockchain.isValidChain()?"Blockchain is valid!":"Blockchain is NOT valid!");
	}


}



