package a5;

import java.util.HashMap;

public class NodeImpl implements Node {
    private String name;
    private double distance;
    HashMap<String, EdgeImpl> outEdge;

    public NodeImpl(String name) {
        this.name = name;
        this.outEdge = new HashMap<>();
        this.distance = Double.MAX_VALUE;
    }

    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Node node2) {
        if (this.getDistance() > node2.getDistance()) {
            return 1;
        } else if(this.getDistance() < node2.getDistance()){
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public double getDistance() {
        return distance;
    }

    @Override
    public void setDistance(double d) {
        this.distance = d;
    }


}
