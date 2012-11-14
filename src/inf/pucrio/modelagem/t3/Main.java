package inf.pucrio.modelagem.t3;

import inf.pucrio.modelagem.t3.gui.MonopolyFrame;

public class Main {
	
	public static Game game;	
	public static MonopolyFrame frame;

	public static void main (String[] args) {
		game = new Game();
		frame = new MonopolyFrame(game);
	}
}