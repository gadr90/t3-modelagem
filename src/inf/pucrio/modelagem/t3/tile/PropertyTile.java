package inf.pucrio.modelagem.t3.tile;

import java.awt.Color;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class PropertyTile extends OwnableTile implements ITaxableTile {

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
	
	
	public PropertyTile(int index, Game game, String address, int saleValue, int constructionValue, int mortgageValue, 
						int noConstructionRent, int oneHouseRent, int twoHousesRent, int threeHousesRent,
						int fourHousesRent, int hotelRent, Player owner, Color color) {
		super(index, game, owner);
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

	@Override
	public void collectTax(Player player) {
		// TODO: Checar número de construções e cobrar de acordo com respectivo valor
	}
	
	public void buy(Player player) {
		this.owner = player;
		player.reduceMoney(this.saleValue);
	}

}
