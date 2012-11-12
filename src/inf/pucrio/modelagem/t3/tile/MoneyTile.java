package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;

public class MoneyTile extends MonopolyTile {

	private int quantity = 200;
	private boolean winMoney = true;
	
	public MoneyTile(int index, Game game, int quantity, boolean winMoney) {
		super(index, game);
		this.quantity = quantity;
		this.winMoney = winMoney;
	}
	
}
