/* Nick Winkler -- CS202 -- Fant -- Node.java
* This file holds the class interface for our Node class*/
public class Node {

    public Node() {
        this.data = null;
        this.next = null;
    }

    public boolean insertData(Service to_add) {
        if(to_add instanceof FoodDelivery) {
            FoodDelivery food = (FoodDelivery)to_add;
            this.data = new FoodDelivery(food);
            return true;
        }
        else if(to_add instanceof Landscaping) {
            Landscaping land = (Landscaping)to_add;
            this.data = new Landscaping(land);
            return true;
        } else {
            BabySitting baby = (BabySitting)to_add;
            this.data = new BabySitting(baby);
            return true;
        }
    }

    public final void display() {
        this.data.display();
    }

    public boolean hasSameName(String to_compare) {
        if(this.data.getName().compareTo(to_compare) == 0)
            return true;
        else
            return false;
    }

    public final Node goNext() {
        return this.next;
    }
    public final void setNext(Service to_set) {
        this.data = to_set;
    }

    public Service getData() {
        return this.data;
    }

    private Service data;
    private Node next;
}

