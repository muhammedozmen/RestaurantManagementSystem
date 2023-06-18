package Main;

public class Node {
	private Cocktail cocktail;
    private Node link;

    public Node() {
        link = null;
        cocktail = null;
    }

    public Node(Cocktail newCocktail) {
        cocktail = newCocktail;
        link = null;
    }

    public Node(Cocktail newCocktail, Node linkValue) {
        cocktail = newCocktail;
        link = linkValue;
    }

    public Cocktail getCocktail() {
        return cocktail;
    }

    public void setCocktail(Cocktail cocktail) {
        this.cocktail = cocktail;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }
}
