package a5;

public class EdgeImpl implements Edge {
    private String source;
    private String destination;
    private double weight;

    public EdgeImpl(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public String getSource() { return source; }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
