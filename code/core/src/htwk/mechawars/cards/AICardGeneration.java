package htwk.mechawars.cards;

import java.util.LinkedList;

public class AICardGeneration {
	
	public static LinkedList<Card> generateRandomAICards(int playercount)
	{
		int[] moveCounts = {-1,1,2,3};
		LinkedList<Card> Cards = new LinkedList<Card>();
		System.out.println("");
		System.out.println("Player: "+(playercount+1));
		System.out.println();
		System.out.println("Cards:  ");
		for(int i = 0; i<5 ;i++)
		{
			Type type = Type.mov;
			int dir = (int)Math.round(Math.random());
			switch (dir){
			case 0: type = Type.mov;break;
			case 1: type = Type.turn;break;
			}
			int moveSetter = (int)Math.round(Math.random()*(4)-0.5);

			byte attributemovCount = (byte) moveCounts[moveSetter];
			int cardAttributePriority = (int)Math.round(Math.random()*3);
			Card card = new Card(type, attributemovCount, cardAttributePriority);
			Cards.add(card);
			System.out.println(card.toString()+"    Card number"+1);	
		}
		System.out.println("*************************************************************");
		return Cards;
				}

}
