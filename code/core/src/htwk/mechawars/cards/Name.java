package htwk.mechawars.cards;

public enum Name {
	mov1("1 Vor"),
	mov2("2 Vor"),
	mov3("3 Vor"),
	movB("Rueckwaerts"),
	turnR("Rechtsdrehung"),
	turnU("Kehrtwendung"),
	turnL("Linksdrehung");
	
	private final String displayName;
	private Name(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * getter function for the Enum-content
	 * @return the String of the Enumeration
	 */
	public String get_Name() {
		return this.displayName;
	}
	
}
