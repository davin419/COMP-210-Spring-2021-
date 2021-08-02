package a3;

public interface BST {
    /**
     * Returns the root of the tree
     *
     * @return the root of the tree
     */
    Node getRoot();

    /**
     * Returns the longest path from the root value to a leaf in the tree
     *
     * @return the longest path from the root to a leaf.
     */
    int height();

    /**
     * Creates a node with the parameter as its value
     * and inserts the node into the tree in the appropriate position.
     * This method should call upon the recursive insert()
     *
     * @param value to be inserted in tree
     * @return String after insertion
     **/
    String insert(String value);

    /**
     * Removes the node containing the value from the tree if it is present.
     * This method should call upon the recursive remove()
     *
     * @param value to be removed from tree
     */
    void remove(String value);

    /**
     * Returns true if the tree is a full tree. A full tree is defined as
     * a binary tree where each node either has 2 children or 0 children.
     * This method should call upon the recursive isFull()
     * @return
     */
    boolean isFull();

    /**
     * Returns the smallest element in the tree.
     * This method should call upon the recursive findMin()
     * @return the smallest element in the tree
     */
    String findMin();

    /**
     * Returns the largest element in the tree.
     * This method should call upon the recursive findMax()
     * @return the largest element in the tree
     */
    String findMax();

    /**
     * Returns true if the bst contains the given string
     * and false otherwise. This method should call upon a
     * private recursive contains() method
     * @return true if the string is in the tree, false if not
     */
    boolean contains(String s);

    /**
     * Return the node that contains the given String as its value
     * @param s - the desired string
     * @return Node containing the string
     */
    Node get(String s);
    /**
     * Returns the number of elements in the tree.
     *
     * @return the number of elements in the tree
     */
    int size();




}
