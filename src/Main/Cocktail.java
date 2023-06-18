package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class Cocktail implements Serializable{
	private String coctailName;
	private Drink_Aroma drinkAroma;
	private ArrayList<String> fruits;
    private boolean withAlcohol;
    private int alcoholRate;
    private double price;

    public Cocktail(String name, boolean containsAlcohol, int alcoholRate, ArrayList<String> fruits, Drink_Aroma drinkAroma, double price) {
        this.coctailName = name;
        this.withAlcohol = containsAlcohol;
        this.alcoholRate = alcoholRate;
        this.fruits = fruits;
        this.drinkAroma = drinkAroma;
        this.price = price;
    }

    public String getCoctailName() {
        return coctailName;
    }

    public void setCoctailName(String coctailName) {
        this.coctailName = coctailName;
    }
    
    public Drink_Aroma getDrinkAroma() {
        return drinkAroma;
    }

    public void setDrinkAroma(Drink_Aroma drinkAroma) {
        this.drinkAroma = drinkAroma;
    }
    
    public ArrayList<String> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<String> fruits) {
        this.fruits = fruits;
    }

    public boolean WithAlcohol() {
        return withAlcohol;
    }

    public void setWithAlcohol(boolean withAlcohol) {
        this.withAlcohol = withAlcohol;
    }

    public int getAlcoholRate() {
        return alcoholRate;
    }

    public void setAlcoholRate(int alcoholRate) {
        this.alcoholRate = alcoholRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String string = coctailName + " " + drinkAroma.toString() + " ";

        for (int i = 0; i < fruits.size(); i++) {
            if (i != fruits.size() - 1) {
                string += fruits.get(i) + "-";
            } else {
                string += fruits.get(i);
            }
        }

        string +=  " " + alcoholRate + " " + withAlcohol + " " + price;
        return string;
    }
}
