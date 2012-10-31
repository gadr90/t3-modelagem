package inf.pucrio.modelagem.t3.utils;

import java.awt.Point;

public class PositionUtils {
	
	public static final int BOARD_TILES = 13;
	public static final int BOARD_SIZE_PIXELS = 650;
	public static final int TILE_SIZE_PIXELS = BOARD_SIZE_PIXELS/BOARD_TILES;
	
	/**
	 * Devolve as coordernadas X,Y de um player dentro de um tile.
	 * @param index �ndice do jogador
	 * @param tileWidth N�mero de tiles horizontais
	 */
	public static Point playerCoordsInTile(int index, int tileWidth) {
		return new Point(index % tileWidth, (int) Math.floor(index / tileWidth));
	}
	
	public static Point tileCoordsInBoard(int index) {
		if (index > 39) {
			index -= 40;
		}
		if (index == 0) return new Point(11, 11);
		if (index == 10) return new Point(0, 11);
		if (index == 20) return new Point(0, 0);
		if (index == 30) return new Point(11, 0);
		
		if (index < 10) {
			return new Point((11 - index), 11);			
		}
		else if (index < 20) {
			return new Point(0, 11 - (index - 10));
		}
		else if (index < 30) {
			return new Point((index - 21) + 2, 0);			
		}
		else {
			return new Point(11, (index - 31) + 2);			
		}
	}
	
	public static Point relativeToGlobal(Point p) {
		return new Point(p.x * TILE_SIZE_PIXELS, p.y * TILE_SIZE_PIXELS);
	}
	
	public static Point tileRelativeToGlobal(Point p, int width) {
		if (width == 2) {
			return new Point(p.x * 25, p.y * 33);			
		}
		else if (width == 3) {
			return new Point(p.x * 33, p.y * 25);			
		}
		return null;
	}
	
	public static Point getPositionForIndex(int boardIndex, int playerIndex) {
		Point tilePosition = PositionUtils.tileCoordsInBoard(boardIndex);
		Point positionInTile = PositionUtils.playerCoordsInTile(playerIndex, PositionUtils.getWidthForTile(boardIndex));
		Point globalTilePosition = PositionUtils.relativeToGlobal(tilePosition);
		Point globalPositionInTile = PositionUtils.tileRelativeToGlobal(positionInTile, PositionUtils.getWidthForTile(boardIndex));
		int x = (int) (globalPositionInTile.getX() + globalTilePosition.getX());
		int y = (int) (globalPositionInTile.getY() + globalTilePosition.getY());
		
		System.out.println("Gerando posição para boardIndex: " + boardIndex );
		System.out.println("Tile position" + tilePosition);
		System.out.println("Position in Tile " + positionInTile);
		System.out.println("Global tile position" + globalTilePosition);
		System.out.println("Global position in tile" + globalPositionInTile);
		System.out.println("Posicionando em " + x + ", " + y);
		return new Point(x,y);
	}

	public static int getWidthForTile(int index) {
		if (index > 39) {
			index -= 40;
		}
		
		if ( (index > 0 && index < 10) || (index > 20 && index < 30)) {
			return 2;			
		}
		else {
			return 3;
		}
	}
}
