package inf.pucrio.modelagem.t3.card;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class CompanyCard extends MonopolyCard {
	
	private String name;
	private int saleValue;
	private int mortgageValue;
	private int rentalValueFactor;
	private Player owner;

	public CompanyCard(Game game, String name,
			int saleValue, int mortgageValue, int rentalValueFactor, Player owner) {
		super(game);
		this.owner = owner;
		this.name = name;
		this.saleValue = saleValue;
		this.mortgageValue = mortgageValue;
		this.rentalValueFactor = rentalValueFactor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(int saleValue) {
		this.saleValue = saleValue;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public int getRentalValueFactor() {
		return rentalValueFactor;
	}

	public void setRentalValueFactor(int rentalValueFactor) {
		this.rentalValueFactor = rentalValueFactor;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		this.owner.getDeck().add(this);
	}

	
}
