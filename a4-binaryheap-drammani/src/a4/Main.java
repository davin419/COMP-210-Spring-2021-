package a4;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        PriorityQueue q = new PriorityQueue();
        Prioritized a = new Prioritized(18,18);
        Prioritized b = new Prioritized(6,6);
        Prioritized c = new Prioritized(25,25);
        Prioritized d = new Prioritized(7,7);
        Prioritized e = new Prioritized(9,9);
        Prioritized f = new Prioritized(12,12);
        Prioritized g = new Prioritized(3,3);
        Prioritized h = new Prioritized(15,15);
        Prioritized[] elements = {a,b,c,d,e,f,g,h};
        q.build(elements);
        q.showSort();

    }
}
