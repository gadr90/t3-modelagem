package inf.pucrio.modelagem.t3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import inf.pucrio.modelagem.t3.card.CompanyCard;
import inf.pucrio.modelagem.t3.card.MonopolyCard;
import inf.pucrio.modelagem.t3.card.PropertyCard;
import inf.pucrio.modelagem.t3.gui.PlayerView;
import inf.pucrio.modelagem.t3.tile.CompanyTile;
import inf.pucrio.modelagem.t3.tile.ITaxableTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

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
	private int currentRoll = 0;
	
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
	
	public List<ITaxableTile> getTaxableTiles() {
		ArrayList<ITaxableTile> tiles = new ArrayList<ITaxableTile>();
		
		for(MonopolyCard c : this.getDeck()) {
			if (c instanceof PropertyCard) {
				tiles.add(((PropertyCard) c).getTile());
			}
			else if (c instanceof CompanyCard) {
				tiles.add(((CompanyCard) c).getTile());
			}
		}
		
		return tiles;
	}

	public boolean addMoney(int value) {
		List<ITaxableTile> sellableTiles = getTaxableTiles();
		// Enquanto a transação resultar em saldo negativo, venda suas propriedades
		while (this.money + value < 0 && sellableTiles.size() > 0) {
			ITaxableTile tile = sellableTiles.get(0);
			
			// Company é vendida diretamente
			if (tile instanceof CompanyTile) {
				tile.sell();
			}
			// Property tem cada uma das suas construções vendidas antes de vender o terreno
			else if (tile instanceof PropertyTile) {
				PropertyTile ptile = (PropertyTile) tile;
				// Tenta vender uma construção da propriedade
				if (ptile.getCard().getBuiltHousesNumber() > 0) {
					ptile.getCard().sellConstruction();
				}
				// Caso contrário, vende o terreno inteiro.
				else {
					ptile.sell();
				}
			}
			sellableTiles = getTaxableTiles();
		}
		
		this.money += value;
		
		// Jogador vendeu todas as suas posses e não conseguiu honrar a dívida. Sai do jogo.
		if (this.money < 0) {
			Main.game.playerGotBankrupt(this);
			// Saldo restante negativo
			return false;
		}
		
		// Saldo restante positivo
		return true;
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

	public int getCurrentRoll() {
		return this.currentRoll;
	}
	
	public void setCurrentRoll(int value) {
		this.currentRoll = value;
	}
	
	

}
