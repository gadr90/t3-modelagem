package inf.pucrio.modelagem.t3;

public class Dice {
	
	public int currentRoll1 = 0;
	public int currentRoll2 = 0;
	public int currentRollTotal = 0;
	public boolean isDoubleRoll = false;
	
	public void roll() {
		currentRoll1 = (int) (Math.floor(Math.random() * 6 + 1));
		currentRoll2 = (int) (Math.floor(Math.random() * 6 + 1));
		currentRollTotal = currentRoll1 + currentRoll2;
		isDoubleRoll = currentRoll1 == currentRoll2;
	}

}
