package inf.pucrio.modelagem.t3.utils;

import java.awt.Point;

public class PositionUtils {
	
	public static final int BOARD_TILES = 13;
	public static final int BOARD_SIZE_PIXELS = 690;
	public static final int TILE_SIZE_PIXELS = BOARD_SIZE_PIXELS/BOARD_TILES;
	
	/**
	 * Devolve as coordernadas X,Y de um player dentro de um tile.
	 * @param index índice do jogador
	 * @param tileWidth Número de tiles horizontais
	 */
	public static Point playerCoordsInTile(int index, int tileWidth) {
		return new Point(index % tileWidth, (int) Math.floor(index / tileWidth));
	}
	
	//TODO
	public static Point tileCoordsInBoard(int index) {
		return new Point();
	}
	
	public static Point relativeToGlobal(Point p) {
		return new Point(p.x * TILE_SIZE_PIXELS, p.y * TILE_SIZE_PIXELS);
	}

}
