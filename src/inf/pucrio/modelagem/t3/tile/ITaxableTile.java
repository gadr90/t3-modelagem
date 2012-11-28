package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.Player;

public interface ITaxableTile {

	Player getOwner();
	void collectTax(Player player);
	public void buy(Player player);
	
}
