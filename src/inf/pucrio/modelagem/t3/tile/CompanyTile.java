package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.NotEnoughMoneyException;
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
	public void collectRent(Player player) {
		if (this.getOwner() == null) return;
		
		int rent = (this.getCard().getRentalValueFactor() * player.getCurrentRoll());
		player.addMoney( - rent);
		this.getOwner().addMoney(rent);
	}

	@Override
	public void buy(Player player, int agreedPrice) throws NotEnoughMoneyException {
		if (this.getOwner() == player)
			return;
		
		if (player.getMoney() < agreedPrice)
			//TODO Colocar esse throw dentro de AddMoney no player
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
		return card.getOwner();
	}

	public CompanyCard getCard() {
		return card;
	}

}
