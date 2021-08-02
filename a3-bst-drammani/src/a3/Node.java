package a3;

public interface Node {

    Node getLeft();
    Node getRight();
    void setLeft(Node left);
    void setRight(Node right);
    String getValue();
    void setValue(String value);
}
