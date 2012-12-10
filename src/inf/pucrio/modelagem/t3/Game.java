package inf.pucrio.modelagem.t3;


import inf.pucrio.modelagem.t3.card.LuckCard;
import inf.pucrio.modelagem.t3.tile.FreeStopTile;
import inf.pucrio.modelagem.t3.tile.ITaxableTile;
import inf.pucrio.modelagem.t3.tile.LuckTile;
import inf.pucrio.modelagem.t3.tile.MoneyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PrisonTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;
import inf.pucrio.modelagem.t3.utils.BoardBuilder;
import inf.pucrio.modelagem.t3.utils.DeckBuilder;
import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Queue;

import javax.swing.JOptionPane;

/** 
 * Controlador do Jogo
 * @author Guilherme
 *
 */
public class Game extends Observable {

	private static final int TURNS_SINCE_ARREST_FREEDOM = 4;

	private static final int PRISON_FEE = 50;

	public static final boolean DEBUG = false;
	
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
		int numberOfPlayers = 0;
		String message = "Quantas pessoas vão jogar? Máximo: 6";
		while (numberOfPlayers == 0) {
			try {
				numberOfPlayers = Integer.parseInt(JOptionPane.showInputDialog(message));
				if (numberOfPlayers > 6) {
					numberOfPlayers = 0;
					throw new IllegalArgumentException();
				}
		    } catch (NumberFormatException exception) {
		    	JOptionPane.showMessageDialog(Main.frame, "Digite um numero inteiro!");
		    }
			catch (IllegalArgumentException exception2) {
		    	JOptionPane.showMessageDialog(Main.frame, "Digite um numero menor que  ou igual a 6!");
			}
		}			
		
		//Adiciona os players
		for (i = 0; i < numberOfPlayers; i++) {
			Player player = new Player(availableColors[i], colorNames[i], this);
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
		currentPlayer.setCurrentRoll(totalRoll);
		
		if (currentPlayer.getTurnsArrested() == TURNS_SINCE_ARREST_FREEDOM) {
			// Jogador já está preso há três rodadas. Ele é liberado e paga 50.
			currentPlayer.addMoney( - PRISON_FEE);
			currentPlayer.setArrested(false);
		}
		
		if (!currentPlayer.isArrested()) {
			currentPlayer.setCurrentIndex( currentPlayer.getCurrentIndex() + totalRoll );
		}
		currentTurn++;
		
		if (currentPlayer.getCurrentTile() instanceof FreeStopTile) {
			System.out.println("Caiu em tile sem ação");
		}
		else if (currentPlayer.getCurrentTile() instanceof PrisonTile) {
			System.out.println("Caiu em tile sem ação");
		}
		else if (currentPlayer.getCurrentTile() instanceof MoneyTile) {
			System.out.println("Caiu em tile de dinheiro");
			MoneyTile tile = (MoneyTile) currentPlayer.getCurrentTile();
			currentPlayer.addMoney(tile.getValue());
		} else if (currentPlayer.getCurrentTile() instanceof ITaxableTile) {
			System.out.println("Pagou aluguel!");
			ITaxableTile tile = (ITaxableTile) currentPlayer.getCurrentTile();
			tile.collectRent(currentPlayer);
		}

		updateInterface();
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
		
		this.setCurrentPlayerIndex(this.getCurrentPlayerIndex() + 1);		
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
		if (currentPlayerIndex == this.getPlayers().size())
			this.currentPlayerIndex = 0;
		else
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
		return this.players.get(this.currentPlayerIndex > 0 ? this.currentPlayerIndex - 1 : this.getPlayers().size() - 1);
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
	
	public List<PropertyTile> getAllPropertiesWithColor(Color color) {
		ArrayList<PropertyTile> tiles = new ArrayList<PropertyTile>();
		for (MonopolyTile c : this.getTiles()) {
			if (c instanceof PropertyTile) {
				PropertyTile t = (PropertyTile) c;
				if (t.getCard().getColor().equals(color)) {
					tiles.add(t);
				}
			}
		}
		return tiles;
	}
	
	public Player findPlayerByName(String name) {
		for (Player p : this.getPlayers())
			if (p.getPlayerName().equals(name)) return p;
		
		return null;
	}


	public void playerGotBankrupt(Player player) {
		JOptionPane.showMessageDialog(Main.frame, "O jogador " + player.getPlayerName() + " não conseguiu honrar suas dívidas e faliu. Boa sorte da próxima vez!");
		this.getPlayers().remove(player);
		// Passa a vez para o próximo jogador.
		this.setCurrentPlayerIndex(currentPlayerIndex + 1);
		
		this.checkEndGame();
	}


	// Condição de término do jogo
	private void checkEndGame() {
		if (this.getPlayers().size() == 1) {
			JOptionPane.showMessageDialog(Main.frame, "Parabéns! Você venceu, "+ this.getPlayers().get(0).getPlayerName() +" !", "Vitória!", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}

	public void buyTerrain() {
			Player player = this.getCurrentPlayer();
			ITaxableTile tile = ((ITaxableTile) player.getCurrentTile());
			String result = null;
			//Tem dono
			if (tile.getOwner() != null) {
				result = JOptionPane.showInputDialog(Main.frame, "Deseja vender esse terreno por quanto, " + tile.getOwner().getPlayerName() + "?");
				if (result == null)
					return;
				
				try {
			        int price = Integer.parseInt(result);
			        tile.buy(player, price);
			    } catch (NumberFormatException exception) {
			    	JOptionPane.showMessageDialog(Main.frame, "Digite um numero inteiro!");
			    } catch (NotEnoughMoneyException e1) {
			    	JOptionPane.showMessageDialog(Main.frame, e1.getMessage());
				}
			}
			else {
				try {
					tile.buy(player);
				} catch (NotEnoughMoneyException e1) {
			    	JOptionPane.showMessageDialog(Main.frame, e1.getMessage());
				}
			}
		}


	public void drawCard() {
		LuckCard card = this.getLuckDeck().poll();
		this.getCurrentPlayer().setLuckCardDrawn(true);
		
		//Trata o caso de retirar o dinheiro dos demais jogadores.
		if (card.isBetCard()) {
			for (Player p : this.getPlayers()) {
				if (p != this.getCurrentPlayer()) {
					p.addMoney( - LuckCard.BET_LUCK_CARD_VALUE);
				}
			}
		}
		// Carta de vá para início
		else if (card.isStartCard()){
			this.getCurrentPlayer().setCurrentIndex(START_TILE_INDEX_WIN_MONEY);
		}
		// Carta de vá para prisão
		else if (card.isPrison() && !card.isGoodLuck()) {
			this.getCurrentPlayer().setArrested(true);
			this.getCurrentPlayer().setCurrentIndex(PRISON_TILE_INDEX);					
		}
		
		if (card.getValue() != 0) {
			this.getCurrentPlayer().addMoney(card.getValue());
		}
		
		JOptionPane.showMessageDialog(Main.frame, card.getDescription(), "Sorte ou Revés!", JOptionPane.WARNING_MESSAGE);
		
		if (card.isPrison() && card.isGoodLuck()) {
			this.getCurrentPlayer().getDeck().add(card);
		}
		else {
			//Insere no final
			this.getLuckDeck().add(card);
		}
		
		this.updateInterface();
	}

	public void rollDice() {
		int numberOfPositions = 0;
		
		if (DEBUG) {
			String result = JOptionPane.showInputDialog(Main.frame, "Numero de tiles a andar?");
			if (result == null)
				return;
			if (result.equals("double"))  {
				this.startTurn(6, true);
			}
			else {
				try {
					numberOfPositions = Integer.parseInt(result);
					if (numberOfPositions > 40)
						throw new IllegalArgumentException();

					this.startTurn(numberOfPositions, false);	
			    } catch (NumberFormatException exception) {
			    	JOptionPane.showMessageDialog(Main.frame, "Digite um numero inteiro!");
			    }
				catch (IllegalArgumentException exception2) {
			    	JOptionPane.showMessageDialog(Main.frame, "Digite um numero menor que 40!");
				}
			}
			
		} else {
			this.startTurn();				
		}
	}
}
