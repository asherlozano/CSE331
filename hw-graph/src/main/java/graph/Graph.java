package graph;

import java.util.*;


/**
 * A graph represents a mutable list of nodes and labeled edges expressed as a graph. Graphs are
 * lists of paths of nodes that have parents and children
 *
 * An Example of a Graph includes (A,B), (B,A), (C,B), (D,C).
 */
public class Graph<N, D> {
    private final HashMap<N, HashSet<Edge<N, D>>> graph;
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
        this.graph = new HashMap<N, HashSet<Edge<N, D>>>();
        checkRep();
    }

//    public Graph(N node, D edge){
//        this.graph = new HashMap<<N>node, HashSet<Edge<<N>node, <E>edge>>>();
//    }

    /**
     * adds a node to the current graph
     * @param name is the node being added
     * @spec.modifies modifies the graph by adding a node
     * @spec.requires name != null and name is not already in graph
     */
    public void addNode(N name){
        if(name == null){
            throw new NullPointerException();
        }
        if(!graph.containsKey(name)) {
            graph.put(name, new HashSet<Edge<N, D>>());
        }
        checkRep();
    }

    /**
     * This method adds an edge to
     * @param parent is the starting node
     * @param child is the ending location
     * @param label is the label to be added to the edge
     *
     * @spec.modifies changes the graph by adding an edge with 2 nodes
     * @spec.requires requires at least 1 node in the graph, and the only one edge of the same edge to be in the graph
     *
     */
    public boolean addEdge(N parent, N child, D label){
        if(!graph.containsKey(parent) || !graph.containsKey(child)){
            throw new IllegalArgumentException();
        }
        HashSet<Edge<N, D>> setEdges = graph.get(parent);
        Edge<N, D> edge1 = new Edge<N, D>(parent, child, label);
        if(!(setEdges.contains(edge1))){
            setEdges.add(edge1);
            return true;
        }
        countsEdges++;
        checkRep();
        return false;
    }

    /**
     * lists all the nodes in the current graph
     * @return a set of strings containing all the nodes in the graph
     *
     */
    public Set<N> listNodes(){
        return Collections.unmodifiableSet(graph.keySet());
    }

    /**
     * lists all the children of the called on node
     * @param parent is the current node wanting to list their children
     * @return a list of the children of the called on node
     * @spec.requires the node to be in the graph
     * @spec.requires node has children
     */
    public List<N> listChildren(N parent){
        if(!graph.containsKey(parent)){
            throw new IllegalArgumentException();
        }
        HashSet<Edge<N, D>> parentEdges = graph.get(parent);
        List<N> client = new ArrayList<>();
        for (Edge<N, D> current : parentEdges){
            client.add(current.getChild());
        }
        return client;


    }

    /**
     * Gets the label of the parent and child nodes
     * @param parent is the parent node of the label
     * @param child is the child node of the child
     * @return the String which is the edgeLabel
     * @spec.requires the parent and child to be in the graph.
     */
    public Set<D> getLabel(N parent, N child){
        HashSet<Edge<N, D>> setEdges = graph.get(parent);
        Set<D> labels = new HashSet<>();
        for(Edge<N, D> curr: setEdges){
            if(child.equals(curr.getChild())){
                labels.add(curr.getEdgeLabel());
            }
        }
        return labels;
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
            for(N node: graph.keySet()) {
                HashSet<Edge<N, D>> edgeSet = graph.get(node);
                for(Edge<N, D> edge : edgeSet){
                    assert (edge != null);
                    assert graph.containsKey(edge.getChild());
                }
            }
        }
    }
}

