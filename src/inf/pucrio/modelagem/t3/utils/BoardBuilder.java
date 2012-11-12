package inf.pucrio.modelagem.t3.utils;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.tile.CompanyTile;
import inf.pucrio.modelagem.t3.tile.LuckTile;
import inf.pucrio.modelagem.t3.tile.MoneyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PrisonTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Color;
import java.util.ArrayList;

public class BoardBuilder {

	public static ArrayList<MonopolyTile> buildTiles(Game game) {
		ArrayList<MonopolyTile> tiles = new ArrayList<>();
		MoneyTile startTile;
		MoneyTile loseMoneyTile;
		MoneyTile winMoneyTile;
		PropertyTile propertyTile;
		CompanyTile companyTile;
		LuckTile luckTile;
		PrisonTile prisonTile;
		PrisonTile goToPrisonTile;

		/* Tile de ponto de partida */
		startTile = new MoneyTile(0, game, 200, true);
		tiles.add(startTile);
		/* Tiles de propriedade */
		propertyTile = new PropertyTile(1, game, null, Color.pink);
		tiles.add(propertyTile);
		/* Tiles de companhia */
		/* Tiles de prisão */
		/* Tiles de vá para prisão */
		/* Tiles de lucros */
		/* Tiles de impostos */
		
		return tiles;		
	}
	
}
