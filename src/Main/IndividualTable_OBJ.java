package Main;

import java.util.ArrayList;

/* In this class, we create all tables as an object. */

public class IndividualTable_OBJ {
	private String tableName;
    private ArrayList<Cocktail> tableCocktailArraylist = new ArrayList<>();

    public IndividualTable_OBJ(String tableName, ArrayList<Cocktail> tableCocktailArraylist) {
        this.tableName = tableName;
        this.tableCocktailArraylist = tableCocktailArraylist;
    }

    public IndividualTable_OBJ(String tableName) {
        this.tableName = tableName;
    }

    public IndividualTable_OBJ() {

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Cocktail> getTableCocktailArraylist() {
        return tableCocktailArraylist;
    }

    public void setTableCocktailArraylist(ArrayList<Cocktail> tableCocktailArraylist) {
        this.tableCocktailArraylist = tableCocktailArraylist;
    }

    @Override
    public String toString() {
        return tableName + " " + tableCocktailArraylist;
    }
}
