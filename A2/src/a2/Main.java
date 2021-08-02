package a2;


public class Main {

    public static void main(String[] args) {
        LinkedList list1 = new LinkedList();
        list1.add(10);
        list1.add(9);
        list1.add(8);
        list1.add(7);
        list1.add(6);
        list1.add(5);
        System.out.println(list1.toString());
        list1.reverse();
        System.out.println(list1.toString());
    }
}
