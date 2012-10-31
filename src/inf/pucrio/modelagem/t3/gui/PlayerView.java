package inf.pucrio.modelagem.t3.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JLabel;

public class PlayerView extends JLabel {
	
	private Color color;

	public PlayerView(Color c) {
		this.color = c;
		this.setSize(20,20);
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
	   super.paintComponent(g);
	   Graphics2D g2d = (Graphics2D)g;
	   Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, 20, 20);
	   g2d.setColor(color);
	   g2d.fill(circle);
	}
	
}
