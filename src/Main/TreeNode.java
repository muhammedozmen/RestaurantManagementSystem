package Main;

public class TreeNode {
	private Cocktail element;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Cocktail newElement) {
        element = newElement;
        left = null;
        right = null;
    }

    public Cocktail getElement() {
        return element;
    }

    public void setElement(Cocktail element) {
        this.element = element;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
