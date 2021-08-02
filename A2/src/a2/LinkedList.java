package a2;

import java.util.HashMap;

public class LinkedList {
    private Node head = null;
    private Node tail = null;
    private int size = 0;


    /**
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is larger than the size of the list, throw an IndexOutOfBounds Exception
     *
     * ex: list: 1 -> 2 -> 3 -> 4
     *     i: 1
     *     list after removeAtIndex: 1 -> 3 -> 4
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i) {
        Node current = head;
        int runningIndex = 0;

        if (i+1 > size) {
            throw new IndexOutOfBoundsException();
        }
        if (i == 0) {
            head = current.getNext();
        } else {
            while (runningIndex < i - 1) {
                current = current.getNext();
                runningIndex++;
            }
            if (current.getNext().getNext() == null) {
                tail = current;
            }
            current.setNext(current.getNext().getNext());
        }
        size--;
    }

    /**
     * Compute and return the average of all the numbers in the linked list rounded down to the nearest integer
     * @return an int that is the floor of the mean of the list.
     */
    public int mean() {
        Node current = head;
        double total = 0.0;

        for (int i=0; i<size; i++) {
            total += current.getValue();
            current = current.getNext();
        }
        return (int) Math.floor(total / size);
    }

    /**
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        Node current1 = this.head;
        Node current2 = list2.head;

        if (this.size != list2.size) { return false; }

        for (int i=0; i<size; i++) {
            if (current1.getValue() != current2.getValue()) {
                return false;
            } else {
                current1 = current1.getNext();
                current2 = current2.getNext();
            }
        }
        return true;
    }

    /**
     * Remove all the nodes at odd indices from the list. Remember that the first Node is at index 0
     *
     * ex: list: 1 -> 3 -> 4 -> 2 -> 8
     *     list after removeOdds: 1 -> 4 -> 8
     */
    public void removeOdds() {
        Node current = head;
        int repeat = size / 2;

        for (int i=0; i<repeat; i++) {
            current.setNext(current.getNext().getNext());
            current = current.getNext();
        }
        tail = current;
        if (size % 2 == 0) {
            size = size / 2;
        } else {
            size = (size+1) / 2;
        }
    }

    /**
     * Return true if the list is symmetrical, false otherwise
     * ex: list: 1 -> 2 -> 3 -> 2 -> 1
     *     return: true
     *
     *     list: 1 -> 2 -> 3 -> 4 -> 5
     *     return: false
     *
     * @return true if the list is symmetrical, false otherwise
     */

    public boolean isSymmetrical() {
        Node current = head;
        int symLength = size/2;
        int[] forward = new int[symLength];
        int[] backward = new int[symLength];

        for (int i=0; i<symLength; i++) {
            forward[i] = current.getValue();
            current = current.getNext();
        }
        if (size % 2 != 0) {
            current = current.getNext();
        }
        for (int i=0; i<symLength; i++) {
            backward[(symLength-1)-i] = current.getValue();
            current = current.getNext();
        }
        for (int i=0; i<symLength; i++) {
            if (forward[i] != backward[i]) {
                return false;
            }
        }
        return true;
    }


    /**
     * Stretch the list so that each element in the list is represented factor times
     * If the factor is 0 the list should be cleared (have 0 nodes)
     * ex: list: 1 -> 2 -> 3
     *     factor: 3
     *     list after multiply: 1 -> 1 -> 1 -> 2 -> 2 -> 2 -> 3 -> 3 -> 3
     *
     * @param factor the amount to multiply the number of occurrences of each element by
     */
    public void multiply(int factor) {
        Node current = head;

        if (factor == 0) {
            clear();
        } else {
            for (int i=0; i<size; i++) {
                int val = current.getValue();
                Node ogNextNode = current.getNext();

                for (int j=0; j<factor-1; j++) {
                    Node newNode = new NodeImpl(val, null);
                    current.setNext(newNode);
                    current = current.getNext();
                }
                current.setNext(ogNextNode);

                if (i == size-1) {
                    tail = current;
                } else {
                    current = ogNextNode;
                }
            }
        }
        size *= factor;
    }

    /**
     * Reverse the list
     *
     * ex list:  10 -> 9 -> 8 -> 7
     *    list after reverse: 7 -> 8 -> 9 -> 10
     *
     */
    public void reverse() {
        Node current = head;
        Node previous = null;
        Node next;
        tail = head;
        /*1. current points to prev
        2. prev moves to current
        3. current moves to next
        4. next moves to next
        5. current points to prev*/
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    /**
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        Node current = head;
        int skips = 0;

        if (current != null) {
            while (current.getNext() != null) {
                if (current.getValue() == current.getNext().getValue()) {
                    Node tmpCurrent = current;

                    while ((tmpCurrent.getValue() == current.getValue()) && (tmpCurrent.getNext() != null)) {
                        tmpCurrent = tmpCurrent.getNext();
                        skips++;
                    }
                    if ((tmpCurrent.getNext() == null) && (current.getValue() == tmpCurrent.getValue())) {
                        tail = current;
                        current.setNext(null);
                    } else {
                        current.setNext(tmpCurrent);
                        current = current.getNext();
                        skips--;
                    }

                } else {
                    current = current.getNext();
                }
            }
        }
        size -= skips;
    }


    /**
     * Return true if the list contains a cycle, false otherwise
     * ex: list: 1 -> 2 -> 3 - > 4 --       (4 points to 2)
     *                ^              |
     *                |              |
     *                ---------------
     *      return: true
     *
     *      list: 1 -> 2 -> 3 -> 4
     *      return: false
     *
     * @return true if the list contains a cycle, false otherwise
     */
    public boolean containsCycle() {
        Node current = head;
        int count = 0;
        while (current.getNext() != null) {
            if (count > size) {
                return true;
            }
            current = current.getNext();
            count++;
        }
        return false;
    }

    /**
     * Merge the given linked list into the current list. The 2 lists will always be
     * either the same size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     return: 1 -> 4 -> 2 -> 5 -> 3 -> 6
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     return 1 -> 5 -> 2 -> 6 -> 3 -> 4
     *
     * @param list2
     */
    public void merge(LinkedList list2) {
        Node current1 = this.head;
        Node current2 = list2.head;

        if (this.size == list2.size) {
            for (int i=0; i<size; i++) {
                Node nextCurrent1 = current1.getNext();
                Node tmpNext = new NodeImpl(current2.getValue(),nextCurrent1);
                current1.setNext(tmpNext);

                current1 = current1.getNext().getNext();
                current2 = current2.getNext();
            }
            tail = list2.tail;
        } else {
            for (int i=0; i<list2.size; i++) {
                Node nextCurrent1 = current1.getNext();
                Node tmpNext = new NodeImpl(current2.getValue(),nextCurrent1);
                current1.setNext(tmpNext);

                current1 = current1.getNext().getNext();
                current2 = current2.getNext();
            }
        }
        size += list2.size;
    }


    /* Implementation given to you. Do not modify below this. */

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /*
    Returns true if the list contains a node whose value matches the element parameter, false otherwise
     */
    public boolean contains(int element) {
        Node current = head;
        while(current != null) {
            if(current.getValue() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /*
    converts the linked list into an array
     */
    public int[] toArray() {
        int[] arr =  new int[size()];
        Node current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    /*
    adds a node to the end of the list
     */
    public void add(int element) {
        Node newNode = new NodeImpl(element, null);
        if(isEmpty()) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }

    }

    /*
    removes the element from the list
     */
    public boolean remove(int element) {
        Node current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            head = head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = current.getNext();
            if(current == null) {
                return false;
            }
        }
        if(current.getNext().getNext() == null) {
            tail = current;
        }
        current.setNext(current.getNext().getNext());
        size--;
        return true;
    }

    /*
        returns the value at the index parameter.
     */
    public int get(int index) {
        validIndex(index);
        Node current = head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    /*
    sets the value of the node at index to the element
     */
    public int set(int index, int element) {
        validIndex(index);
        Node current = head;
        int prevValue = 1;
        int i = 0;
        if(index == 0) {
            prevValue = head.getValue();
            head.setValue(element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue(element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    /*
    adds a node at the given index with the given element as its value
     */
    public void add(int index, int element) {
        if(index > size) {
            validIndex(index);
        }
        Node current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node newNode = new NodeImpl(element, head);
                head = newNode;
                size++;
                return;
            }

        }  else if(index == size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node temp = current.getNext();
                Node newNode = new NodeImpl(element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    /*
    returns the index of the given element
     */
    public int indexOf(int element) {
        Node current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue() == element) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    /*
    returns the last index of the element
     */
    public int lastIndexOf(int element) {
        Node current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue() == element) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public Node getHead() {
        return head;
    }

    /* prints out list */
    public String toString() {
        String list = "";
        Node current = head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }

}