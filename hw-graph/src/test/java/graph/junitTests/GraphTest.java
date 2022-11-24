package graph.junitTests;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.Timeout;
import graph.*;

import java.util.HashSet;

import static org.junit.Assert.*;

public class GraphTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);
    private String string = "";

    //10 seconds max per each method tested
    // This test checks if countNodes works and returns the right number of counting nodes
    // Checks cases for counting multiple nodes, creating a graph and makes sure there is a good amount of nodes
    @Test

    public void testCountNodes() {
        Graph graph = new Graph(string, string);
        graph.addNode("5");
        graph.addNode("7");
        assertEquals(2, graph.countNodes());
    }



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
