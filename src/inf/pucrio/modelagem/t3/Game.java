package inf.pucrio.modelagem.t3;


import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
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
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();
	
	public Game() {
		Color[] availableColors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.magenta, Color.RED, Color.YELLOW};
		for (Color c : availableColors) {
			Player player = new Player(c, "Player");
			players.add(player);
			Point p = PositionUtils.getPositionForIndex(0, players.indexOf(player));
			player.getView().setBounds(new Rectangle(p.x, p.y, 10, 10));
		}
		
		//TODO inicializar posicão das views dos players
	}

	public void doTurn() {
		System.out.println("Doing turn " + currentTurn + " for player " + currentPlayerIndex);
		Player currentPlayer = getCurrentPlayer();
		//TODO classe Dice que é responsável por rolar dados etc
		int roll1 = (int) (Math.floor(Math.random() * 6 + 1));
		int roll2 = (int) (Math.floor(Math.random() * 6 + 1));
		//TODO checar se são iguais, aumentar contador no player, permitir outro roll.
		int totalRoll = roll1 + roll2;
		//DEBUG
		totalRoll = 1;
		currentPlayer.setCurrentIndex( currentPlayer.getCurrentIndex() +  totalRoll );
		
		setChanged();
		notifyObservers();

		currentTurn++;
		currentPlayerIndex++;
		if (currentPlayerIndex > 5)
			currentPlayerIndex = 0;
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
	
}
