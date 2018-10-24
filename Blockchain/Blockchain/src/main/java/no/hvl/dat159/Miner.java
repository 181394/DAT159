package no.hvl.dat159;

import java.io.IOException;

public class Miner {
	
	private Blockchain chain;
	
	public Miner(Blockchain chain) {
		this.chain = chain;
	}

	public Block createAndMineNewBlock(String data) throws IOException {
		// Create a new block and mine with the goal of appending to the chain.
		Block b1 =new Block(chain.getHashLastBlock(), data);
		b1.mine(chain.getMiningTarget());
		chain.validateAndAppendNewBlock(b1);
		// Return the mined block.
		return b1;
	}		
	
		
}
