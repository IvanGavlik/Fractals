package application;

/**
 * Only to values no more
 */
public enum Fill {
	
	RED ("red"),
	WHITE("white");
	
	private String value;
	
	
	private Fill(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
