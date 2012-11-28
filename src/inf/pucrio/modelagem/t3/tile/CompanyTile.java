package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.card.CompanyCard;

public class CompanyTile extends MonopolyTile implements ITaxableTile {
	
	private CompanyCard card;

	public CompanyTile(int index, Game game, String name,
			int saleValue, int mortgageValue, int rentalValueFactor, Player owner) {
		super(index, game);
		card = new CompanyCard(game, name, saleValue, mortgageValue, rentalValueFactor, owner);
	}

	@Override
	public void collectTax(Player player) {
		// TODO: Multiplicar n�mero tirado nos dados pelo rentalValueFactor
	}
	
	public void buy(Player player) {
		if (this.getOwner() == player) 
			return;
		
		this.card.setOwner(player);
		player.addMoney( - this.card.getSaleValue());
		
		game.updateInterface();
	}

	public Player getOwner() {
		return card.getOwner();
	}

	public CompanyCard getCard() {
		return card;
	}

}
