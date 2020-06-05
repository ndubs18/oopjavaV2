public class Btree {
    public Btree() {
        root = null;
    }

    public void display() {
        if(root == null)
            return;
        else
            display(root);
    }

    private void display(Bnode root) {
        if(root == null)
            return;

        display(root.goLeft());
        //if(root.goMiddle() != null)
        //display(root.goMiddle());
        root.display();
        display(root.goRight());

    }
    public int addService(Service to_add) {
        if(root == null ) {
            root = new Bnode();
            root.addData(to_add);
            return 1;
        }
        else if(root.goLeft() == null && root.goRight() == null) {
            int added = root.addData(to_add);
            if(added == 1)
                return 1;
                //if there are two keys in the leaf Bnode
            else if(added == 0) {
                //store a reference to original root Bnode
                Bnode temp = root;
                //assign a new Bnode to root
                root = new Bnode();
                int toPush = temp.isBetween(to_add);
                //if to_add is the key to push up
                if(toPush == 0)
                {
                    root.addData(to_add);

                    Bnode right = new Bnode();
                    right.addData(temp.getGreater());
                    temp.removeGreater();

                    root.setRight(right);
                    root.setLeft(temp);

                    //attach the parent pointer to root in the Bnode
                    right.addParent(root);
                    temp.addParent(root);
                    return 1;
                }
                //if smaller is the key to push up
                else if(toPush == -1)
                {
                    root.addData(temp.getSmaller());
                    Bnode right = new Bnode();
                    right.addData(temp.getGreater());
                    temp.removeSmaller();
                    temp.removeGreater();
                    temp.addData(to_add);


                    root.setLeft(temp);
                    root.setRight(right);

                    right.addParent(root);
                    temp.addParent(root);

                    return 1;
                    //if greater is the key to push up
                } else {
                    root.addData(temp.getGreater());
                    temp.removeGreater();

                    Bnode right = new Bnode();
                    right.addData(to_add);
                    root.setLeft(temp);
                    root.setRight(right);

                    right.addParent(root);
                    temp.addParent(root);
                    return 1;
                }
            }
            return 0;
        }
        else {

            return addService(root, to_add);
        }
    }

    private int addService(Bnode root, Service to_add) {
        if (root == null)
            return 0;

        int compare = root.compare(to_add);

        if(compare == -1) {
            int wasAdded = addService(root.goLeft(), to_add);
            if(wasAdded == 1)
                return 1;

        }
        else if(compare == 2) {
            int wasAdded = addService(root.goMiddle(), to_add);
            if(wasAdded == 1)
                return 1;
        } else {
            int wasAdded = addService(root.goRight(), to_add);
            //we had to add this so that on the stack frame popping off, the service wouldn't get added to the parent Bnode.
            if(wasAdded == 1)
                return 1;
        }
        //add the data to root
        int added = root.addData(to_add);
        if(added == 1) {
            return 1;
        }
        else if(added == 0)
        {
            int between = root.isBetween(to_add);
            //get the parent of the leaf Bnode
            Bnode parent = root.getParent();

            //if the leaf Bnode already has two keys
            if(between == 0) {
                int addToParent = parent.addData(to_add);
                //if the parent already has two keys
                if(addToParent == 0)
                {
                    //find what service is in the middle
                    int parBetween = parent.isBetween(to_add);
                    //if to_add needs to be pushed down
                    if(parBetween == 0) {
                        Bnode middle = new Bnode();
                        parent.setMiddle(middle);

                        middle.addData(to_add);
                        middle.addParent(parent);

                        return 1;
                    }
                    //if the smaller item in the parent needs to be pushed down
                    else if(parBetween == -1) {
                        Bnode middle = new Bnode();
                        parent.setMiddle(middle);

                        Service smaller = parent.getSmaller();
                        middle.addData(smaller);
                        parent.removeSmaller();
                        parent.addData(to_add);

                        middle.addParent(parent);

                        return 1;
                    } else {

                        Bnode middle = new Bnode();
                        parent.setMiddle(middle);

                        Service greater = parent.getGreater();
                        parent.removeGreater();
                        middle.addData(greater);

                        middle.addParent(parent);
                        return 1;
                    }

                }
                return 0;
            }
        }

        return 0;

    }

    public Service retrieveByName(String name, float cost)
    {
        if(this.root == null)
            return null;
        else
            return retrieveByName(this.root, name, cost);
    }

    private Service retrieveByName(Bnode root, String name, float cost)
    {
        if(root == null)
            return null;

        int compare = root.compare(cost);
        if(compare == -1) {
            Service retrieved = retrieveByName(root.goLeft(), name, cost);
            if(retrieved != null)
                return retrieved;
        }
        else if(compare == 2) {
            Service retrieved = retrieveByName(root.goMiddle(), name, cost);
            if(retrieved != null)
                return retrieved;
        }
        else {
            Service retrieved = retrieveByName(root.goRight(), name, cost);
            if(retrieved != null)
                return retrieved;
        }

        if(root.compareName(name) == 1)
            return root.getSmaller();
        else if(root.compareName(name) == 2)
            return root.getGreater();
        else
            return null;
    }

    private Bnode root;
}
