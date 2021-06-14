package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cardTypes.Mov1;
import cardTypes.Mov2;
import cardTypes.Mov3;
import cardTypes.MovB;
import cardTypes.TurnL;
import cardTypes.TurnR;
import cardTypes.TurnU;

public class CardFunctions{
	

	/**
	 * Fill the empty cardDeck-Array with 
	 * # 18 Mov1, TurnR, TurnL
	 * # 12 Mov2
	 * # 6  Mov3, MovB, TurnU
	 * 
	 * @param cardDeck
	 * @return The initial cardDeck
	 */
	public static Card[] initDeck(Card[] cardDeck) {
		for (int i = 0; i < 18; i+=1) {
			cardDeck[i] 	= new Mov1();
			cardDeck[i+18] 	= new TurnR();
			cardDeck[i+36] 	= new TurnL();
		}
		for (int i = 54; i < 60; i+=1) {
			cardDeck[i] 	= new Mov3();
			cardDeck[i+6] 	= new MovB();
			cardDeck[i+12] 	= new TurnU();
		}
		for (int i = 72; i < 84; i+=1) {
			cardDeck[i] 	= new Mov2();
		}
		return cardDeck;
	}
	
	/**
	 * Shuffles the cards, which were created before.
	 * 
	 * @param oDeck -> includes the cards to shuffle
	 * @return Array of cards, that are shuffled
	 */
	public static Card[] shuffle(Card[] oDeck) {
		 List<Card> deck = new ArrayList<>(Arrays.asList(oDeck));
	     List<Card> shuffled = new ArrayList<>();

	        Collections.shuffle(deck);
	        shuffled.addAll(deck);
	        
	        return shuffled.toArray(new Card[shuffled.size()]);
	}

}
