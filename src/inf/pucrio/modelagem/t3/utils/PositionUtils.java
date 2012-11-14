package inf.pucrio.modelagem.t3.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JComponent;

public class PositionUtils {

	public static final int BOARD_TILES = 13;
	public static final int BOARD_SIZE_PIXELS = 650;
	public static final int TILE_SIZE_PIXELS = BOARD_SIZE_PIXELS / BOARD_TILES;

	/**
	 * Devolve as coordernadas X,Y de um player dentro de um tile.
	 * 
	 * @param index
	 *            índice do jogador
	 * @param tileWidth
	 *            Número de tiles horizontais
	 */
	public static Point playerCoordsInTile(int index, int tileWidth) {
		return new Point(index % tileWidth, (int) Math.floor(index / tileWidth));
	}

	public static Point tileCoordsInBoard(int index) {
		if (index > 39) {
			index -= 40;
		}
		if (index == 0)
			return new Point(11, 11);
		if (index == 10)
			return new Point(0, 11);
		if (index == 20)
			return new Point(0, 0);
		if (index == 30)
			return new Point(11, 0);

		if (index < 10)
			return new Point((11 - index), 11);
		if (index < 20)
			return new Point(0, 11 - (index - 10));
		if (index < 30)
			return new Point((index - 21) + 2, 0);
		return new Point(11, (index - 31) + 2);
	}

	public static Point relativeToGlobal(Point p) {
		return new Point(p.x * TILE_SIZE_PIXELS, p.y * TILE_SIZE_PIXELS);
	}

	public static Point tileRelativeToGlobal(Point p) {
		return new Point(p.x * TILE_SIZE_PIXELS / 2, p.y * TILE_SIZE_PIXELS / 2);
	}

	public static Point getPositionForIndex(int boardIndex, int playerIndex) {
		Point tilePosition = PositionUtils.tileCoordsInBoard(boardIndex);
		Point positionInTile = PositionUtils.playerCoordsInTile(playerIndex,
				PositionUtils.getWidthForTile(boardIndex));
		Point globalTilePosition = PositionUtils.relativeToGlobal(tilePosition);
		Point globalPositionInTile = PositionUtils
				.tileRelativeToGlobal(positionInTile);
		int x = (int) (globalPositionInTile.getX() + globalTilePosition.getX());
		int y = (int) (globalPositionInTile.getY() + globalTilePosition.getY());

		System.out.println("Gerando posição para boardIndex: " + boardIndex);
		System.out.println("Tile position" + tilePosition);
		System.out.println("Position in Tile " + positionInTile);
		System.out.println("Global tile position" + globalTilePosition);
		System.out.println("Global position in tile" + globalPositionInTile);
		System.out.println("Posicionando em " + x + ", " + y);
		return new Point(x, y);
	}

	public static int getWidthForTile(int index) {
		if (index > 39) {
			index -= 40;
		}

		if ((index > 0 && index < 10) || (index > 20 && index < 30)) {
			return 2;
		} else {
			return 3;
		}
	}

	public static void drawDebugLines(JComponent jComponent, Graphics2D g2d, boolean clear) {
		g2d.setColor(Color.orange);
		g2d.setStroke(new BasicStroke(2));
        
        Dimension size = jComponent.getSize();
        Insets insets = jComponent.getInsets();
        int w = size.width - insets.left - insets.right;
        int h = size.height - insets.top - insets.bottom;
         
        /*for(int i=100; i<w - TILE_SIZE_PIXELS; i=i+TILE_SIZE_PIXELS)
        {
            g2d.drawLine(0, i, BOARD_SIZE_PIXELS, i);
            g2d.drawLine(i, 0, i, BOARD_SIZE_PIXELS);
        }*/
        //g2d.clearRect(100, 100, 450, 450);
	}
}
