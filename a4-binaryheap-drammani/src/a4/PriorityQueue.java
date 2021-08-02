package a4;


import java.util.Arrays;

public class PriorityQueue implements Queue {

    private Prioritized[] heap;
    private int size;
    private static final int arraySize = 10000; // Everything in the array will initially
    // be null. This is ok! Just build out
    // from array[1]. Don't change the value of this variable.

    public PriorityQueue() {
        heap = new Prioritized[arraySize];
        size = 0;
        heap[0] = new Prioritized(Double.NaN, Double.NaN); //0th will be unused for simplicity
                                                            //of child/parent computations
    }

    // fill in the methods below based on the descriptions in the Queue interface. Do NOT change the interface or any
    // of the method signatures
    /**
     * Create new Prioritized object and insert it into the heap in the proper position.
     * The heap used will be a MAXIMUM binary heap.
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(double value, double priority) {
        //1. Insert element at end of list
        heap[size+1] = new Prioritized(value, priority);
        //2. Keep comparing priority with parent element (i/2) until priority <= parent
        size++;
        int i = size;
        if (size > 1) {
            while (heap[i].getPriority() > heap[i/2].getPriority()) {
                Prioritized current = heap[i];
                heap[i] = heap[i/2];
                heap[i/2] = current;
                i /= 2;
            }
        }
    }

    /**
     * Remove the element with the largest priority from the heap
     * and return its value
     *
     * @return the value of the removed element
     */
    @Override
    public double dequeue() {
        Prioritized temp = heap[size];
        int i = 1;
        double val = heap[1].getValue();
        heap[i] = temp;
        heap[size] = null;
        while ((maxNode(heap[2*i], heap[2*i+1]) != null) && (temp.getPriority() < maxNode(heap[2*i], heap[2*i+1]).getPriority())) {
            Prioritized maxNode = maxNode(heap[2*i], heap[2*i+1]);
            int maxIndex;
            if (maxNode == heap[2*i]) maxIndex = 2*i;
            else maxIndex = 2*i+1;
            heap[i] = maxNode;
            heap[maxIndex] = temp;
            i = maxIndex;
        }
        size--;
        return val;
    }
    private Prioritized maxNode(Prioritized l, Prioritized r) {
        if (l == null && r == null) return null;
        else if (l == null) return r;
        else if (r == null) return l;
        else if (l.getPriority() < r.getPriority()) return r;
        else return l;
    }

    /**
     * return the value of the prioritized object with the highest priority
     * without removing it
     * @return value of the prioritized object with the highest priority
     */
    @Override
    public double front() {
        return heap[1].getValue();
    }

    /**
     * Return the number of Prioritized objects in the heap. Do not return the size of the entire array (1000)
     * @return numbers of elements in heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Return true if the priority queue is empty, false otherwise. The object at element 0 does not count as
     * part of the heap.
     * @return true if the priority queue is empty, false otherwise
     */
    @Override
    public boolean empty() {
        return size == 0;
    }

    /**
     * Return an array of the sorted values of the Priority Queue.
     * @return array of sorted elements of the priority queue
     */
    @Override
    public double[] sort() {
        double[] values = new double[size];
        for (int i=size-1; i>=0; i--) {
            values[i] = this.dequeue();
        }
        return values;
    }

    /**
     *  Insert all the objects in the array into the heap. assume for a build that the heap will start empty.
     * @param elements array of elements that need to be in the heap
     */
    @Override
    public void build(Prioritized[] elements) {
        // 1. Start with parent of last node and bubble down
        // 2. Go back node by node and bubble down
        int parent = (elements.length-2)/2;
        while (parent >= 0) {
            int curr = parent;
            Prioritized currNode = elements[curr];
            Prioritized leftNode;
            Prioritized rightNode;
            if (2*curr+1 >= elements.length) leftNode = null;
            else leftNode = elements[2*curr+1];
            if (2*curr+2 >= elements.length) rightNode = null;
            else rightNode = elements[2*curr+2];

            while ((maxNode(leftNode,rightNode) != null) && (currNode.getPriority() < maxNode(leftNode,rightNode).getPriority())) {
                Prioritized maxNode = maxNode(leftNode, rightNode);
                int maxIndex;
                if (maxNode == leftNode) maxIndex = 2*curr+1;
                else maxIndex = 2*curr+2;
                if (maxIndex >= elements.length) break;
                elements[curr] = maxNode;
                elements[maxIndex] = currNode;
                curr = maxIndex;
                if (2*curr+1 >= elements.length) leftNode = null;
                else leftNode = elements[2*curr+1];
                if (2*curr+2 >= elements.length) rightNode = null;
                else rightNode = elements[2*curr+2];
            }
            parent--;
        }

        for (int i=0; i<elements.length; i++) {
            heap[i+1] = elements[i];
        }
        size = elements.length;
    }

    public void show() {
        double[] values = new double[size];
        double[] priority = new double[size];
        for (int i=0; i< size; i++) {
            values[i] = heap[i+1].getValue();
            priority[i] = heap[i+1].getPriority();
        }
        System.out.print("Values:     ");
        System.out.println(Arrays.toString(values));
        System.out.print("Priorities: ");
        System.out.println(Arrays.toString(priority));
    }
    public void showSort() {
        System.out.print("Sorted: ");
        System.out.println(Arrays.toString(this.sort()));
    }


    // do not change
    public Prioritized[] getHeap() {
        return heap;
    }
}
