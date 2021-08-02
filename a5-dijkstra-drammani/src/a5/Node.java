package a5;

public interface Node extends Comparable<Node> {

     /**
      * @return the name of the node
      */
     String getName();

     int compareTo(Node node);

     double getDistance();

     void setDistance(double d);

}
