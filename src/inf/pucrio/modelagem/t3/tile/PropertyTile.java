package inf.pucrio.modelagem.t3.tile;

import java.awt.Color;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class PropertyTile extends OwnableTile implements ITaxableTile {

	private static Color[] availableColors = {Color.GREEN, Color.DARK_GRAY, Color.BLUE, Color.CYAN, Color.magenta, Color.RED, Color.orange, Color.YELLOW};
	private Color color;

	public PropertyTile(int index, Game game, Player owner, Color color) {
		super(index, game, owner);
		this.color = color;
	}

	@Override
	public void collectTax(Player player) {
	}

}
