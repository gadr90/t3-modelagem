package inf.pucrio.modelagem.t3;


import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

/** 
 * Controlador do Jogo
 * @author Guilherme
 *
 */
public class Game extends Observable {
	
	public static final int NUMBER_OF_PLAYERS = 6;
	private int currentTurn = 1;
	private int currentPlayerIndex = 0;
	private int currentRoll1 = 0;
	private int currentRoll2 = 0;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();
	
	public Game() {
		Color[] availableColors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.magenta, Color.RED, Color.YELLOW};
		String[] colorNames = {"Azul", "Ciano", "Verde", "Magenta", "Vermelho", "Amarelo"};
		int i = 0;
		for (Color c : availableColors) {
			Player player = new Player(c, colorNames[i++]);
			players.add(player);
			Point p = PositionUtils.getPositionForIndex(0, players.indexOf(player));
			player.getView().setBounds(p.x, p.y, 20, 20);
		}
	}

	public void doTurn() {
		System.out.println("Doing turn " + currentTurn + " for player " + currentPlayerIndex);
		Player currentPlayer = getCurrentPlayer();
		//TODO classe Dice que � respons�vel por rolar dados etc
		currentRoll1 = (int) (Math.floor(Math.random() * 6 + 1));
		currentRoll2 = (int) (Math.floor(Math.random() * 6 + 1));
		//TODO checar se s�o iguais, aumentar contador no player, permitir outro roll.
		int totalRoll = currentRoll1 + currentRoll2;
		//DEBUG
		//totalRoll = 1;
		currentPlayer.setCurrentIndex( currentPlayer.getCurrentIndex() + totalRoll );

		currentTurn++;
		currentPlayerIndex++;
		if (currentPlayerIndex > 5)
			currentPlayerIndex = 0;
		
		setChanged();
		notifyObservers();
	}

	public int getCurrentTurn() {
		return currentTurn;
	}

	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	public int getCurrentPlayerIndex() {
		return currentPlayerIndex;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<BoardTile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<BoardTile> tiles) {
		this.tiles = tiles;
	}

	public Player getCurrentPlayer() {
		return this.players.get(this.currentPlayerIndex);
	}
	
	public Player getLastPlayer() {
		return this.players.get(this.currentPlayerIndex > 0 ? this.currentPlayerIndex - 1 : 5);
	}

	public int getCurrentRoll1() {
		return currentRoll1;
	}

	public void setCurrentRoll1(int currentRoll1) {
		this.currentRoll1 = currentRoll1;
	}

	public int getCurrentRoll2() {
		return currentRoll2;
	}

	public void setCurrentRoll2(int currentRoll2) {
		this.currentRoll2 = currentRoll2;
	}
	
	
}
