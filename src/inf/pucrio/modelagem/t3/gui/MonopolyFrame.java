package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.tile.OwnableTile;
import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private JLabel playerTile;
	private Game game;

	public MonopolyFrame(Game game) {
		super("0921720;0920523");
		this.game = game;
		this.game.addObserver(this);
		System.out.println("Initializing MonopolyFrame...");
		// Graphic initialization
		this.setSize(PositionUtils.BOARD_SIZE_PIXELS + 230,
				PositionUtils.BOARD_SIZE_PIXELS + 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		diceButton = new JButton("Jogar Dados");
		diceButton.addMouseListener(new RollDiceButtonMouseListener());
		buyButton = new JButton("Comprar");
		buyButton.addMouseListener(new BuyTerrainButtonMouseListener());
		drawCardButton = new JButton("Tirar carta");
		drawCardButton.addMouseListener(new DrawCardButtonMouseListener());
		finishTurnButton = new JButton("Finalizar turno");
		finishTurnButton.addMouseListener(new FinishTurnButtonMouseListener());

		roll1 = new JLabel("Dado 1: ");
		roll2 = new JLabel("Dado 2: ");
		player = new JLabel("Jogador: "
				+ game.getCurrentPlayer().getPlayerName());
		playerMoney = new JLabel("Dinheiro: R$ "
				+ game.getCurrentPlayer().getMoney());
		playerTile = new JLabel("Casa atual: "
				+ game.getCurrentPlayer().getCurrentTile().getClass().getSimpleName());

		/*
		 * Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		 * hours.setFont(font); minutes.setFont(font); colon.setFont(font);
		 */

		JPanel centerPanel = new JPanel(null);

		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

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
		eastPanel.setSize(200, PositionUtils.BOARD_SIZE_PIXELS);

		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);

		eastPanel.add(diceButton);
		eastPanel.add(roll1);
		eastPanel.add(roll2);
		eastPanel.add(player);
		eastPanel.add(playerMoney);
		eastPanel.add(playerTile);
		eastPanel.add(buyButton);
		eastPanel.add(drawCardButton);
		eastPanel.add(finishTurnButton);

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

		roll1.setText("Dado 1: " + String.valueOf(game.getCurrentRoll1()));
		roll2.setText("Dado 2: " + String.valueOf(game.getCurrentRoll2()));
		player.setText("Jogador: " + game.getCurrentPlayer().getPlayerName());
		playerMoney.setText("Dinheiro: R$ "
				+ game.getCurrentPlayer().getMoney());
		playerTile.setText("Casa atual: "
				+ game.getCurrentPlayer().getCurrentTile().getClass().getSimpleName());
		
		//TODO - disponibilizar um array de PossibleActionsThisTurn e usar isso para desabilitar botões
		if (game.isTurnStarted()) {
			this.diceButton.setEnabled(false);
			this.finishTurnButton.setEnabled(true);
		}
		else {
			this.diceButton.setEnabled(true);
			this.finishTurnButton.setEnabled(false);
		}
		
		if (game.getCurrentPlayer().getCurrentTile().getClass().getSimpleName().equals("PropertyTile")
			|| game.getCurrentPlayer().getCurrentTile().getClass().getSimpleName().equals("CompanyTile")) {
			this.drawCardButton.setEnabled(false);
			this.buyButton.setEnabled(true);
		}
		else if (game.getCurrentPlayer().getCurrentTile().getClass().getSimpleName().equals("LuckTile")) {
			this.drawCardButton.setEnabled(true);
			this.buyButton.setEnabled(false);
		}
		else {
			this.drawCardButton.setEnabled(false);
			this.buyButton.setEnabled(false);			
		}
	}

	public Game getGame() {
		return this.game;
	}

	class RollDiceButtonMouseListener extends MouseAdapter {
		private Game getGame(MouseEvent e) {
			return ((MonopolyFrame) ((JComponent) e.getComponent())
					.getTopLevelAncestor()).getGame();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!diceButton.isEnabled())
				return;
			
			System.out.println("Pressed roll dice button");
			getGame(e).startTurn();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button A");
		}
	}
	
	class BuyTerrainButtonMouseListener extends MouseAdapter {
		private Game getGame(MouseEvent e) {
			return ((MonopolyFrame) ((JComponent) e.getComponent())
					.getTopLevelAncestor()).getGame();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!buyButton.isEnabled())
				return;
			
			System.out.println("Pressed buy terrain button");
			Player player = getGame(e).getCurrentPlayer();
			OwnableTile tile = ((OwnableTile) player.getCurrentTile());
			tile.buy(player);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button");
		}
	}
	
	class DrawCardButtonMouseListener extends MouseAdapter {
		private Game getGame(MouseEvent e) {
			return ((MonopolyFrame) ((JComponent) e.getComponent())
					.getTopLevelAncestor()).getGame();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!drawCardButton.isEnabled())
				return;
			
			System.out.println("Pressed draw card button");
			// TODO mostrar carta drawn
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button");
		}
	}
	
	class FinishTurnButtonMouseListener extends MouseAdapter {
		private Game getGame(MouseEvent e) {
			return ((MonopolyFrame) ((JComponent) e.getComponent())
					.getTopLevelAncestor()).getGame();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!finishTurnButton.isEnabled())
				return;
			
			System.out.println("Pressed finish turn button");
			getGame(e).finishTurn();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button");
		}
	}

}