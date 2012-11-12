package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class OwnableTile extends MonopolyTile {
	
	private Player owner;

	public OwnableTile(int index, Game game, Player owner) {
		super(index, game);
		this.owner = owner;
	}

}