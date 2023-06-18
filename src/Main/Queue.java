package Main;

/* We wanted to use queue because in management section if we want to send orders in order we should have used queue. 
 * According to the queue algorithm, new orders come down the list and the first order is delivered first.
 * So we can deliver all orders starting from the top. 
 */

public class Queue {
	private Node front;
    private Node rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public void initializeQueue() {
        front = null;
        rear = null;
    }

    public Cocktail getFront() {
        assert (front != null);
        return front.getCocktail();
    }

    public Cocktail getRear() {
        assert (rear != null);
        return rear.getCocktail();
    }

    public void addQueue(Cocktail newCocktail) {
        Node a = new Node(newCocktail);

        if (front == null) {
            front = rear = a;
        } else {
            rear.setLink(a);
            rear = rear.getLink();
        }
    }

    public void deleteQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            front = front.getLink();
            if (front == null) {
                rear = null;
            }
        }
    }

    public void outputQueue() {
        if (!isEmpty()) {
            Node c = new Node();
            c = front;
            while (c != null) {
                System.out.println(c.getCocktail());
                c = c.getLink();
            }
        }
    }
}
