package a5;

import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args){
        GraphImpl g = new GraphImpl();
        g.addNode("a");
        g.addNode("b");
        g.addNode("c");
        g.addNode("d");
        g.addNode("e");
        g.addNode("f");
        g.addNode("g");

        g.addEdge("a","b",2);
        g.addEdge("a","d",1);
        g.addEdge("b","d",3);
        g.addEdge("b","e",1);
        g.addEdge("c","a",4);
        g.addEdge("c","f",5);
        g.addEdge("d","c",2);
        g.addEdge("d","f",8);
        g.addEdge("d","g",4);
        g.addEdge("d","e",3);
        g.addEdge("e","g",6);
        g.addEdge("g","f",1);
        System.out.println(g.dijkstra("a"));
    }
}
