/* Nick Winkler -- CS202 -- Fant -- List.java
This file contains the interface for
 */
public class List {

    public List() {
        this.list = new Node[11];

    }

    public void display() {
        int n = 0;
        while(n < 10) {
            display(this.list[n]);
            ++n;
        }
    }

    public boolean insertData(Service to_add) {
        //get the hash value of the service
        int hash = hashFunction(to_add);
        System.out.println(hash);
        if(this.list[hash] == null) {

            this.list[hash] = new Node();
            this.list[hash].insertData(to_add);

            return true;
        }
        else
            return insertData(list[hash], to_add);

    }

    public Service retrieve(String name) {
        int hash = hashFunction(name);
        System.out.println(hash);

        if(this.list[hash] == null)
            return null;

        else if(this.list[hash].hasSameName(name)) {
            return this.list[hash].getData();
        }
        else if(!this.list[hash].hasSameName(name) && this.list[hash].goNext() == null) {
            return null;
        }
        else
            return retrieve(this.list[hash], name);


    }

    private Service retrieve(Node head, String name) {
        if(head == null)
            return null;
        if(head.hasSameName(name)) {
            return head.getData();
        } else {
            retrieve(head.goNext(), name);
            return null;
        }
    }

    private int hashFunction(Service to_add) {
        String name = to_add.getName();
        int hash = name.hashCode();

        hash %= 11;

        return hash;

    }
    private int hashFunction(String name) {
        int hash = name.hashCode();
        hash %= 11;
        return hash;
    }
    private boolean insertData(Node head, Service to_add) {
        if(head == null)
            return false;
        insertData(head.goNext(), to_add);

        head = new Node();
        head.insertData(to_add);
        return true;
    }

    private void display(Node head) {
        if(head == null)
            return;

        head.display();
        display(head.goNext());

        return;
    }
    private Node list[];

}
