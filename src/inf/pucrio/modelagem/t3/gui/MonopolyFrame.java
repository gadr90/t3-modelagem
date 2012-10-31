package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.Game;
import inf.pucrio.modelagem.t3.Player;
import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.BorderLayout;
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
	//private JLabel hours;
	//private JLabel minutes;
	//private JLabel colon;
	private Game game;
	
	public MonopolyFrame(Game game) {
		super("0921720;0920523");
		this.game = game;
		this.game.addObserver(this);
		System.out.println("Initializing MonopolyFrame...");
		//Graphic initialization
		this.setSize(PositionUtils.BOARD_SIZE_PIXELS + 200, PositionUtils.BOARD_SIZE_PIXELS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		diceButton = new JButton("Jogar Dados");
		diceButton.addMouseListener(new RollDiceButtonMouseListener());
		/*
		hours = new JLabel("00");
		minutes = new JLabel("00");
		colon = new JLabel(":");
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		hours.setFont(font);
		minutes.setFont(font);
		colon.setFont(font);
		*/
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		JPanel eastPanel = new JPanel();
		
		//Adiciona todos os players no tabuleiro representado pelo Panel
		for (Player p : getGame().getPlayers()) {
			centerPanel.add(p.getView());
			p.getView().repaint();
		}
		
		//Desenhando tabuleiro
		try {
			Image image = ImageIO.read(new File("bancoimobiliario1.png")).getScaledInstance(650, 650, java.awt.Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(image);
			JLabel picLabel = new JLabel(icon);
			picLabel.setSize(650, 650);
			picLabel.setVisible(true);
			centerPanel.add(picLabel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		centerPanel.add(minutes, BoxLayout.X_AXIS);
		centerPanel.add(colon, BoxLayout.X_AXIS);
		centerPanel.add(hours, BoxLayout.X_AXIS);
		 */
		eastPanel.add(diceButton, BoxLayout.X_AXIS);
		
		centerPanel.setSize(PositionUtils.BOARD_SIZE_PIXELS, PositionUtils.BOARD_SIZE_PIXELS);
		eastPanel.setSize(200, PositionUtils.BOARD_SIZE_PIXELS);
		
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		System.out.println("Finished Initializing MonopolyFrame.");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Notified of update!");
		Game game = this.getGame();
		Player currentPlayer = game.getCurrentPlayer();
		Point p = PositionUtils.getPositionForIndex(currentPlayer.getCurrentIndex(), game.getPlayers().indexOf(currentPlayer));
		currentPlayer.getView().setBounds(p.x, p.y, 10, 10);
		currentPlayer.getView().repaint();
	}
	
	public Game getGame() {
		return this.game;
	}

	class RollDiceButtonMouseListener extends MouseAdapter {
		private Game getGame(MouseEvent e) {
			return ((MonopolyFrame) ((JComponent) e.getComponent()).getTopLevelAncestor()).getGame();
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Pressed roll dice button");
			getGame(e).doTurn();
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button A");
		}
	}
	
}