package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.Main;
import inf.pucrio.modelagem.t3.card.CompanyCard;
import inf.pucrio.modelagem.t3.card.LuckCard;
import inf.pucrio.modelagem.t3.card.MonopolyCard;
import inf.pucrio.modelagem.t3.card.PropertyCard;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PlayerCardPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private PropertyCard propertyCard;
	private CompanyCard companyCard;
	private LuckCard luckCard;
	private JLabel housesBuiltLabel;
	
	public PlayerCardPanel(MonopolyCard card) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(100, 100));
		this.setSize(new Dimension(100, 100));
		propertyCard = card instanceof PropertyCard ? (PropertyCard) card : null;
		companyCard = card instanceof CompanyCard ? (CompanyCard) card : null;
		luckCard = card instanceof LuckCard ? (LuckCard) card : null;
		
		if (luckCard != null) {
			JButton button = new JButton("Sair da prisão");
			button.addActionListener(new LuckButtonActionListener());
			this.add(new JLabel("Saída livre da prisão"));
			this.add(button);
			if (!Main.game.getCurrentPlayer().isArrested()) {
				button.setEnabled(false);
			}
		}
		else {
			if (propertyCard != null) {
				this.setBackground(propertyCard.getColor());
				String label = propertyCard.getAddress().length() > 20 ? propertyCard.getAddress().substring(0, 20) + "..." : propertyCard.getAddress(); 
				this.add(new JLabel(label));
				housesBuiltLabel = new JLabel(propertyCard.getLabel());
				this.add(housesBuiltLabel);
				JButton button = new JButton("Construir");
				button.addActionListener(new BuildButtonActionListener());
				this.add(button);
			}
			else if (companyCard != null) {
				String label = companyCard.getName().length() > 20 ? companyCard.getName().substring(0, 20) + "..." : companyCard.getName(); 
				this.add(new JLabel(label));			
			}
			
			this.add(new JButton("Vender"));
		}
	}
	
	class BuildButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Pressed build button");
			
			if (propertyCard.build()) {
				housesBuiltLabel.setText(propertyCard.getLabel());
				Main.game.updateInterface();
			}
			else if (propertyCard.getBuiltHousesNumber() > PropertyCard.MAX_NUMBER_HOUSES) {
				JOptionPane.showMessageDialog(Main.frame, "Você já construiu tudo que podia aqui!");
			}
			else {
				JOptionPane.showMessageDialog(Main.frame, "Você precisa ser dono de todos os terrenos dessa cor para construir e todas têm que ter um número mínimo de construções!");
			}
		}
	}
	
	class LuckButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Pressed get out of jail button");
			Main.game.getCurrentPlayer().getDeck().remove(luckCard);
			Main.game.getLuckDeck().add(luckCard);
			Main.game.getCurrentPlayer().setArrested(false);
			Main.game.updateInterface();
		}
	}
	
}
