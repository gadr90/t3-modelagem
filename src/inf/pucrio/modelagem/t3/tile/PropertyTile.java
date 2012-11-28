package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.card.PropertyCard;

import java.awt.Color;

public class PropertyTile extends MonopolyTile implements ITaxableTile {

	// private static Color[] availableColors = {Color.GREEN, Color.DARK_GRAY,
	// Color.BLUE, Color.CYAN, Color.magenta, Color.RED, Color.orange,
	// Color.YELLOW};
	private PropertyCard card;

	public PropertyTile(int index, Game game, String address, int saleValue,
			int constructionValue, int mortgageValue, int noConstructionRent,
			int oneHouseRent, int twoHousesRent, int threeHousesRent,
			int fourHousesRent, int hotelRent, Player owner, Color color) {
		super(index, game);
		this.card = new PropertyCard(game, address, saleValue,
				constructionValue, mortgageValue, noConstructionRent,
				oneHouseRent, twoHousesRent, threeHousesRent, fourHousesRent,
				hotelRent, owner, color);
	}

	@Override
	public void collectTax(Player player) {
		// TODO: Checar n�mero de constru��es e cobrar de acordo com respectivo
		// valor
	}

	@Override
	public void buy(Player player) {
		if (this.getOwner() == player)
			return;

		this.card.setOwner(player);
		player.addMoney( - this.card.getSaleValue());
		game.updateInterface();
	}

	@Override
	public Player getOwner() {
		return this.card.getOwner();
	}

	public PropertyCard getCard() {
		return card;
	}

}
