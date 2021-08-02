package a5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GraphImpl implements Graph {
    Map<String, NodeImpl> nodes; //do not delete, use this field to store your nodes
                             // key: name of node. value: Node object associated with name
    private int nodeCount;
    private int edgeCount;

    public GraphImpl() {
        nodes = new HashMap<>();
        nodeCount = 0;
        edgeCount = 0;
    }

    /**
     * @param name - string for name of Node, must be unique
     *                  (cannot be the same as an existing node in the graph)
     * @return false if label is not unique (or is null)
     *         true if node is successfully added
     */
    @Override
    public boolean addNode(String name) {
        if (nodes.containsKey(name) || name == null) { return false; }
        nodes.put(name, new NodeImpl(name));
        nodeCount++;
        return true;
    }

    /**
     * @param src - label of source node
     * @param dest - label of destination node
     * @param weight - weight for new edge (use 1 by default)
     * @return false if edge weight is less than 0
     *         false if source node is not in graph
     *         false if destination node is not in graph
     *         false is there already is an edge between these 2 nodes
     *         true if edge is successfully added
     */
    @Override
    public boolean addEdge(String src, String dest, double weight) {
        if(weight < 0 || !nodes.containsKey(src) || !nodes.containsKey(dest) || edgeExists(src, dest)) { return false; }
        EdgeImpl newEdge = new EdgeImpl(src,dest,weight);

        nodes.get(src).outEdge.put(dest, newEdge);
        edgeCount++;
        return true;
    }
    public boolean addEdge(String src, String dest) {   // Adds edge if weight not provided
        if(!nodes.containsKey(src) || !nodes.containsKey(dest) || edgeExists(src, dest)) { return false; }
        EdgeImpl newEdge = new EdgeImpl(src,dest,1);

        nodes.get(src).outEdge.put(dest, newEdge);
        edgeCount++;
        return true;
    }
    private boolean edgeExists(String src, String dest) {
        return nodes.get(src).outEdge.containsKey(dest);
    }

    /**
     * @param name - label of node to remove
     * @return false if the node does not exist
     *         true if the node is found and successfully removed
     */
    @Override
    public boolean deleteNode(String name) {
        if(!nodes.containsKey(name)) { return false; }
        NodeImpl node = nodes.get(name);
        // remove associated edges
        // loop through each node and
        // check to see if their inEdge/outEdge
        // contains the node to be deleted
        for(String key : nodes.keySet()) {
            removeEdges(key, name);
        }
        nodes.remove(name,node);
        nodeCount--;
        return true;
    }
    private void removeEdges(String currNode, String refNode) {
        EdgeImpl edge = nodes.get(refNode).outEdge.get(currNode);
        if(nodes.get(currNode).outEdge.containsKey(refNode)) {
            nodes.get(currNode).outEdge.remove(refNode);
        }
        if(nodes.get(refNode).outEdge.containsKey(currNode)) {
            nodes.get(refNode).outEdge.remove(currNode);
        }
        edge = null;
    }

    /**
     * @param src - label for source node
     * @param dest - label from destination node
     * @return false if the edge does not exist
     *         true if the edge is found and successfully removed
     */
    @Override
    public boolean deleteEdge(String src, String dest) {
        if(!edgeExists(src, dest)) { return false; }
        EdgeImpl edge = nodes.get(src).outEdge.get(dest);
        nodes.get(src).outEdge.remove(dest);
        edge = null;
        edgeCount--;
        return true;
    }

    /**
     * reports how many nodes are in the graph
     * @return - integer 0 or greater that is the number of nodes in the graph
     */
    @Override
    public int numNodes() {
        return nodeCount;
    }

    /**
     * reports how many edges are in the graph
     * @return - integer 0 or greater that is the number of edges in the graph
     */
    @Override
    public int numEdges() {
        return edgeCount;
    }

    /**
     * Runs Dijkstra's algorithm on the graph, starting at the node specified by the start parameter.
     * Return a map where the key is the name of the node and the value is the distance from the start node.
     * The start node should be included in the returned map, with the value 0 as the distance.
     *
     * @param start - Node to start Dijkstra's algorithm at
     * @return Map of all the nodes in the graph and the distance to the start Node.
     */
    @Override
    public Map<String, Double> dijkstra(String start) {
        Map<String, Double> distances = new HashMap<>();
        Map<String, Boolean> visited = new HashMap<>();
        PriorityQueue<NodeImpl> nodeQ = new PriorityQueue<>();

        for(String n : nodes.keySet()){     // initializes visited map as false
            visited.put(n, false);
            distances.put(n, Double.MAX_VALUE);
        }

        nodes.get(start).setDistance(0);        // Decide whether to keep/update distances in node class
        distances.replace(start, 0.0);
        nodeQ.add(nodes.get(start));

        while(nodeQ.size() > 0){        // Add stuff to the distances map
            NodeImpl n = nodeQ.poll();
            if(!visited.get(n.getName())) {     // checks to see if node has been visited
                visited.replace(n.getName(), true);

                for(String adj : n.outEdge.keySet()) {
                    double newWeight = distances.get(n.getName()) + n.outEdge.get(adj).getWeight();
                    if(distances.get(adj) > newWeight) {
                        distances.replace(adj, newWeight);
                        nodes.get(adj).setDistance(newWeight);
                        nodeQ.add(nodes.get(adj));
                    }
                }
            }
        }
        return distances;
    }
}
