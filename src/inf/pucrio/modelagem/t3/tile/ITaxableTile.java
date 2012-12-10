package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.NotEnoughMoneyException;
import inf.pucrio.modelagem.t3.Player;

public interface ITaxableTile {

	Player getOwner();
	void collectRent(Player player);
	public void buy(Player player) throws NotEnoughMoneyException;
	public void buy(Player player, int agreedPrice) throws NotEnoughMoneyException;
	
}
