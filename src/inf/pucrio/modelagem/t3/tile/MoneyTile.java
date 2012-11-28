package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;

public class MoneyTile extends MonopolyTile {

	private int value = 200;
	
	public MoneyTile(int index, Game game, int value) {
		super(index, game);
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
