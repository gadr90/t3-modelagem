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
	
	// TODO: Tratar que no tale de partida no começo da partida ninguém ganha 200, só quando passa por ele completando uma volta completa no tabuleiro. Não há necessidade de parar na casa, ganha quem passa por ela.
	
}
