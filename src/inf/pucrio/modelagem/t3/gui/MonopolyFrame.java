package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.Action;
import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Main;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.card.LuckCard;
import inf.pucrio.modelagem.t3.tile.ITaxableTile;
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
	private JButton buildHouseButton;
	private JButton buildHotelButton;
	private JButton finishTurnButton;
	private JLabel roll1;
	private JLabel roll2;
	private JLabel player;
	private JLabel playerMoney;
	private Game game;
	private CardPanel cardPanel;

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
		
		JPanel deckPanel = new JPanel();
		deckPanel.setBackground(new Color(0xCCCCCC));
		deckPanel.setBorder(new MatteBorder(10, 10, 10, 0, new Color(0xDDDDDD)));
		deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));

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
		deckPanel.add(Box.createHorizontalStrut(300));
		deckPanel.add(Box.createVerticalGlue());
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
		
		this.drawCardButton.setEnabled(false);
		this.buyButton.setEnabled(false);
		for (Action a : game.getAvailableActions()) {
			switch (a) {
			case ROLL_DICE:
				this.diceButton.setEnabled(true);
				this.finishTurnButton.setEnabled(false);
				break;

			case BUY:
				this.drawCardButton.setEnabled(false);
				this.buyButton.setEnabled(true);
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
			
			System.out.println("Pressed buy terrain button");
			Player player = Main.game.getCurrentPlayer();
			ITaxableTile tile = ((ITaxableTile) player.getCurrentTile());
			tile.buy(player);
		}
	}
	
	class DrawCardButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (!drawCardButton.isEnabled())
				return;
			
			System.out.println("Pressed draw card button");
			// TODO mostrar carta drawn
			LuckCard card = Main.game.getLuckDeck().poll();
			Main.game.getCurrentPlayer().setLuckCardDrawn(true);
			if (card.getValue() != 0) {
				Main.game.getCurrentPlayer().addMoney(card.getValue());
				Main.game.updateInterface();
			}
			JOptionPane.showMessageDialog(Main.frame, card.getDescription(), "Sorte ou Revés!", JOptionPane.WARNING_MESSAGE);
			//Insere no final
			Main.game.getLuckDeck().add(card);
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