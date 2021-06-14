package cardTypes;

import cards.Name;
import cards.Type;
import cards.Card;

public class TurnU extends Card{

	public TurnU() {
		super();
		cardAttributeType = Type.turn;
		cardAttributeName = Name.turnU;
		cardAttributeMovCount = 2;
	}
	
}
