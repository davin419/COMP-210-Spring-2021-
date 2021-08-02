package a4;


public interface Queue {

    /**
     * Create new Prioritized object and insert it into the heap in the proper position.
     * The heap used will be a MAXIMUM binary heap.
     * @param value
     * @param priority
     */
    void enqueue(double value, double priority);

    /**
     * Remove the element with the smallest priority from the heap
     * and return its value
     *
     * @return the value of the removed element
     */
    double dequeue();

    /**
     * return the value of the prioritized object with the highest priority
     * without removing it
     * @return value of the prioritized object with the highest priority
     */
    double front();

    /**
     * Return the number of Prioritized objects in the heap. Do not return the size of the entire array (1000)
     * @return numbers of elements in heap
     */
    int size();

    /**
     * Return true if the priority queue is empty, false otherwise. The object at element 0 does not count as
     * part of the heap.
     * @return true if the priority queue is empty, false otherwise
     */
    boolean empty();

    /**
     * Return an array of the sorted values of the Priority Queue.
     * This should be an in place sort (you should sort the heap without making a new array)
     * @return array of sorted elements of the priority queue
     */
    double[] sort();

    /**
     *  Insert all the objects in the array into the heap. assume for a build that the heap will start empty.
     * @param elements array of elements that need to be in the heap
     */
    void build(Prioritized[] elements);

}
