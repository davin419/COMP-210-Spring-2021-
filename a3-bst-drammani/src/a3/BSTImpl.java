package a3;

import java.security.spec.RSAOtherPrimeInfo;

public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public BSTImpl(String s) {
        root = new NodeImpl(s);
        size = 0;
    }

    // The implementation of "height" is given to you below
    // it is here to illustrate for you how to set up
    // the method implementation as recursion.
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive methods

    @Override
    public int height() { // public interface method signature
        // this method is the public interface method
        // it is not recursive, but it calls a recursive
        // private method and passes it access to the tree cells
        return height_recursive(this.root);
    }
    private int height_recursive(Node c) {
        // inner method with different signature
        // this helper method uses recursion to traverse
        // and process the recursive structure of the tree of cells
        if (c==null) return -1;
        int lht = height_recursive(c.getLeft());
        int rht = height_recursive(c.getRight());
        return Math.max(lht,rht) + 1;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    /**
     * Creates a node with the parameter as its value
     * and inserts the node into the tree in the appropriate position.
     * This method should call upon the recursive insert()
     *
     * @param value to be inserted in tree
     * @return String after insertion
     **/
    @Override
    public String insert(String value) {
        this.root = insert_r(value, this.root);
        return value;
    }

    private Node insert_r(String val, Node c) {
        if (c == null) { // If BST is empty
            c = new NodeImpl(val);
            size++;
            return c;
        }

        int cflag = val.compareTo(c.getValue());

        if (cflag < 0) {
            c.setLeft(insert_r(val, c.getLeft()));
        }
        else if (cflag > 0) {
            c.setRight(insert_r(val,c.getRight()));
        }
        return c;
    }

    // remove implementation given to you, do NOT change
    @Override
    public void remove(String value) {
        remove_r(value,this.root);
        size--;
    }
    private Node remove_r(String k, Node c) {
        if (c==null) return null; // item not found, nothing to do

        // now we know we have a real node to examine
        int cflag = k.compareTo(c.getValue());

        if (cflag<0) { // k is smaller than node
            c.setLeft(remove_r(k,c.getLeft()));
            return c;
        }
        else if (cflag>0) { // k is larger than node
            c.setRight(remove_r(k,c.getRight()));
            return c;
        }
        else //cflag==0
        { // found it... now figure out how to rearrange

            // cases
            if (c.getLeft()==null && c.getRight()==null) { c = null; } // leaf
            else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); } // RC only
            else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); } // LC only
            else { // 2 children
                Node max = maxCell(c.getLeft());
                c.setValue(max.getValue());
                c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
            }
            return c;
        }

    }

    private Node maxCell(Node c) { // this is used in remove too
        if (c.getRight()==null) return c;
        return maxCell(c.getRight());
    } ;

    /**
     * Returns true if the tree is a full tree. A full tree is defined as
     * a binary tree where each node either has 2 children or 0 children.
     * This method should call upon the recursive isFull()
     * @return
     */
    @Override
    public boolean isFull() {
        return isFull_r(this.root);
    }

    private boolean isFull_r(Node c) {
        if (c == null) {
            return true;
        }
        if (c.getLeft() == null && c.getRight() == null) {
            return true;
        }
        else if (c.getLeft() != null && c.getRight() != null) {
            return isFull_r(c.getLeft()) && isFull_r(c.getRight());
        }
        return false;
    }

    /**
     * Returns the smallest element in the tree.
     * This method should call upon the recursive findMin()
     * @return the smallest element in the tree
     */
    @Override
    public String findMin() {
        Node c = findMin_r(this.root);
        return c.getValue();
    }
    private Node findMin_r(Node c) {
        if (c.getLeft() == null) {
            return c;
        } else {
            return findMin_r(c.getLeft());
        }
    }

    /**
     * Returns the largest element in the tree.
     * This method should call upon the recursive findMax()
     * @return the largest element in the tree
     */
    @Override
    public String findMax() {
        Node c = findMax_r(this.root);
        return c.getValue();
    }
    private Node findMax_r(Node c) {
        if (c.getRight() == null) {
            return c;
        } else {
            return findMax_r(c.getRight());
        }
    }

    /**
     * Returns true if the bst contains the given string
     * and false otherwise. This method should call upon a
     * private recursive contains() method
     * @return true if the string is in the tree, false if not
     */
    @Override
    public boolean contains(String s) {
        return contains_r(s, this.root);
    }
    private boolean contains_r(String val, Node c) {
        if (c == null) {
            return false;
        }
        int cflag = val.compareTo(c.getValue());
        if (cflag == 0) {
            return true;
        } else if (cflag < 0) {
            return contains_r(val, c.getLeft());
        } else if (cflag > 0){
            return contains_r(val, c.getRight());
        }
        return false;
    }

    /**
     * Return the node that contains the given String as its value
     * @param s - the desired string
     * @return Node containing the string
     */
    @Override
    public Node get(String s) {
        return get_r(s, this.root);
    }
    private Node get_r(String val, Node c) {
        if (c == null) {
            return null;
        }
        int cflag = val.compareTo(c.getValue());
        if (cflag == 0) {
            return c;
        } else if (cflag < 0) {
            return get_r(val, c.getLeft());
        } else if (cflag > 0){
            return get_r(val, c.getRight());
        }
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void show() {
        int off = 4;
        int lev = 0;
        for (int k=0; k<10; k++) {
            System.out.print("+");
            for (int kk=0; kk<off-1; kk++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
        show_r(this.root,lev,off);
        for (int k=0; k<10; k++) {
            System.out.print("+");
            for (int kk=0; kk<off-1; kk++) {
                System.out.print("-");
            }
        }
        System.out.println("+");
    }

    private void show_r(Node n, int lev, int off) {
        if (n == null) return;
        show_r(n.getRight(), lev+off, off);
        for (int b=0; b<lev; b++) {
            System.out.print(" ");
        }
        System.out.println(n.getValue());
        show_r(n.getLeft(), lev+off, off);
    }
}
