package no.hvl.dat159;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import no.hvl.dat159.Block;

public class Blockchain {

	private int miningDifficulty; // The number of leading zeros in block hashes
	private String miningTarget;  // The reg-exp representing the mining target

	private List<Block> listOfBlocks; // The list of blocks
	
	public Blockchain(int miningDifficulty) {
		// Initializing *ALL* the instance variables to a valid state.
		this.miningDifficulty = miningDifficulty;
		miningTarget = "^0{"+miningDifficulty +"}.*";
		listOfBlocks = new ArrayList<Block>();
	}
	
	public String getHashLastBlock() {
		// Returning the hash of the last block appended to the chain.
		// If the chain is empty, "0" is returned.
		return listOfBlocks.isEmpty() ? String.join("", Collections.nCopies(64,"0")) : listOfBlocks.get(listOfBlocks.size() - 1).getHash();
	}

	public boolean validateAndAppendNewBlock(Block b) {
		// Validate and append to chain if valid.
		// Return whether everything went OK and the block was appended.
		return b.isValidAsNextBlock(b.getPrev(), miningTarget) && listOfBlocks.add(b);
	}
	
	public boolean isValidChain() {
		// Validate the entire chain.
		return getHashLastBlock().matches(miningTarget);
	}

	// getters and setters


	public int getMiningDifficulty() {
		return miningDifficulty;
	}

	public void setMiningDifficulty(int miningDifficulty) {
		this.miningDifficulty = miningDifficulty;
	}

	public String getMiningTarget() {
		return miningTarget;
	}

	public void setMiningTarget(String miningTarget) {
		this.miningTarget = miningTarget;
	}

	public List<Block> getListOfBlocks() {
		return listOfBlocks;
	}

	public int sizeOfChain(){return listOfBlocks.size();}

	public void setListOfBlocks(List<Block> listOfBlocks) {
		this.listOfBlocks = listOfBlocks;
	}
}
