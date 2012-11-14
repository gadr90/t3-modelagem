package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class OwnableTile extends MonopolyTile {
	
	protected Player owner;

	public OwnableTile(int index, Game game, Player owner) {
		super(index, game);
		this.owner = owner;
	}
	
	public void buy (Player player) {
		this.owner = player;
	}

}
