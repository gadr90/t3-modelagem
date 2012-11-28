package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.card.CompanyCard;
import inf.pucrio.modelagem.t3.card.PropertyCard;
import inf.pucrio.modelagem.t3.tile.CompanyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class CardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyCard companyCard;
	private PropertyCard propertyCard;
	private JTextArea text;
	
	public CardPanel(MonopolyTile tile) {
		super();
		text = new JTextArea();
		this.setBorder(null);
		this.setLayout(null);
		this.setSize(150, 300);
		this.setPreferredSize(new Dimension(100, 300));
		text.setBounds(0, 0, 200, 300);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false); 
		text.setOpaque(false);
		text.setCursor(null);  
		text.setFocusable(false);
		
		this.add(text);
		this.setTile(tile);
	}
	
	public void setTile(MonopolyTile tile) {
		clean();
		this.companyCard = tile instanceof CompanyTile ? ((CompanyTile) tile).getCard() : null;
		this.propertyCard = tile instanceof PropertyTile ? ((PropertyTile) tile).getCard() : null;
		
		if (companyCard != null) {
			if (companyCard.getOwner() != null)
				text.append("Dono: " + companyCard.getOwner().getPlayerName() + "\n\n");

			text.append("Companhia: " + companyCard.getName() + "\n\n");
			text.append("Valor: " + companyCard.getSaleValue() + "\n\n");
		}
		else if (propertyCard != null) {
			if (propertyCard.getOwner() != null)
				text.append("Dono: " + propertyCard.getOwner().getPlayerName() + "\n\n");

			text.append("Propriedade: " + propertyCard.getAddress() + "\n\n");
			text.append("Valor: " + propertyCard.getSaleValue() + "\n\n");
		}
	}

	private void clean() {
		this.text.setText("");
	}
	
	
	
}
