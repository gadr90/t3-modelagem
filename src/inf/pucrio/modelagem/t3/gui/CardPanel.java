package inf.pucrio.modelagem.t3.gui;

import inf.pucrio.modelagem.t3.card.CompanyCard;
import inf.pucrio.modelagem.t3.card.PropertyCard;
import inf.pucrio.modelagem.t3.tile.CompanyTile;
import inf.pucrio.modelagem.t3.tile.MonopolyTile;
import inf.pucrio.modelagem.t3.tile.PropertyTile;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class CardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CompanyCard companyCard;
	private PropertyCard propertyCard;
	
	public CardPanel(MonopolyTile tile) {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(180, 240);
		this.setBorder(new MatteBorder(5,5,5,5, new Color(0xBBBBBB)));
		
		this.setTile(tile);
	}
	
	public void setTile(MonopolyTile tile) {
		clean();
		this.companyCard = tile instanceof CompanyTile ? ((CompanyTile) tile).getCard() : null;
		this.propertyCard = tile instanceof PropertyTile ? ((PropertyTile) tile).getCard() : null;
		this.add(new JLabel("Carta"));
		
		if (companyCard != null) {
			if (companyCard.getOwner() != null)
				this.add(new JLabel("Dono: " + companyCard.getOwner().getPlayerName()));

			this.add(new JLabel("Companhia: " + companyCard.getName()));	
			this.add(new JLabel("Valor: " + companyCard.getSaleValue()));
		}
		else if (propertyCard != null) {
			if (propertyCard.getOwner() != null)
				this.add(new JLabel("Dono: " + propertyCard.getOwner().getPlayerName()));

			this.add(new JLabel("Propriedade: " + propertyCard.getAddress()));	
			this.add(new JLabel("Valor: " + propertyCard.getSaleValue()));
		}	
	}

	private void clean() {
		this.removeAll();
	}
	
	
	
}
