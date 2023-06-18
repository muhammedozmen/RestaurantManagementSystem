package Main;

import java.util.ArrayList;

/*All cocktails are inside the arraylist as an object. When user add a new drink, arraylist will be created and drink goes into arraylist
as an object. Cocktails in menu are save as an object inside the file. With binary search tree algorithm, we can sort all objects in the file
alphabetically. In Coctails_Tree class we used a TreeNode. Each branch of Binary Search Tree is essentially a node.
*/

public class Coctails_Tree {
	private TreeNode root;
	private ArrayList<Cocktail> cocktailArrayList = new ArrayList<>();
    private int size;
    
    public Coctails_Tree() {
        root = null;
        size = 0;
    }

    public TreeNode createNewNode(Cocktail newCocktail) {
        return new TreeNode(newCocktail);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void setCocktailArrayList(ArrayList<Cocktail> cocktailArrayList) {
        this.cocktailArrayList = cocktailArrayList;
    }

    public ArrayList<Cocktail> getCocktailArrayList() {
        return cocktailArrayList;
    }
    
    public int getSize() {
        return size;
    }

    public boolean insert(Cocktail newCocktail) {
        if (root == null) {
            root = createNewNode(newCocktail);
        } else {
            TreeNode parent = null;
            TreeNode current = root;

            while (current != null) {
                if (newCocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) < 0) {
                    parent = current;
                    current = current.getLeft();
                } else if (newCocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) > 0) {
                    parent = current;
                    current = current.getRight();
                } else
                    return false;
            }

            if (newCocktail.getCoctailName().compareTo(parent.getElement().getCoctailName()) < 0) {
                parent.setLeft(createNewNode(newCocktail));
            } else {
                parent.setRight(createNewNode(newCocktail));
            }
        }

        size++;
        return true;
    }

    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft());
        System.out.println(root.getElement() + " ");
        inorder(root.getRight());
    }

    public void inorderCreatingArrayList(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderCreatingArrayList(root.getLeft());
        cocktailArrayList.add(root.getElement());
        inorderCreatingArrayList(root.getRight());
    }


    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft());
        inorder(root.getRight());
        System.out.println(root.getElement() + " ");
    }

    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getElement());
        inorder(root.getLeft());
        inorder(root.getRight());
    }

    public boolean delete(Cocktail cocktail) {
        TreeNode parent = null;
        TreeNode current = root;
        while (current != null) {
            if (cocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) < 0) {
                parent = current;
                current = current.getLeft();
            } else if (cocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) > 0) {
                parent = current;
                current = current.getRight();
            } else
               break;
        }

        if (current == null)
            return false;
        if (current.getLeft() == null) {

            if (parent == null) {
                root = current.getRight();
            } else {
                if (cocktail.getCoctailName().compareTo(parent.getElement().getCoctailName()) < 0) {
                    parent.setLeft(createNewNode(cocktail));
                } else
                    parent.setRight(createNewNode(cocktail));
            }
        } else {
            TreeNode parentOfRightMost = current;
            TreeNode rightMost = current.getLeft();
            while (rightMost.getRight() != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.getRight();
            }
            current.setElement(rightMost.getElement());

            if (parentOfRightMost.getRight() == rightMost) {
                parentOfRightMost.setRight(rightMost.getLeft());
            } else {
                parentOfRightMost.setLeft(rightMost.getLeft());
            }
        }

        size--;
        return true;
    }
    public boolean search(Cocktail cocktail) {
        TreeNode current = root;

        while (current != null) {
            if (cocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) < 0) {
                current = current.getLeft();
            } else if (cocktail.getCoctailName().compareTo(current.getElement().getCoctailName()) > 0) {
                current = current.getRight();
            } else
                return true;
        }
        return false;
    }
}
