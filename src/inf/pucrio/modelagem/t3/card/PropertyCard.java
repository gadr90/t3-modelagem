package inf.pucrio.modelagem.t3.card;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Main;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

public class PropertyCard extends MonopolyCard {

	public static final int MAX_NUMBER_HOUSES = 4;
	//private static Color[] availableColors = {Color.GREEN, Color.DARK_GRAY, Color.BLUE, Color.CYAN, Color.magenta, Color.RED, Color.orange, Color.YELLOW};
	private Color color;
	private String address;
	private int saleValue;
	private int constructionValue;
	private int mortgageValue;
	private int builtHousesNumber = 0;
	private PropertyTile tile;
	private Player owner;
	private HashMap<Integer, Integer> rentMap;
	
	public PropertyCard(Game game, String address, int saleValue, int constructionValue, int mortgageValue, 
						int noConstructionRent, int oneHouseRent, int twoHousesRent, int threeHousesRent,
						int fourHousesRent, int hotelRent, Player owner, Color color) {
		super(game);
		this.owner = owner;
		this.address = address;
		this.saleValue = saleValue;
		this.constructionValue = constructionValue;
		this.mortgageValue = mortgageValue;
		this.rentMap = new HashMap<Integer, Integer>();
		this.rentMap.put(0, noConstructionRent);
		this.rentMap.put(1, oneHouseRent);
		this.rentMap.put(2, twoHousesRent);
		this.rentMap.put(3, threeHousesRent);
		this.rentMap.put(4, fourHousesRent);
		this.rentMap.put(5, hotelRent);
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
		if (this.owner != null)
			this.owner.getDeck().add(this);
	}

	public int getBuiltHousesNumber() {
		return builtHousesNumber;
	}
	
	public void setBuildHousesNumber(int builtHousesNumber) {
		this.builtHousesNumber = builtHousesNumber;
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
			if (this.getOwner().getMoney() > this.getConstructionValue()) {
				this.builtHousesNumber++;
				this.getOwner().addMoney( - this.getConstructionValue());
			}
			else {
				JOptionPane.showMessageDialog(Main.frame, "Você não tem dinheiro suficiente para construir!");
			}
			return true;
		}
		return false;
	}
	
	// Vende uma construção para o banco por metade do preço.
	public void sellConstruction() {
		if (this.getBuiltHousesNumber() > 0) {
			this.getOwner().addMoney( this.getConstructionValue() / 2 );
			this.builtHousesNumber--;
		}
	}

	public String getLabel() {
		return this.getBuiltHousesNumber() > 4 ? (this.getBuiltHousesNumber() - 1) + " Casas e 1 Hotel" : this.getBuiltHousesNumber() + " Casas";
	}

	public HashMap<Integer, Integer> getRentMap() {
		return rentMap;
	}

	public PropertyTile getTile() {
		return tile;
	}

	public void setTile(PropertyTile tile) {
		this.tile = tile;
	}

}
