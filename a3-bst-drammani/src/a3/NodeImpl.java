package a3;

public class NodeImpl implements Node {

    private Node left;
    private Node right;
    private String value;

    public NodeImpl(Node left, Node right, String value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public NodeImpl(String value) {
        this.left = null;
        this.right = null;
        this.value = value;
    }
    @Override
    public Node getLeft() {
        return this.left;
    }

    @Override
    public Node getRight() {
        return this.right;
    }

    @Override
    public void setLeft(Node left) {
        this.left = left;
    }

    @Override
    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
