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
		this.card.setTile(this);
	}

	@Override
	public void collectRent(Player player) {
		if (this.getOwner() == null) return;
		
		int rent = this.getCard().getRentMap().get(this.getCard().getBuiltHousesNumber());
		
		// Se o jogador faliu ao pagar esse aluguél, ele não pôde pagar o aluguel todo.
		if ( ! player.addMoney( - rent)) {
			// Retire do dinheiro enviado para o dono o quanto o jogador ficou devendo (Valor negativo).
			rent += player.getMoney();
		}
		this.getOwner().addMoney(rent);
	}

	@Override
	public void buy(Player player, int agreedPrice) throws NotEnoughMoneyException {
		if (this.getOwner() == player)
			return;
		
		if (player.getMoney() < agreedPrice)
			throw new NotEnoughMoneyException("Você não tem dinheiro suficiente para comprar isto!");
		
		// É uma venda
		if (this.getOwner() != null) {
			// Trata o caso do terreno ter construções. Todas devem ser vendidas para o banco pela metade do preço para que depois a negociação seja concretizada.
			while(this.getCard().getBuiltHousesNumber() > 0) {
				this.getOwner().addMoney(this.getCard().getRentMap().get(this.getCard().getBuiltHousesNumber())/2);
				this.getCard().setBuildHousesNumber(this.getCard().getBuiltHousesNumber() - 1);
			}
			
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

	@Override
	public void sell() {
		this.getOwner().addMoney( this.getCard().getSaleValue() / 2 );
		this.getOwner().getDeck().remove(this.card);
		this.card.setOwner(null);
	}

}
