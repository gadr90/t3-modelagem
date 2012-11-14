package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;

public abstract class MonopolyTile {
	
	protected int index;
	protected Game game;

	public MonopolyTile(int index, Game game) {
		super();
		this.index = index;
		this.game = game;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
