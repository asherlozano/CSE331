package graph;

import java.util.*;


/**
 * A graph represents a mutable list of nodes and labeled edges expressed as a graph. Graphs are
 * lists of paths of nodes that have parents and children
 *
 * An Example of a Graph includes (A,B), (B,A), (C,B), (D,C).
 */
public class Graph<N, D> {
    private final Map<N, HashSet<Edge>> graph;
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
        graph = new HashMap<>();
        checkRep();
    }


    /**
     * adds a node to the current graph
     * @param name is the node being added
     * @spec.modifies modifies the graph by adding a node
     * @spec.requires name != null and name is not already in graph
     */
    public void addNode(N name){
        if(name == null){
            throw new IllegalArgumentException("Node cannot be null");
        }
        if(!graph.containsKey(name)) {
            graph.put(name, new HashSet<>());
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
    public void addEdge(N parent, N child, D label){
        if(!graph.containsKey(parent) || !graph.containsKey(child) || (parent == null || child == null)){
            throw new IllegalArgumentException("The node is not in the graph");
        }
        Edge edge1 = new Edge(label, parent, child);
        graph.get(parent).add(edge1);
        countsEdges++;
        checkRep();
    }

    /**
     * lists all the nodes in the current graph
     * @return a set of strings containing all the nodes in the graph
     *
     */
    public Set<N> listNodes(){
        Set<N> nodeSet = new HashSet<>();
        nodeSet.addAll(graph.keySet());
        return nodeSet;
    }

    /**
     * lists all the children of the called on node
     * @param parent is the current node wanting to list their children
     * @return a list of the children of the called on node
     * @spec.requires the node to be in the graph
     * @spec.requires node has children
     */
    public Set<N> listChildren(N parent){
        if(!graph.containsKey(parent)){
            throw new IllegalArgumentException();
        }
        Set<N> client = new HashSet<>();
        if (graph.get(parent).size() != 0){
            for(Edge current: graph.get(parent)){
                client.add(current.getChild());
            }
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
        HashSet<Edge> setEdges = graph.get(parent);
        Set<D> labels = new HashSet<>();
        for(Edge curr: setEdges){
            if(child.equals(curr.getChild())){
                labels.add((D) curr.getEdgeLabel());
            }
        }
        return labels;
    }
    /**
     * Get a set of all edges in the node
     * @param n the name of the node
     * @spec.requires n != null
     * @return a set of all edges from that node
     */
    public Set<Edge> listNodeEdges(N n){
        if(n == null) {
            throw new IllegalArgumentException("n cannot be null");
        }
        if(!graph.containsKey(n)){
            throw new IllegalArgumentException("n is not in the graph");
        }
        return graph.get(n);
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
                HashSet<Edge> edgeSet = graph.get(node);
                for(Edge edge : edgeSet){
                    assert (edge != null);
                    assert graph.containsKey(edge.getChild());
                }
            }
        }
    }

    /**
     * Edge represents an Immutable Edge that is constructed by two nodes. The parent and the child.
     * An edge is the connection between 2 nodes and has a name which is the label
     *  AF: An Edge is e12 = e(parent, then child)
     * *p = parent c = child and l = label. An Edge looks like this (p, c, l)
     * REP INV: parent != null child != null and label != null
     */
    public class Edge {
        private N parent;
        private N child;
        private D label;

        /**
         * The class edge holds 2 nodes and a name for the labeled edge
         *
         * @param parent starting node
         * @param child  ending node
         * @param label  name of label
         */
        public Edge(D label, N parent, N child) {
            if (parent == null || child == null || label == null) {
                throw new IllegalArgumentException("The label, parent, or node cannot be null");
            }
            this.parent = parent;
            this.child = child;
            this.label = label;
            checkRep();
        }

        /**
         * getParent is called and returns the starting node of the edge
         *
         * @return the starting node
         */
        public N getParent() {
            return parent;
        }

        /**
         * This method when called returns the ending node of the edge
         *
         * @return the child node
         */
        public N getChild() {
            return child;
        }

        /**
         * This method returns the label of the edge
         *
         * @return the label of the edge
         */

        public D getEdgeLabel() {
            return label;
        }

        @Override
        /**
         * This method overrides the equals methods for edges
         * @return true or false if an edge is equal
         */
        public boolean equals(Object other) {
            if (!(other instanceof Graph<?, ?>.Edge)) {
                return false;
            }
            Graph<?, ?>.Edge o = (Graph<?, ?>.Edge) other;
            return this.parent.equals(o.parent) && this.child.equals(o.child) && this.label.equals(o.label);
        }


        /**
         * This method returns the hashcode of an edge
         *
         * @return the hashcode of an edge
         */
        @Override
        public int hashCode() {
            return label.hashCode() ^ (parent.hashCode() ^ child.hashCode());
        }
    }
}

