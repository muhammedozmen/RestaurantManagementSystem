package Main;

/* Aromas are kept in enum. Each menu item is splitted and they are appointed with switch case. */

public enum Drink_Aroma {
	
	SWEET("Sweet"),
    SOUR("Sour"),
    BITTER("Bitter"),
    SALTY("Salty"),
    SPICY("Spicy");

	private String name;

    private Drink_Aroma(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
