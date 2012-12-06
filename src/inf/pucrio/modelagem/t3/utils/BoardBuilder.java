package inf.pucrio.modelagem.t3.utils;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.tile.CompanyTile;
import inf.pucrio.modelagem.t3.tile.FreeStopTile;
import inf.pucrio.modelagem.t3.tile.LuckTile;
import inf.pucrio.modelagem.t3.tile.MoneyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PrisonTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BoardBuilder {
	
	public static ArrayList<MonopolyTile> buildTiles(Game game) {
		ArrayList<MonopolyTile> tiles = new ArrayList<MonopolyTile>();
		MoneyTile startTile;
		MoneyTile loseMoneyTile;
		MoneyTile winMoneyTile;
		PropertyTile propertyTile;
		CompanyTile companyTile;
		LuckTile luckTile;
		PrisonTile prisonTile;
		PrisonTile goToPrisonTile;
		FreeStopTile freeStopTile;

		/* Tile de ponto de partida */
		startTile = new MoneyTile(0, game, 200);
		tiles.add(startTile);
		
		/* Tiles de propriedade */
		propertyTile = new PropertyTile(1, game, "Leblon", 100, 50, 30, 6, 30, 90, 270, 400, 500, null, Color.magenta);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(3, game, "Av. Presidente Vargas", 60, 50, 30, 2, 10, 30, 90, 160, 250, null, Color.magenta);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(4, game, "Av. Nossa S. de Copacabana", 60, 50, 30, 4, 20, 60, 180, 320, 450, null, Color.magenta);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(6, game, "Av. Brigadeiro Faria Lima", 240, 150, 120, 20, 100, 300, 750, 925, 1100, null, Color.cyan);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(8, game, "Av. Rebouças", 220, 150, 110, 18, 90, 250, 700, 875, 1050, null, Color.cyan);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(9, game, "Av. 9 de Julho", 220, 150, 110, 18, 90, 250, 700, 875, 1050, null, Color.cyan);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(11, game, "Av. Europa", 200, 100, 100, 16, 80, 220, 600, 800, 1000, null, Color.DARK_GRAY);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(13, game, "Rua Augusta", 180, 100, 90, 14, 70, 200, 550, 750, 950, null, Color.DARK_GRAY);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(14, game, "Av. Pacaembú", 180, 100, 90, 14, 70, 200, 550, 750, 950, null, Color.DARK_GRAY);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(17, game, "Interlagos", 350, 200, 175, 35, 175, 500, 1100, 1300, 1500, null, Color.orange);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(19, game, "Morumbi", 400, 200, 200, 50, 200, 600, 1400, 1700, 2000, null, Color.orange);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(21, game, "Flamengo", 120, 50, 60, 8, 40, 100, 300, 450, 600, null, Color.red);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(23, game, "Botafogo", 100, 50, 50, 6, 30, 90, 270, 400, 500, null, Color.red);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(26, game, "Av. Brasil", 160, 100, 80, 12, 60, 180, 500, 700, 900, null, Color.yellow);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(28, game, "Av. Paulista", 140, 100, 70, 10, 50, 150, 450, 625, 750, null, Color.yellow);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(29, game, "Jardim Europa", 140, 100, 70, 10, 50, 150, 450, 625, 750, null, Color.yellow);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(31, game, "Copacabana", 260, 150, 130, 22, 110, 330, 800, 975, 1150, null, Color.green);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(33, game, "Av. Vieira Souto", 320, 200, 160, 28, 150, 450, 1000, 1200, 1400, null, Color.green);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(34, game, "Av. Atlântica", 300, 200, 150, 26, 130, 390, 900, 1100, 1275, null, Color.green);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(36, game, "Ipanema", 300, 200, 150, 26, 130, 390, 900, 1100, 1275, null, Color.green);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(38, game, "Jardim Paulista", 280, 150, 140, 24, 120, 360, 850, 1025, 1200, null, Color.blue);
		tiles.add(propertyTile);
		propertyTile = new PropertyTile(39, game, "Brooklin", 260, 150, 130, 22, 110, 330, 800, 975, 1150, null, Color.blue);
		tiles.add(propertyTile);
		
		/* Tiles de sorte/rev�s */
		luckTile = new LuckTile(2, game);
		tiles.add(luckTile);
		luckTile = new LuckTile(12, game);
		tiles.add(luckTile);
		luckTile = new LuckTile(16, game);
		tiles.add(luckTile);
		luckTile = new LuckTile(22, game);
		tiles.add(luckTile);
		luckTile = new LuckTile(27, game);
		tiles.add(luckTile);
		luckTile = new LuckTile(37, game);
		tiles.add(luckTile);
		
		/* Tiles de companhia */
		companyTile = new CompanyTile(5, game, "Companhia Ferroviária", 200, 100, 50, null);
		tiles.add(companyTile);
		companyTile = new CompanyTile(7, game, "Companhia de Viação", 200, 100, 50, null);
		tiles.add(companyTile);
		companyTile = new CompanyTile(15, game, "Companhia de Táxi", 150, 75, 40, null);
		tiles.add(companyTile);
		companyTile = new CompanyTile(25, game, "Companhia de Navegação", 150, 75, 40, null);
		tiles.add(companyTile);
		companyTile = new CompanyTile(32, game, "Companhia de Aviação", 200, 100, 50, null);
		tiles.add(companyTile);
		companyTile = new CompanyTile(35, game, "Companhia de Táxi Aéreo", 200, 100, 50, null);
		tiles.add(companyTile);
		
		/* Tile de prisão */
		prisonTile = new PrisonTile(10, game);
		tiles.add(prisonTile);
		
		/* Tile de vá para prisão */
		goToPrisonTile = new PrisonTile(30, game);
		tiles.add(goToPrisonTile);
		
		/* Tiles de lucros */
		winMoneyTile = new MoneyTile(18, game, 200);
		tiles.add(winMoneyTile);
		
		/* Tiles de impostos */
		loseMoneyTile = new MoneyTile(24, game, -200);
		tiles.add(loseMoneyTile);
		
		/* Tile de Parada livre */
		freeStopTile = new FreeStopTile(20, game);
		tiles.add(freeStopTile);
		
		Collections.sort(tiles, new Comparator<MonopolyTile>() {
	        @Override public int compare(MonopolyTile t1, MonopolyTile t2) {
	            return t1.getIndex() - t2.getIndex();
	        }
	    });
		
		return tiles;		
	}
	
}
