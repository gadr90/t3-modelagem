package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.Action;
import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Main;
import inf.pucrio.modelagem.t3.NotEnoughMoneyException;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.card.LuckCard;
import inf.pucrio.modelagem.t3.card.MonopolyCard;
import inf.pucrio.modelagem.t3.tile.ITaxableTile;
import inf.pucrio.modelagem.t3.utils.DeckBuilder;
import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

public class MonopolyFrame extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton diceButton;
	private JButton buyButton;
	private JButton drawCardButton;
	private JButton finishTurnButton;
	private JLabel roll1;
	private JLabel roll2;
	private JLabel player;
	private JLabel playerMoney;
	private Game game;
	private CardPanel cardPanel;
	private JPanel deckPanel;

	public MonopolyFrame(Game game) {
		super("0921720;0920523");
		this.game = game;
		this.game.addObserver(this);
		System.out.println("Initializing MonopolyFrame...");
		// Graphic initialization
		this.setSize(PositionUtils.BOARD_SIZE_PIXELS + 530,
				PositionUtils.BOARD_SIZE_PIXELS + 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		diceButton = new JButton("Jogar Dados");
		diceButton.addActionListener(new RollDiceButtonActionListener());
		buyButton = new JButton("Comprar");
		buyButton.addActionListener(new BuyTerrainButtonActionListener());
		drawCardButton = new JButton("Tirar carta");
		drawCardButton.addActionListener(new DrawCardButtonActionListener());
		finishTurnButton = new JButton("Finalizar turno");
		finishTurnButton.addActionListener(new FinishTurnButtonActionListener());

		roll1 = new JLabel("Dado 1: ");
		roll1.setBorder(new EmptyBorder(5, 5, 5, 5));
		roll2 = new JLabel("Dado 2: ");
		roll2.setBorder(new EmptyBorder(5, 5, 5, 5));
		player = new JLabel("Jogador: "
				+ game.getCurrentPlayer().getPlayerName());
		player.setBorder(new EmptyBorder(5, 5, 5, 5));
		playerMoney = new JLabel("Dinheiro: R$ "
				+ game.getCurrentPlayer().getMoney());
		playerMoney.setBorder(new EmptyBorder(5, 5, 5, 5));

		/*
		 * Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		 * hours.setFont(font); minutes.setFont(font); colon.setFont(font);
		 */

		JPanel centerPanel = new JPanel(null);
		
		JPanel eastPanel = new JPanel(null);
		eastPanel.setBackground(new Color(0xBBBBBB));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.X_AXIS));
		
		JPanel controlsPanel = new JPanel();
		controlsPanel.setBackground(new Color(0xCCCCCC));
		controlsPanel.setBorder(new MatteBorder(10, 10, 10, 10, new Color(0xDDDDDD)));
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		
		cardPanel = new CardPanel(null);
		
		deckPanel = new JPanel();
		deckPanel.setBackground(new Color(0xCCCCCC));
		deckPanel.setBorder(new MatteBorder(10, 10, 10, 0, new Color(0xDDDDDD)));
		deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));
		deckPanel.setSize(300, 650);

		// Adiciona todos os players no tabuleiro representado pelo Panel
		for (Player p : getGame().getPlayers()) {
			centerPanel.add(p.getView());
			p.getView().repaint();
		}

		// Desenhando tabuleiro
		try {
			Image image = ImageIO.read(new File("tabuleiro.jpg"))
					.getScaledInstance(650, 650, java.awt.Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(image);
			JLabel picLabel = new JLabel(icon) {
				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) {
					super.paintComponent(g);
					Graphics2D g2d = (Graphics2D) g;
					PositionUtils.drawDebugLines(this, g2d, false);
				}
			};
			picLabel.setSize(650, 650);
			picLabel.setVisible(true);
			centerPanel.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * centerPanel.add(minutes, BoxLayout.X_AXIS); centerPanel.add(colon,
		 * BoxLayout.X_AXIS); centerPanel.add(hours, BoxLayout.X_AXIS);
		 */

		centerPanel.setSize(PositionUtils.BOARD_SIZE_PIXELS,
				PositionUtils.BOARD_SIZE_PIXELS);

		eastPanel.setSize(500, PositionUtils.BOARD_SIZE_PIXELS);
		deckPanel.setSize(300, PositionUtils.BOARD_SIZE_PIXELS);
		controlsPanel.setSize(200, PositionUtils.BOARD_SIZE_PIXELS);

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);
		eastPanel.add(deckPanel);
		eastPanel.add(controlsPanel);

		controlsPanel.add(roll1);
		controlsPanel.add(roll2);
		controlsPanel.add(player);
		controlsPanel.add(playerMoney);
		controlsPanel.add(Box.createVerticalStrut(20));
		controlsPanel.add(diceButton);
		controlsPanel.add(buyButton);
		controlsPanel.add(drawCardButton);
		controlsPanel.add(finishTurnButton);
		controlsPanel.add(Box.createVerticalStrut(20));
		controlsPanel.add(cardPanel);
		controlsPanel.add(Box.createVerticalGlue());

		this.setVisible(true);
		game.updateInterface();
		System.out.println("Finished Initializing MonopolyFrame.");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Notified of update!");
		Game game = this.getGame();
		Point p = PositionUtils.getPositionForIndex(
				game.getCurrentPlayer().getCurrentIndex(),
				game.getPlayers().indexOf(game.getCurrentPlayer()));
		game.getCurrentPlayer().getView().setBounds(p.x, p.y, 20, 20);

		cardPanel.setTile(game.getCurrentPlayer().getCurrentTile());
		roll1.setText("Dado 1: " + String.valueOf(game.getCurrentRoll1()));
		roll2.setText("Dado 2: " + String.valueOf(game.getCurrentRoll2()));
		player.setText("Jogador: " + game.getCurrentPlayer().getPlayerName());
		playerMoney.setText("Dinheiro: R$ "
				+ game.getCurrentPlayer().getMoney());
		
		deckPanel.removeAll();
		deckPanel.repaint();
		for (MonopolyCard card : game.getCurrentPlayer().getDeck()) {
			deckPanel.add(new PlayerCardPanel(card));	
		}
		
		drawCardButton.setEnabled(false);
		buyButton.setEnabled(false);
		buyButton.setText("Comprar");
		for (Action a : game.getAvailableActions()) {
			switch (a) {
			case ROLL_DICE:
				this.diceButton.setEnabled(true);
				this.finishTurnButton.setEnabled(false);
				break;

			case BUY:
				this.drawCardButton.setEnabled(false);
				this.buyButton.setEnabled(true);
			
				ITaxableTile tile = (ITaxableTile) game.getCurrentPlayer().getCurrentTile();
				if (tile.getOwner() != null)
					buyButton.setText("Comprar de " + tile.getOwner().getPlayerName());
				break;

			case DRAW_CARD:
				this.drawCardButton.setEnabled(true);
				this.buyButton.setEnabled(false);
				break;

			case END_TURN:
				this.diceButton.setEnabled(false);
				this.finishTurnButton.setEnabled(true);
				break;
			}
		}
	}

	public Game getGame() {
		return this.game;
	}

	class RollDiceButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!diceButton.isEnabled())
				return;
			
			System.out.println("Pressed roll dice button");
			Main.game.startTurn();			
		}
	}
	
	class BuyTerrainButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!buyButton.isEnabled())
				return;

			Player player = Main.game.getCurrentPlayer();
			ITaxableTile tile = ((ITaxableTile) player.getCurrentTile());
			String result = null;
			System.out.println("Pressed buy terrain button");
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
	}
	
	class DrawCardButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!drawCardButton.isEnabled())
				return;
			
			System.out.println("Pressed draw card button");
			LuckCard card = Main.game.getLuckDeck().poll();
			Main.game.getCurrentPlayer().setLuckCardDrawn(true);
			if (card.getValue() != 0) {

				//Trata o caso de retirar o dinheiro dos demais jogadores.
				if (card.isBetCard()) {
					for (Player p : Main.game.getPlayers()) {
						if (p != Main.game.getCurrentPlayer()) {
							p.addMoney( - LuckCard.BET_LUCK_CARD_VALUE);
						}
					}
				}
				
				Main.game.getCurrentPlayer().addMoney(card.getValue());
				Main.game.updateInterface();
			}
			
			JOptionPane.showMessageDialog(Main.frame, card.getDescription(), "Sorte ou Revés!", JOptionPane.WARNING_MESSAGE);
			
			if (card.isPrison() && card.isGoodLuck()) {
				Main.game.getCurrentPlayer().getDeck().add(card);
			}
			else {
				//Insere no final
				Main.game.getLuckDeck().add(card);
			}
			
		}
	}
	
	class FinishTurnButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!finishTurnButton.isEnabled())
				return;
			
			System.out.println("Pressed finish turn button");
			Main.game.finishTurn();
		}
	}

}