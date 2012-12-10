package inf.pucrio.modelagem.t3.card;

import inf.pucrio.modelagem.t3.Game;

public class LuckCard extends MonopolyCard {

	public static int BET_LUCK_CARD_VALUE = 50;
	
	private boolean goodLuck;
	private boolean prison;
	private boolean betCard;
	private boolean startCard;
	private int value;
	private String description;
	
	public LuckCard(Game game, boolean goodLuck, boolean prison, int value,
			String description) {
		super(game);
		this.goodLuck = goodLuck;
		this.prison = prison;
		this.value = value;
		this.description = description;
		this.betCard = false;
		this.startCard = false;
	}
	
	public LuckCard(Game game, boolean goodLuck, boolean prison, int value,
			String description, boolean betCard, boolean startCard) {
		this(game, goodLuck, prison, value, description);
		this.betCard = betCard;
		this.startCard = startCard;
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
		//Caso especial - a carta de aposta
		if (this.betCard) {
			return (game.getPlayers().size() - 1) * BET_LUCK_CARD_VALUE;
		}
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isBetCard() {
		return this.betCard;
	}
	
	public boolean isStartCard() {
		return this.startCard;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
