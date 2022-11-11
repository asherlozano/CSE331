package graph.junitTests;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import graph.*;

import static org.junit.Assert.*;

public class GraphTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    //10 seconds max per each method tested
    // This test checks if countNodes works and returns the right number of counting nodes
    // Checks cases for counting multiple nodes, creating a graph and makes sure there is a good amount of nodes
    @Test
    public void testCountNodes() {
        Graph graph = new Graph();
        graph.addNode("5");
        graph.addNode("7");
        assertEquals(2, graph.countNodes());
    }

    // This test uses a node that is non existant
    // This test checks cases for when an edge is trying to be made without atleast 1 node existing
    @Test
    public void testEdgeToNowhere(){
        Graph graph = new Graph();
        graph.addNode("2");
        try {
            graph.addEdge("2", "3", "e23");
        } catch(IllegalArgumentException e){

        }
        assertNull("Throws IllegalArgumentException because atleast 1 node didn't exist");
    }


    //This test checks if a null node is created and used to make an edge.
    // This test should check cases where a node is null and an edge is null
    @Test
    public void testNodeToNullEdge(){
        Graph graph = new Graph();
        graph.addNode(null);
        graph.addNode("2");
        graph.addEdge(null, "2", "eN2");
        Object n = null;
        try {
            graph.addEdge(null, "2", "eN2");
            n = graph;
        } catch(NullPointerException e){

        }
        assertNull("Throws NullPointerException because 1 node was null", n);
    }

//    public void testEdgeEquals(){
//        Graph graph = new Graph();
//        graph.addNode("5");
//        graph.addNode("7");
//        graph.addEdge("5", "7", "e57");
//        graph.addEdge("5", "7", "e57");
//        assertEquals()
//    }



    // This test counts the total edges of the graph
    // This test will check if it returns the right amount of edges
    // Checks cases where there is an extra node, multiple edges, and same node edges
    @Test
    public void testCountEdges() {
        Graph graph = new Graph();
        graph.addNode("5");
        graph.addNode("7");
        graph.addNode("9");
        graph.addEdge("5", "7", "5to7");
        graph.addEdge("7", "5", "7to5");
        assertEquals(2, graph.countEdges());

    }
}
