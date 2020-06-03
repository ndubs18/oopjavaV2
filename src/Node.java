/* Nick Winkler -- CS202 -- Fant -- Node.java
* This file holds the class interface for our Node class*/
public class Node {

    public boolean insertData(Service to_add) {
        this.data = new Service(to_add);
        return true;
    }

    public final Service goNext() {
        return this.next;
    }
    public final void setNext(Service to_set) {
        this.data = to_set;
    }



    private Service data;
    private Service next;
}

