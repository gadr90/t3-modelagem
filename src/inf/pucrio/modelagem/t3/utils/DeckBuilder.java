package inf.pucrio.modelagem.t3.utils;

import inf.pucrio.modelagem.t3.card.LuckCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DeckBuilder {

	public static Queue<LuckCard> buildLuckDeck() {
		LinkedList<LuckCard> deck = new LinkedList<LuckCard>();
		LuckCard card;

		/* Cartas de sorte */
		card = new LuckCard(true, false, 100, "Sorte");
		deck.add(card);
		
		/* Cartas de revés */
		card = new LuckCard(false, false, 100, "Revés");
		deck.add(card);
		
		/* Cartas de prisão - sorte */
		card = new LuckCard(true, true, 0, "Prisão Sorte");
		deck.add(card);
		
		/* Cartas de prisão - revés */
		card = new LuckCard(false, true, 0, "Prisão Revés");
		deck.add(card);
		
		
		Collections.shuffle(deck);
		
		return deck;		
	}
	
}
