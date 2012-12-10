package inf.pucrio.modelagem.t3.tile;

import inf.pucrio.modelagem.t3.NotEnoughMoneyException;
import inf.pucrio.modelagem.t3.Player;

public interface ITaxableTile {
	// Retorna o dono deste tile
	Player getOwner();
	// Faz com que player pague o aluguel para o owner
	void collectRent(Player player);
	// Player compra esse tile
	public void buy(Player player) throws NotEnoughMoneyException;
	// Player compra esse tile pelo preço agreedPrice
	public void buy(Player player, int agreedPrice) throws NotEnoughMoneyException;
	// Owner vende esse terreno para o banco para recuperar dinheiro
	public void sell();
	
}
