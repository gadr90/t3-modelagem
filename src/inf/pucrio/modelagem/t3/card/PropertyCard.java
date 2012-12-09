package inf.pucrio.modelagem.t3.card;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Main;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Color;
import java.util.List;

public class PropertyCard extends MonopolyCard {

	public static final int MAX_NUMBER_HOUSES = 4;
	//private static Color[] availableColors = {Color.GREEN, Color.DARK_GRAY, Color.BLUE, Color.CYAN, Color.magenta, Color.RED, Color.orange, Color.YELLOW};
	private Color color;
	private String address;
	private int saleValue;
	private int constructionValue;
	private int mortgageValue;
	private int noConstructionRent;
	private int oneHouseRent;
	private int twoHousesRent;
	private int threeHousesRent;
	private int fourHousesRent;
	private int hotelRent;
	private int builtHousesNumber;
	private Player owner;
	
	public PropertyCard(Game game, String address, int saleValue, int constructionValue, int mortgageValue, 
						int noConstructionRent, int oneHouseRent, int twoHousesRent, int threeHousesRent,
						int fourHousesRent, int hotelRent, Player owner, Color color) {
		super(game);
		this.owner = owner;
		this.address = address;
		this.saleValue = saleValue;
		this.constructionValue = constructionValue;
		this.mortgageValue = mortgageValue;
		this.noConstructionRent = noConstructionRent;
		this.oneHouseRent = oneHouseRent;
		this.twoHousesRent = twoHousesRent;
		this.threeHousesRent = threeHousesRent;
		this.fourHousesRent = fourHousesRent;
		this.hotelRent = hotelRent;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(int saleValue) {
		this.saleValue = saleValue;
	}

	public int getConstructionValue() {
		return constructionValue;
	}

	public void setConstructionValue(int constructionValue) {
		this.constructionValue = constructionValue;
	}

	public int getMortgageValue() {
		return mortgageValue;
	}

	public void setMortgageValue(int mortgageValue) {
		this.mortgageValue = mortgageValue;
	}

	public int getNoConstructionRent() {
		return noConstructionRent;
	}

	public void setNoConstructionRent(int noConstructionRent) {
		this.noConstructionRent = noConstructionRent;
	}

	public int getOneHouseRent() {
		return oneHouseRent;
	}

	public void setOneHouseRent(int oneHouseRent) {
		this.oneHouseRent = oneHouseRent;
	}

	public int getTwoHousesRent() {
		return twoHousesRent;
	}

	public void setTwoHousesRent(int twoHousesRent) {
		this.twoHousesRent = twoHousesRent;
	}

	public int getThreeHousesRent() {
		return threeHousesRent;
	}

	public void setThreeHousesRent(int threeHousesRent) {
		this.threeHousesRent = threeHousesRent;
	}

	public int getFourHousesRent() {
		return fourHousesRent;
	}

	public void setFourHousesRent(int fourHousesRent) {
		this.fourHousesRent = fourHousesRent;
	}

	public int getHotelRent() {
		return hotelRent;
	}

	public void setHotelRent(int hotelRent) {
		this.hotelRent = hotelRent;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		this.owner.getDeck().add(this);
	}

	public int getBuiltHousesNumber() {
		return builtHousesNumber;
	}
	
	public boolean playerOwnsAllProperties(Player player, List<PropertyTile> properties) {
		for (PropertyTile t : properties) {
			if (t.getOwner() != player) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkMinimumHousesBuilt(List<PropertyTile> properties, int numberOfHouses) {
		for (PropertyTile t : properties) {
			if (t.getCard().getBuiltHousesNumber() < numberOfHouses) {
				return false;
			}
		}
		return true;
	}

	public boolean build() {
		List<PropertyTile> likeColoredProperties = Main.game.getAllPropertiesWithColor(this.getColor());
		boolean ownsLikeColoredProperties = playerOwnsAllProperties(getOwner(), likeColoredProperties);
		boolean minimumHousesBuilt = checkMinimumHousesBuilt(likeColoredProperties, this.getBuiltHousesNumber());
		
		if (ownsLikeColoredProperties && minimumHousesBuilt && this.getBuiltHousesNumber() <= MAX_NUMBER_HOUSES) {
			this.builtHousesNumber++;
			this.getOwner().addMoney( - this.getConstructionValue());
			return true;
		}
		return false;
	}

	public String getLabel() {
		return this.getBuiltHousesNumber() > 4 ? (this.getBuiltHousesNumber() - 1) + " Casas e 1 Hotel" : this.getBuiltHousesNumber() + " Casas";
	}

}
