package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;

public class CompanyTile extends OwnableTile implements ITaxableTile {
	
	public CompanyTile(int index, Game game, Player owner) {
		super(index, game, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collectTax(Player player) {
		
	}

}
