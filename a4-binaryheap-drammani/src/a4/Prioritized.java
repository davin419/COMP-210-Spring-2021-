package a4;

public class Prioritized {
    private double value;
    private double priority;


    // Prioritized objects will make up the heap. They take in a double for the value and a double for the priority
    // do not change anything below
    public Prioritized(double value, double priority) {
        this.value = value;
        this.priority = priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public double getPriority() {
        return priority;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
