package inf.pucrio.modelagem.t3.card;

public class LuckCard extends MonopolyCard {

	private boolean goodLuck;
	private boolean prison;
	private int value;
	private String description;
	
	public LuckCard(boolean goodLuck, boolean prison, int value,
			String description) {
		super();
		this.goodLuck = goodLuck;
		this.prison = prison;
		this.value = value;
		this.description = description;
	}

	public boolean isGoodLuck() {
		return goodLuck;
	}

	public void setGoodLuck(boolean goodLuck) {
		this.goodLuck = goodLuck;
	}

	public boolean isPrison() {
		return prison;
	}

	public void setPrison(boolean prison) {
		this.prison = prison;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
