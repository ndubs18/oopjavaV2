    /* Nick Winkler -- CS202 -- Fant -- Bnode.java
     * This file holds the class interface for our Bnode class*/

public class Bnode {

        public int compare(Service to_compare) {
            if(greater == null)
            {
                if(smaller.compare(to_compare) == 1)
                    return -1;
                else
                    return 1;
            } else {
                if(smaller.compare(to_compare) == 1)
                    return -1;
                else if(greater.compare(to_compare) == -1)
                    return 1;
                else if(smaller.compare(to_compare) == -1 && greater.compare(to_compare) == 1)
                    return 2;
            }

            return 0;
        }

        public int compare(float cost) {
            if(greater == null)
            {
                if(smaller.compare(cost) == 1)
                    return -1;
                else
                    return 1;
            } else {
                if(smaller.compare(cost) == 1)
                    return -1;
                else if(greater.compare(cost) == -1)
                    return 1;
                else if(smaller.compare(cost) == -1 && greater.compare(cost) == 1)
                    return 2;
            }

            return 0;
        }

        public int compareName(String to_compare)
        {
            if(smaller == null)
                return 0;
            else {
                if(to_compare.equals(smaller.getName()) == true)
                    return 1;
                else if(greater != null && to_compare.equals(greater.getName()) == true)
                    return 2;
                else
                    return 0;
            }
        }

        public int isBetween(Service to_compare) {
            if(smaller.compare(to_compare) == -1 && greater.compare(to_compare) == 1) {
                return 0;
            }
            else if(smaller.compare(to_compare) == 1 && greater.compare(to_compare) == 1) {
                return -1;
            }
            else {
                return 1;
            }
        }

        public final void display() {
            if(smaller != null && greater == null)
                smaller.display();
            else {
                smaller.display();
                greater.display();
            }
        }

        public int addData(Service to_add) {
            int whatServ = whatService(to_add);
            //if there are no keys in the this Bnode
            if(smaller == null && greater == null) {
                if(whatServ == 1) {
                    FoodDelivery temp = (FoodDelivery)to_add;
                    smaller = new FoodDelivery(temp);
                    return 1;
                }
                else if(whatServ == 2)
                {
                   Landscaping temp = (Landscaping)to_add;
                   smaller = new Landscaping(temp);
                    return 1;
                }
                else if(whatServ == 3) {
                    BabySitting temp = (BabySitting)to_add;
                    smaller = new BabySitting(temp);
                    return 1;
                }
                else
                    return 0;
            }
            //if there is one data item in this Bnode
            else if(smaller != null && greater == null) {
                //if the new data item is greater than the existing
                if (smaller.compare(to_add) == -1) {
                    if (whatServ == 1) {
                        FoodDelivery temp = (FoodDelivery) to_add;
                        greater = new FoodDelivery(temp);
                        return 1;
                    } else if (whatServ == 2) {
                        Landscaping temp = (Landscaping) to_add;
                        greater = new Landscaping(temp);
                        return 1;
                    } else if (whatServ == 3) {
                        BabySitting temp = (BabySitting) to_add;
                        greater = new BabySitting(temp);
                        return 1;
                    } else
                        return 0;
                    //if the to_add data is smaller than the existing data
                } else {
                    greater = smaller;
                    smaller = null;
                    if (whatServ == 1) {
                        FoodDelivery temp = (FoodDelivery) to_add;
                        smaller = new FoodDelivery(temp);
                        return 1;

                    } else if (whatServ == 2) {
                        Landscaping temp = (Landscaping) to_add;
                        smaller = new Landscaping(temp);
                        return 1;
                    } else if (whatServ == 3) {
                        BabySitting temp = (BabySitting) to_add;
                        smaller = new BabySitting(temp);
                        return 1;
                    }

                }
            }
            else
                return 0;

            return 0;
        }

        public Bnode getParent() {
            if(this.parent == null)
                return null;
            else
                return this.parent;
        }
        public void addParent(Bnode to_add)
        {
            this.parent = to_add;
        }
        public Service getSmaller() {
            if(this.smaller == null)
                return null;
            else
                return this.smaller;
        }

        public Service getGreater() {
            if(this.greater == null)
                return null;
            else
                return this.greater;
        }

        public boolean removeSmaller() {
            smaller = null;
            return true;
        }

        public boolean removeGreater() {
            greater = null;
            return true;
        }

        public int whatService(Service to_check) {
            if(to_check instanceof FoodDelivery)
                return 1;
            else if(to_check instanceof Landscaping)
                return 2;
            else
                return 3;
        }

        public Bnode goLeft() {
            return left;
        }
        public Bnode goRight() {
            return right;
        }
        public Bnode goMiddle() {
            return this.middle;
        }
        public void setLeft(Bnode to_set) {
            this.left = to_set;
        }
        public void setRight(Bnode to_set) {
            this.right = to_set;
        }
        public void setMiddle(Bnode to_set) {
            this.middle = to_set;
        }

        private Bnode parent;

        private Service smaller;
        private Service greater;

        private Bnode left;
        private Bnode middle;
        private Bnode right;
}
