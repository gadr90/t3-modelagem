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

}
