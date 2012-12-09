package inf.pucrio.modelagem.t3;


import inf.pucrio.modelagem.t3.card.LuckCard;
import inf.pucrio.modelagem.t3.tile.FreeStopTile;
import inf.pucrio.modelagem.t3.tile.ITaxableTile;
import inf.pucrio.modelagem.t3.tile.LuckTile;
import inf.pucrio.modelagem.t3.tile.MoneyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PrisonTile;
import inf.pucrio.modelagem.t3.utils.BoardBuilder;
import inf.pucrio.modelagem.t3.utils.DeckBuilder;
import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

/** 
 * Controlador do Jogo
 * @author Guilherme
 *
 */
public class Game extends Observable {

	private static final int TURNS_SINCE_ARREST_FREEDOM = 4;

	private static final int PRISON_FEE = 50;

	public static final boolean DEBUG = true;
	
	public static final int NUMBER_OF_PLAYERS = 6;
	public static final int START_TILE_INDEX = 0;
	public static final int PRISON_TILE_INDEX = 10;
	public static final int START_TILE_INDEX_WIN_MONEY = 40;
	public static final int GO_TO_PRISON_TILE_INDEX = 30;
	private int currentTurn = 1;
	private int currentPlayerIndex = 0;
	private Dice dice = new Dice();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<MonopolyTile> tiles;
	private Queue<LuckCard> luckDeck;
	private String playerMessage;
	private boolean turnStarted = false;
	
	public Game() {
		//Todas as cores disponíveis para os usuários
		Color[] availableColors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.magenta, Color.RED, Color.YELLOW};
		String[] colorNames = {"Azul", "Ciano", "Verde", "Magenta", "Vermelho", "Amarelo"};
		int i = 0;
		//Adiciona os players
		for (Color c : availableColors) {
			Player player = new Player(c, colorNames[i++], this);
			players.add(player);
			Point p = PositionUtils.getPositionForIndex(0, players.indexOf(player));
			player.getView().setBounds(p.x, p.y, 20, 20);
		}
		
		//Constrói o tabuleiro
		tiles = BoardBuilder.buildTiles(this);
		luckDeck = DeckBuilder.buildLuckDeck(this);
	}


	public void startTurn() {
		this.startTurn(0, false);
	}
	
	public void startTurn(int numberOfPositions, boolean doubleRoll) {
		int totalRoll = 0;
		System.out.println("Starting turn " + currentTurn + " for player " + currentPlayerIndex);
		this.turnStarted = true;
		Player currentPlayer = getCurrentPlayer();
		
		if (DEBUG) {
			dice.currentRollTotal = numberOfPositions;
			dice.currentRoll1 = (int) Math.floor((float)numberOfPositions/2);
			dice.currentRoll2 = (int) Math.ceil((float)numberOfPositions/2);
			dice.isDoubleRoll = doubleRoll;
		}
		else {
			dice.roll();
		}

		if (dice.isDoubleRoll) {
			currentPlayer.addDoubleRoll();
		}
		totalRoll = dice.currentRollTotal;	
		
		if (currentPlayer.getTurnsArrested() == TURNS_SINCE_ARREST_FREEDOM) {
			// Jogador já está preso há três rodadas. Ele é liberado e paga 50.
			currentPlayer.addMoney( - PRISON_FEE);
			currentPlayer.setArrested(false);
		}
		
		if (!currentPlayer.isArrested()) {
			currentPlayer.setCurrentIndex( currentPlayer.getCurrentIndex() + totalRoll );
		}
		currentTurn++;
		updateInterface();
		
		//TODO Melhorar a logica de qual tile o player est�
		if (currentPlayer.getCurrentTile() instanceof FreeStopTile) {
			System.out.println("Caiu em tile sem ação");
			updateInterface();
		}
		else if (currentPlayer.getCurrentTile() instanceof PrisonTile) {
			System.out.println("Caiu em tile sem ação");
			updateInterface();
		}
		else if (currentPlayer.getCurrentTile() instanceof MoneyTile) {
			System.out.println("Caiu em tile sem ação");
			MoneyTile tile = (MoneyTile) currentPlayer.getCurrentTile();
			currentPlayer.addMoney(tile.getValue());
			updateInterface();
		}

	}
	
	public void finishTurn() {
		System.out.println("Finishing turn " + currentTurn + " for player " + currentPlayerIndex);
		this.turnStarted = false;
		Player currentPlayer = this.getCurrentPlayer();
		currentPlayer.setDoubleRollsThisTurn(0);
		currentPlayer.setLuckCardDrawn(false);

		if (currentPlayer.isArrested()) {
			currentPlayer.addTurnArrested();
		}
		
		currentPlayerIndex++;
		if (currentPlayerIndex > 5)
			currentPlayerIndex = 0;
		
		updateInterface();
	}

	public List<Action> getAvailableActions() {
		List<Action> array = new ArrayList<Action>();
		if (this.isTurnStarted()) {
			array.add(Action.END_TURN);
		}
		else {
			array.add(Action.ROLL_DICE);
		}
		
		if (this.getCurrentPlayer().getCurrentTile() instanceof ITaxableTile) {
			ITaxableTile tile = (ITaxableTile) this.getCurrentPlayer().getCurrentTile();
			if (! this.getCurrentPlayer().equals(tile.getOwner()))
				array.add(Action.BUY);
		}
		else if (this.getCurrentPlayer().getCurrentTile() instanceof LuckTile && !this.getCurrentPlayer().isLuckCardDrawn()) {
			array.add(Action.DRAW_CARD);
		}
		
		return array;
	}
	
	public void updateInterface() {
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

	public ArrayList<MonopolyTile> getTiles() {
		return tiles;
	}

	public void setTiles(ArrayList<MonopolyTile> tiles) {
		this.tiles = tiles;
	}

	public Player getCurrentPlayer() {
		return this.players.get(this.currentPlayerIndex);
	}
	
	public Player getLastPlayer() {
		return this.players.get(this.currentPlayerIndex > 0 ? this.currentPlayerIndex - 1 : 5);
	}

	public int getCurrentRoll1() {
		return dice.currentRoll1;
	}

	public int getCurrentRoll2() {
		return dice.currentRoll2;
	}

	public String getPlayerMessage() {
		return playerMessage;
	}

	public void setPlayerMessage(String playerMessage) {
		this.playerMessage = playerMessage;
	}

	public boolean isTurnStarted() {
		return turnStarted;
	}

	public void setTurnStarted(boolean turnStarted) {
		this.turnStarted = turnStarted;
	}

	public Queue<LuckCard> getLuckDeck() {
		return luckDeck;
	}

	public void setLuckDeck(Queue<LuckCard> luckDeck) {
		this.luckDeck = luckDeck;
	}
	
}
