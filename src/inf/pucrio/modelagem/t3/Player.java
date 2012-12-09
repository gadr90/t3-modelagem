package inf.pucrio.modelagem.t3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import inf.pucrio.modelagem.t3.card.MonopolyCard;
import inf.pucrio.modelagem.t3.gui.PlayerView;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;

public class Player {
	
	private PlayerView view;
	private int currentIndex = 0;
	private int doubleRolls = 0;
	private boolean luckCardDrawn = false;
	private boolean arrested = false;
	private int turnsArrested = 0;
	private Color color;
	private String playerName;
	private int money;
	private Game game;
	private List<MonopolyCard> deck;
	
	public Player(Color color, String playerName, Game game) {
		this.color = color;
		this.view = new PlayerView(color);
		this.playerName = playerName;
		this.money = 8*1 + 10*5 + 10*10 + 10*50 + 8*100 + 2*500;
		this.game = game;
		this.deck = new ArrayList<MonopolyCard>();
	}

	public PlayerView getView() {
		return view;
	}

	public void setView(PlayerView view) {
		this.view = view;
	}

	public int getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		if (currentIndex > 39) {
			currentIndex -= 40;
			// Ao passar pelo ponto inicial, ganha 200 reais.
			this.addMoney(200);
		}
		else if (currentIndex == Game.GO_TO_PRISON_TILE_INDEX) {
			this.setArrested(true);
			this.currentIndex = Game.PRISON_TILE_INDEX;
			return;
		}
		this.currentIndex = currentIndex;
	}

	public int getDoubleRollsThisTurn() {
		return doubleRolls;
	}

	public void setDoubleRollsThisTurn(int doubleRollsThisTurn) {
		this.doubleRolls = doubleRollsThisTurn;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void addDoubleRoll() {
		this.doubleRolls++;		
		if (this.isArrested()) {
			this.setArrested(false);
		}
		else if (this.doubleRolls == 3) {
			this.setArrested(true);
			this.setCurrentIndex(Game.PRISON_TILE_INDEX);
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
	public MonopolyTile getCurrentTile() {
		System.out.println(this.currentIndex);
		return this.game.getTiles().get(this.currentIndex);
	}

	public List<MonopolyCard> getDeck() {
		return deck;
	}

	public void setDeck(List<MonopolyCard> deck) {
		this.deck = deck;
	}

	public void addMoney(int value) {
		this.money += value;
	}

	public boolean isLuckCardDrawn() {
		return luckCardDrawn;
	}

	public void setLuckCardDrawn(boolean luckCardDrawn) {
		this.luckCardDrawn = luckCardDrawn;
	}

	public boolean isArrested() {
		return arrested;
	}

	public void setArrested(boolean arrested) {
		this.arrested = arrested;
		// Se o jogador saiu da prisão, reset o número de double rolls
		if (this.arrested == false) {
			this.doubleRolls = 0;
			this.turnsArrested = 0;
		}
	}

	public int getTurnsArrested() {
		return turnsArrested;
	}

	public void setTurnsArrested(int turnsArrested) {
		this.turnsArrested = turnsArrested;
	}

	public void addTurnArrested() {
		this.turnsArrested++;		
	}
	
	

}
