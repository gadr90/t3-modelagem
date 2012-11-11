package inf.pucrio.modelagem.t3;

import java.awt.Color;

import inf.pucrio.modelagem.t3.gui.PlayerView;

public class Player {
	
	private PlayerView view;
	private int currentIndex = 0;
	private int doubleRollsThisTurn = 0;
	private Color color;
	private String playerName;
	private int money;
	
	public Player(Color color, String playerName) {
		this.color = color;
		this.view = new PlayerView(color);
		this.playerName = playerName;
		this.money = 8*1 + 10*5 + 10*10 + 10*50 + 8*100 + 2*500;
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
		this.currentIndex = currentIndex;
	}

	public int getDoubleRollsThisTurn() {
		return doubleRollsThisTurn;
	}

	public void setDoubleRollsThisTurn(int doubleRollsThisTurn) {
		this.doubleRollsThisTurn = doubleRollsThisTurn;
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
		this.doubleRollsThisTurn++;		
	}

}
