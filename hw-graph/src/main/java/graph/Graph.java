package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * A graph represents a mutable list of nodes and labeled edges expressed as a graph. Graphs are
 * lists of paths of nodes that have parents and children
 *
 * An Example of a Graph includes <A,B>, <B,A>, <C,B>, <D,C>.
 */
public class Graph {
    private HashMap<String, HashSet<Edge>> graph;
    public static final boolean CHECK_REP = false;
    private int countsEdges = 0;
    /**
     * Constructs a new graph
     * @spec.effects Constructs a new Graph, "".
     */
    //Rep Invariant:
    // The rep invariant is: graph != null && graph.contains(null)
    // when the graph != null, one of the nodes = null
    // a node != a different node, and an edge != null.
    //Abstract Function:
    // AF() = graph g1 that
    // g1 = [] an empty graph
    // if node1 is a node in g1, then
    // node1 = [] if node1 doesn't have an edge to it.
    // if not node1 = [node1(edge1), node2(edge2), ....] node1 = [edge1], node2 = [edge2]
    // node1 is parent node and node2 represents a child of node1 and edge1 and edge2 are the labels of the
    // edges.
    public Graph(){
        this.graph = new HashMap<>();
        checkRep();
    }

    /**
     * adds a node to the current graph
     * @param name is the node being added
     * @spec.modifies modifies the graph by adding a node
     * @throws an exception when a name = null or another node in a graph
     */
    public void addNode(String name){
        if(name == null){
            throw new NullPointerException();
        }
        graph.put(name, new HashSet<Edge>());
        checkRep();
    }

    /**
     * This method adds an edge to
     * @param node1 is the starting node
     * @param node2 is the ending location
     * @param label is the label to be added to the edge
     *
     * @spec.modifies changes the graph by adding an edge with 2 nodes
     * @spec.requires requires at least 1 node in the graph
     * @throws throws an expection when the node is not in the graph
     * @throws throws an expection when the edge is already in the graph
     *
     */
    public void addEdge(String node1, String node2, String label){
        if(!graph.containsKey(node1) || !graph.containsKey(node2)){
            throw new IllegalArgumentException();
        }

        Edge edge1 = new Edge(node1, node2, label);
        HashSet<Edge> setEdges = graph.get(node1);
        if(setEdges.contains(edge1)){
            throw new IllegalArgumentException();
        }
        setEdges.add(edge1);
        countsEdges++;
        checkRep();
    }

    /**
     * lists all the nodes in the current graph
     * @return a set of strings containing all the nodes in the graph
     *
     */
    public Set<String> listNodes(){
        return graph.keySet();
    }

    /**
     * lists all the children of the called on node
     * @param node1 is the current node wanting to list their children
     * @return an array of strings of the children of the called on node
     * @throws an expection if the node is not in the graph
     */
    public Set<String> listChildren(String node1){
        if(!graph.containsKey(node1)){
            throw new IllegalArgumentException();
        }
        Set<Edge> node1Edges = graph.get(node1);
        Set<String> childrenSet = new HashSet<>();
        for(Edge e : node1Edges){
            childrenSet.add(e.getNode2());
        }
        return childrenSet;
    }

    /**
     * This returns the count of all the nodes currently in the graph
     * @return the number of nodes in the graph
     */
    public int countNodes(){
        return graph.keySet().size();
    }

    /**
     * This method returns all the edges of the graph
     * @return a count of all the edges in the graph
     */
    public int countEdges(){
        return countsEdges;
    }

    private void checkRep(){
        assert (graph != null);
        assert (!graph.containsKey(null));
        if(CHECK_REP){
            for(String node: graph.keySet()) {
                Set<Edge> edgeSet = graph.get(node);
                for(Edge edge : edgeSet){
                    assert (edge != null);
                    assert graph.containsKey(edge.getNode1());
                    assert graph.containsKey(edge.getNode2());
                }
            }
        }
    }
}

