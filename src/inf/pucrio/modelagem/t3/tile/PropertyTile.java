package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.NotEnoughMoneyException;
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
	public void buy(Player player, int agreedPrice) throws NotEnoughMoneyException {
		if (this.getOwner() == player)
			return;
		
		if (player.getMoney() < agreedPrice)
			throw new NotEnoughMoneyException("Você não tem dinheiro suficiente para comprar isto!");
		
		// É uma venda
		if (this.getOwner() != null) {
			this.getOwner().getDeck().remove(this.card);
			this.getOwner().addMoney( agreedPrice );
		}

		this.card.setOwner(player);
		player.addMoney( - agreedPrice);
		game.updateInterface();
	}
	
	@Override
	public void buy(Player player) throws NotEnoughMoneyException {
		this.buy(player, this.card.getSaleValue());		
	}

	@Override
	public Player getOwner() {
		return this.card.getOwner();
	}

	public PropertyCard getCard() {
		return card;
	}

}
