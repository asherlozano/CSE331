package graph;

import java.util.Set;

/**
 * A graph represents a mutable list of nodes and labeled edges expressed as a graph. Graphs are
 * lists of paths of nodes that have parents and children
 *
 * An Example of a Graph includes <A,B>, <B,A>, <C,B>, <D,C>.
 */
public class Graph {

    /**
     * Constructs a new graph
     * @spec.effects Constructs a new Graph, "".
     */
    public Graph(){

    }

    /**
     * adds a node to the current graph
     * @param name is the node being added
     * @spec.modifies modifies the graph by adding a node
     * @throws an exception when a name = a node in graph
     */
    public void addNode(String name){

    }

    /**
     * This method adds an edge to
     * @param node1 is the starting node
     * @param node2 is the ending location
     * @param label is the label to be added to the edge
     *
     * @spec.modifies changes the graph by adding an edge with 2 nodes
     * @spec.requires requires at least 1 node in the graph
     */
    public void addEdge(String node1, String node2, String label){

    }

    /**
     * lists all the nodes in the current graph
     * @returns a set of strings containing all the nodes in the graph
     *
     * @spec.effects adds an array of strings containing nodes
     */
    public Set<String> listNodes(){
        return null;
    }

    /**
     * lists all the children of the called on node
     * @param node1 is the current node wanting to list their children
     * @returns an array of strings of the children of the called on node
     *
     * @spec.effects adds an array of strings containing nodes
     */
    public Set<String> listChildren(String node1){
        return null;
    }

    /**
     * This returns the count of all the nodes currently in the graph
     * @return the number of nodes in the graph
     */
    public int countNodes(){
        return 0;
    }

    /**
     * This method returns all the edges of the graph
     * @returns a count of all the edges in the graph
     */
    public int countEdges(){
        return 0;
    }


}

