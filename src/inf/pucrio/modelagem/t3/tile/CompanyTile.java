package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class CompanyTile extends OwnableTile implements ITaxableTile {
	
	private String name;
	private int saleValue;
	private int mortgageValue;
	private int rentalValueFactor;
	
	public CompanyTile(int index, Game game, String name, int saleValue, int mortgageValue, int rentalValueFactor, Player owner) {
		super(index, game, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collectTax(Player player) {
		// TODO: Multiplicar número tirado nos dados pelo rentalValueFactor
		
	}
	
	public void buy(Player player) {
		this.owner = player;
		player.reduceMoney(this.saleValue);
	}

}
