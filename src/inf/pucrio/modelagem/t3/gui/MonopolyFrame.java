package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.utils.PositionUtils;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MonopolyFrame extends JFrame implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonA;
	private JButton buttonB;
	private JLabel hours;
	private JLabel minutes;
	private JLabel colon;
	
	public MonopolyFrame() {
		super("0921720;0920523");
		System.out.println("Initializing MonopolyFrame...");
		//Graphic initialization
		this.setSize(PositionUtils.BOARD_SIZE_PIXELS + 200, PositionUtils.BOARD_SIZE_PIXELS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		buttonA = new JButton("Botão A");
		buttonA.addMouseListener(new ButtonAMouseListener());
		buttonB = new JButton("Botão B");
		buttonB.addMouseListener(new ButtonBMouseListener());
		hours = new JLabel("00");
		minutes = new JLabel("00");
		colon = new JLabel(":");
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 30);
		hours.setFont(font);
		minutes.setFont(font);
		colon.setFont(font);
		
		JPanel centerPanel = new JPanel();
		JPanel eastPanel = new JPanel();		

		centerPanel.add(minutes, BoxLayout.X_AXIS);
		centerPanel.add(colon, BoxLayout.X_AXIS);
		centerPanel.add(hours, BoxLayout.X_AXIS);

		eastPanel.add(buttonB, BoxLayout.X_AXIS);
		eastPanel.add(buttonA, BoxLayout.X_AXIS);
		
		centerPanel.setSize(PositionUtils.BOARD_SIZE_PIXELS, PositionUtils.BOARD_SIZE_PIXELS);
		eastPanel.setSize(200, PositionUtils.BOARD_SIZE_PIXELS);
		
		this.getContentPane().add(centerPanel, BorderLayout.CENTER);
		this.getContentPane().add(eastPanel, BorderLayout.EAST);
		
		this.setVisible(true);
		System.out.println("Finished Initializing ClockFrame.");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Notified of update!");
	}
	

	class ButtonAMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Pressed button A");
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button A");
		}
	}
	
	class ButtonBMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("Pressed button B");
			super.mousePressed(e);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Released button B");
			super.mouseReleased(e);
		}
	}
}