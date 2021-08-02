package a6;

import java.util.ArrayList;

public class LinkedList<T> {
    private NodeImpl<T> head = null;
    private NodeImpl<T> tail = null;
    private int size = 0;


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
    public boolean contains(T element) {
        NodeImpl<T> current = head;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return true;
            }
            current = (NodeImpl<T>) current.getNext(); // Type casted onto NodeImpl
        }
        return false;
    }

    /*
    converts the linked list into an array
     */
    public T[] toArray() {
        /*
        declaring a generic array is complicated, so that part is given to you
        comment out the line below when you get to this method
         */
        T[] arr =  (T[]) new Object[size()];
        // int[] arr =  new int[size()];
        NodeImpl<T> current = head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = (NodeImpl<T>) current.getNext();
            i++;
        }
        return arr;
    }

    /*
    adds a node to the end of the list
     */
    public void add(T element) {
        NodeImpl<T> newNode = new NodeImpl<T>(element, null);
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
    public boolean remove(T element) {
        NodeImpl<T> current = head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue().equals(element)){
            head = (NodeImpl<T>) head.getNext();
            size--;
            return true;
        }
        while(current.getNext().getValue() != element) {
            current = (NodeImpl<T>) current.getNext();
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
    public T get(int index) {
        validIndex(index);
        NodeImpl<T> current = head;
        int i = 0;
        while (i < index) {
            current = (NodeImpl<T>) current.getNext();
            i++;
        }
        return current.getValue();
    }

    /*
    sets the value of the node at index to the element
     */
    public T set(int index, T element) {
        validIndex(index);
        NodeImpl<T> current = head;
        T prevValue = current.getValue();  //before = 1
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
                current = (NodeImpl<T>) current.getNext();
                i++;
            }
        }
        return prevValue;
    }

    /*
    adds a node at the given index with the given element as its value
     */
    public void add(int index, T element) {
        if(index > size) {
            validIndex(index);
        }
        NodeImpl<T> current = head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                NodeImpl<T> newNode = new NodeImpl<T>(element, head);
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
                NodeImpl<T> temp = (NodeImpl<T>) current.getNext();
                NodeImpl<T> newNode = new NodeImpl<T>(element, temp);
                current.setNext(newNode);
                size++;
                return;
            } else {
                current = (NodeImpl<T>) current.getNext();
                i++;
            }
        }
    }

    /*
     returns the index of the given element
     */
    public int indexOf(T element) {
        NodeImpl<T> current = head;
        int index = 0;
        while(current != null) {
            if(current.getValue().equals(element)) {
                return index;
            }
            index++;
            current = (NodeImpl<T>) current.getNext();
        }
        return -1;
    }

    /*
    returns the last index of the element
     */
    public int lastIndexOf(T element) {
        NodeImpl<T> current = head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue() == element) {
                index = i;
            }
            i++;
            current = (NodeImpl<T>) current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }

    public NodeImpl<T> getHead() {
        return head;
    }

    /* prints out list */
    public String toString() {
        String list = "";
        NodeImpl<T> current = head;
        while(current != null) {
            if(current.getNext() == null)
                list += current.getValue();
            else
                list += current.getValue() + " -> ";
            current = (NodeImpl<T>) current.getNext();
        }
        return list;
    }

}
